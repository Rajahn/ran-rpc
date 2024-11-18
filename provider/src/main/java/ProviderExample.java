import app.RpcApplication;
import bootstrap.ProviderBootstrap;
import common.service.UserService;
import config.RegistryConfig;
import config.RpcConfig;
import constant.RpcConstant;
import model.ServiceMetaInfo;
import model.ServiceRegisterInfo;
import registry.LocalRegistry;
import registry.Registry;
import registry.RegistryFactory;
import server.HttpServer;
import server.VertxHttpServer;
import server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

public class ProviderExample {

    public static void main(String[] args) {

        // 要注册的服务
        List<ServiceRegisterInfo> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
