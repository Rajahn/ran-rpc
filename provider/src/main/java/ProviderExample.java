import app.RpcApplication;
import common.service.UserService;
import config.RegistryConfig;
import config.RpcConfig;
import constant.RpcConstant;
import model.ServiceMetaInfo;
import registry.LocalRegistry;
import registry.Registry;
import registry.RegistryFactory;
import server.HttpServer;
import server.VertxHttpServer;
import server.tcp.VertxTcpServer;

public class ProviderExample {

    public static void main(String[] args) {

        RpcApplication.init();

        //向本地服务注册器, 注册一个服务提供者
        String serviceName = UserService.class.getName();
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


       // HttpServer server = new VertxHttpServer();
        VertxTcpServer server = new VertxTcpServer();
        server.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
