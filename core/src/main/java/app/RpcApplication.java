package app;

import config.RegistryConfig;
import config.RpcConfig;
import constant.RpcConstant;
import lombok.extern.slf4j.Slf4j;
import registry.Registry;
import registry.RegistryFactory;
import utils.ConfigUtils;

/**
 * 相当于holder, 作为rpc框架的启动入口
 * 单例维护所有项目用到的全局变量
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newConfig){
        rpcConfig = newConfig;
        log.info("rpc load config = {}",newConfig.toString());

        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    public static void init(){
        RpcConfig newConfig;
        try {
            newConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFEX);
        }catch (Exception e){
            log.warn("load fail, use default config");;
            newConfig = new RpcConfig();
        }
        init(newConfig);
    }

    public static RpcConfig getRpcConfig(){
        if(rpcConfig==null){
            synchronized (RpcApplication.class){
                if(rpcConfig==null){
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
