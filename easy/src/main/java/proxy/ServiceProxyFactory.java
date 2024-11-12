package proxy;

import java.lang.reflect.Proxy;

/**
 * 创建代理对象的服务代理工厂
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());

    }
}
