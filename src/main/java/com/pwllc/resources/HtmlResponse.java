package com.pwllc.resources;

import com.pwllc.application.cfg.TestConfig;
import com.pwllc.util.FileUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// the default for this site it HTML
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HtmlResponse {

	private String response;

    public HtmlResponse(TestConfig cfg) {

        try {
            response = FileUtils.getResource(cfg.getDefaultHtmlResourceLocation());
        }
        catch (Exception e) {
            //logger.error("unable to load flow point resources", e);
        }
    }

    @GET
    public String getDefaultResponse() {
        return response;
    }

    @GET
    @Path("index.html")
    public String getDefaultResponseAsIndex() {
        return response;
    }

    @GET
    @Path("help")
    public String getHelpResponse() {
        return "response: use api/ to access the REST interface";
    }


}