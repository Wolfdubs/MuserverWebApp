package org.example;

import io.muserver.*;

import static io.muserver.MuServerBuilder.muServer;

public class Main {
    public static void main(String[] args) {
        MuServer muServer = muServer().withHttpsPort(9443)
                .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                    response.write("MuServer started");
                })
                .addHandler(Method.GET, "/login", (request, response, pathParams) -> {
                    response.write("Login Page");
                })
                .addHandler(Method.GET, "/number/{id:[0-9]+}", (request, response, pathParams) -> {
                    int id = Integer.parseInt(pathParams.get("id"));
                    response.write("The ID = " + id);
                })
                .addHandler(Method.GET, "/user/{name}", (request, response, pathParams) -> {
                    String name = pathParams.get("name");
                    response.write("The name is: " + name);
                })
                .start();
        System.out.println("Started server at " + muServer.uri());
    }
}

