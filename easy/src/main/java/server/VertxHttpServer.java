package server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            System.out.println("Recv req: "+request.method()+" "+request.uri());
            request.response()
                    .putHeader("content-type","text/plain")
                    .end("Hello from Vertx HTTP server");
        });

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        server.listen(port, result -> {
            if(result.succeeded()){
                System.out.println("Server is now listen on port: "+port);
            }else {
                System.out.println("failed to start server :"+result.cause());
            }
        });

    }
}
