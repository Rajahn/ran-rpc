package config;

import lombok.Data;
import serializer.SerializerKeys;

@Data
public class RpcConfig {

    private String name = "ran-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8081;

    //当前进行的是模拟调用
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();

}
