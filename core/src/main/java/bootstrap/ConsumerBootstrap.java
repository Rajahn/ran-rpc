package bootstrap;

import app.RpcApplication;

public class ConsumerBootstrap {
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }

}
