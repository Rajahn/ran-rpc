package config;

import fault.retry.RetryStrategyKeys;
import fault.tolerant.TolerantStrategyKeys;
import loadbalancer.LoadBalancerKeys;
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

    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    private String retryStrategy = RetryStrategyKeys.NO;

    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;

}
