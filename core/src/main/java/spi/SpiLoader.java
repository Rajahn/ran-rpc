package spi;

import cn.hutool.core.io.resource.ResourceUtil;
import serializer.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpiLoader {
    //存储已加载的类, 接口名-> key-> 实现类
    private static Map<String,Map<String,Class<?>>> loaderMap =new ConcurrentHashMap<>();

    private static Map<String,Object> instanceCache = new ConcurrentHashMap<>();

    private static final String RPC_DIR = "META-INF/rpc/";

    private static final List<Class<?>> LOAD_CLASS_LIST = Arrays.asList(Serializer.class);

    public static void loadAll(){
        for(Class<?> c:LOAD_CLASS_LIST){
            load(c);
        }
    }

    public static Map<String,Class<?>> load(Class<?> loadClass){
        Map<String,Class<?>> keyClassMap = new HashMap<>();
        List<URL> resources = ResourceUtil.getResources(RPC_DIR+loadClass.getName());
        for(URL resource:resources){
            System.out.println("Loading SPI from: " + resource);
            try{
                InputStreamReader inputStreamReader = new InputStreamReader(resource.openStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine())!=null){
                    System.out.println("SPI Line: " + line);
                    String[] strArray = line.split("=");
                    if(strArray.length>1){
                        String key = strArray[0];
                        String className = strArray[1];
                        keyClassMap.put(key,Class.forName(className));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        loaderMap.put(loadClass.getName(),keyClassMap);
        return keyClassMap;
    }

    public static <T> T getInstance(Class<?> tClass, String key) {
        String tClassName = tClass.getName();
        Map<String, Class<?>> keyClassMap = loaderMap.get(tClassName);
        if (keyClassMap == null) {
            throw new RuntimeException(String.format("SpiLoader 未加载 %s 类型", tClassName));
        }
        if (!keyClassMap.containsKey(key)) {
            throw new RuntimeException(String.format("SpiLoader 的 %s 不存在 key=%s 的类型", tClassName, key));
        }
        // 获取到要加载的实现类型
        Class<?> implClass = keyClassMap.get(key);
        // 从实例缓存中加载指定类型的实例
        String implClassName = implClass.getName();
        if (!instanceCache.containsKey(implClassName)) {
            try {
                instanceCache.put(implClassName, implClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                String errorMsg = String.format("%s 类实例化失败", implClassName);
                throw new RuntimeException(errorMsg, e);
            }
        }
        return (T) instanceCache.get(implClassName);
    }

}

