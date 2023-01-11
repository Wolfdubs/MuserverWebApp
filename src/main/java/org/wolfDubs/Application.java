package org.wolfDubs;

import io.muserver.*;
import io.muserver.rest.RestHandlerBuilder;
import org.wolfDubs.model.getAmmo;
import org.wolfDubs.model.Default;
import org.wolfDubs.model.Health;
import org.wolfDubs.model.Login;

import java.util.Date;
import java.util.Map;

import static io.muserver.MuServerBuilder.muServer;

public class Application {
    public static void main(String[] args) {
        MuServer muServer = muServer().withHttpsPort(9443)
                .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                    response.contentType("text/html");
                    response.write("<h1>Welcome to the home page</h1>");
                })
                .addHandler(Method.GET, "/number/{id:[0-9]+}", (request, response, pathParams) -> {
                    int id = Integer.parseInt(pathParams.get("id"));
                    response.write("The ID = " + id);
                })
                .addHandler(Method.GET, "/user/{name}", (request, response, pathParams) -> {
                    String name = pathParams.get("name");
                    response.write("The name is: " + name);
                })
                .addHandler(Method.GET, "/hello", (request, response, pathParams) -> {
                    Map<String, String> headers = Map.of("Content-Type", "application/json");
                    response.headers();
                    response.write("{\"message\":\"Hello, World!\"}");
                })
                .addHandler((req, resp) -> {
                    String path = req.uri().getPath();
                    if (path.equals("/handle-it") && req.method() == Method.GET) {
                        resp.contentType("text/html");
                        resp.write("This was handled by the <b>handle-it</b> handler");
                        return true;
                    } else {
                        return false;
                    }
                })
                .addHandler(WebSocketHandlerBuilder.webSocketHandler()
                        .withPath("/web-socket")
                        .withWebSocketFactory(new MuWebSocketFactory() {
                            @Override
                            public MuWebSocket create(MuRequest muRequest, Headers headers) throws Exception {
                                return new BaseWebSocket() {
                                    @Override
                                    public void onText(String message, boolean isLast, DoneCallback onComplete) throws Exception {
                                        session().sendText("Received " + message, onComplete);
                                    }
                                };
                            }
                }))
                .addHandler(RestHandlerBuilder.restHandler(new getAmmo()))
                .addHandler(RestHandlerBuilder.restHandler(new Health()))
                .addHandler(new Login()) //still not being hit properly, so still acting as a default
                .addHandler(new Default())   //will catch any request that doesn't hit the ones above
                .start();
        String date = Mutils.toHttpDate(new Date());   //Mutils is a muserver class with utility methods
        System.out.println("Started server at " + muServer.uri() + " at " + date);
    }
}

/*
https://muserver.io
Mu-Server is a modern, lightweight web server library for Java.
An HTTP server is created by creating a server builder, such as MuServerBuilder.httpsServer() and then calling MuServerBuilder.start().
Handlers are added to the builder with the MuServerBuilder.addHandler(io.muserver.MuHandler) or MuServerBuilder.addHandler(io.muserver.Method, java.lang.String, io.muserver.RouteHandler) methods.
Rest resources can be created by using the RestHandlerBuilder.restHandler(java.lang.Object...) builder.

For each endpoint, a lambda function is provided that generates the appropriate response.
The response can be written via headers, and message body.
 */
