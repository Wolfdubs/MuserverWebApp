package org.wolfDubs.model;

import io.muserver.*;

public class Default implements MuHandler {

    @Override
    public boolean handle(MuRequest muRequest, MuResponse muResponse) {
        System.out.println("Default handler hit: Received request: " + muRequest + " from " + muRequest.remoteAddress());
        muResponse.write("Default Page");
        return false;
    }
}
