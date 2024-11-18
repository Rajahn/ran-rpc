package ranhuo.vt.edu.ranrpcspringbootstarter.bootstrap;

import app.RpcApplication;
import config.RpcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import ranhuo.vt.edu.ranrpcspringbootstarter.annotation.EnableRpc;
import server.tcp.VertxTcpServer;

public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {

    private static final Logger log = LoggerFactory.getLogger(RpcInitBootstrap.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){

        //获取enablerpc注解的属性值
        boolean needServer = (boolean) importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName()).get("needServer");

        RpcApplication.init();

        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        if(needServer){
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        }else {
            log.info("无启动注解, 不启动 server");
        }

    }
}
