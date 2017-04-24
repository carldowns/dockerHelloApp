package com.pwllc.application;

import com.pwllc.resources.HtmlResponse;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.pwllc.application.cfg.TestConfig;
import com.pwllc.resources.RestResponse;

/**
 * The main Dropwizard Application class.
 */
public class TestApplication extends Application<TestConfig> {
	
	
    public static void main(String[] args) throws Exception {
    	
    	// instantiate and run this main Application class
    	
        new TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Docker Test App";
    }

    @Override
    public void initialize(Bootstrap<TestConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TestConfig configuration,
                    Environment environment) {
    	
    	// create and register REST resource endpoints
        environment.jersey().register(new RestResponse(configuration));
        environment.jersey().register(new HtmlResponse(configuration));
    }

}