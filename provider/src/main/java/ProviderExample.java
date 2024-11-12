import app.RpcApplication;
import common.service.UserService;
import registry.LocalRegistry;
import server.HttpServer;
import server.VertxHttpServer;

public class ProviderExample {

    public static void main(String[] args) {

        RpcApplication.init();

        //向本地服务注册器, 注册一个服务提供者
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        HttpServer server = new VertxHttpServer();
        server.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
