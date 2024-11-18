package ranhuo.vt.edu.ranrpcspringbootstarter.annotation;

import org.springframework.context.annotation.Import;
import ranhuo.vt.edu.ranrpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import ranhuo.vt.edu.ranrpcspringbootstarter.bootstrap.RpcInitBootstrap;
import ranhuo.vt.edu.ranrpcspringbootstarter.bootstrap.RpcProviderBootstrap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {
    boolean needServer() default true;
}
