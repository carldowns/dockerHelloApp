package com.pwllc.resources;

import com.pwllc.application.cfg.TestConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class RestResponse {
	
	private String response;
	
    public RestResponse(TestConfig cfg) {
    	this.response = cfg.getResponse();
    }

    @GET
    public String get() {
        return response;
    }

    @GET
    @Path("mongoHost")
    public String getEnv() {
        return "mongoHost:" + System.getProperty("dbHost") + ":" + System.getProperty("dbPort");
    }
}