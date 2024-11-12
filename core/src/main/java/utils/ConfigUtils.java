package utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 读取配置文件并返回配置对象的工具类
 * 工具类应该尽量通用, 和业务解绑, 比如支持外层传入要读取的配置前缀
 */
public class ConfigUtils {
    public static <T> T loadConfig(Class<T> tClass, String prefix){
        return loadConfig(tClass,prefix,"");
    }

    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment){
        StringBuilder configBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(environment)){
            configBuilder.append("-").append(environment);
        }
        configBuilder.append(".properties");
        Props props = new Props(configBuilder.toString());
        return props.toBean(tClass,prefix);
    }

}
