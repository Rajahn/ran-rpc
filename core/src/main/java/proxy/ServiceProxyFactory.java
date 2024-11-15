package proxy;

import app.RpcApplication;

import java.lang.reflect.Proxy;

/**
 * 创建代理对象的服务代理工厂
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass) {
        if(RpcApplication.getRpcConfig().isMock()){
            return getMockProxy(serviceClass);
        }

        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }
}
