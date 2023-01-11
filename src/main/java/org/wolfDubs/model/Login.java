package org.wolfDubs.model;

import io.muserver.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Produces("text/html")
@Path("/login")
public class Login implements MuHandler {
    @Override
    public boolean handle(MuRequest muRequest, MuResponse muResponse){
        muResponse.write("Login Page");
        return false;  //so next handler is invoked
    }
}
