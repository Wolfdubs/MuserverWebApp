package org.wolfDubs.model;

import io.muserver.rest.Description;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/health")
@Description("healthpoint")
public class Health {
    JSONObject all = new JSONObject().put("Name", "Womble")
            .put("Species","Dog")
            .put("Breed", "Pekingese")
            .put("Birth", "2009")
            .put("Gender", "Male")
            .put("Weight", "10lbs");

    @GET
    @Produces("application/json")
    public String getHealthInfo() {return all.toString(5);}

}
