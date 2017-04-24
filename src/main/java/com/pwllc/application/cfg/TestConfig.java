package com.pwllc.application.cfg;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/*
 *  configuration read from dropwizard yaml configuration file
 */
public class TestConfig extends Configuration {
	
	@NotEmpty
	private String response;

    @NotEmpty
    private String defaultHtmlResourceLocation;

	@JsonProperty
	public String getResponse() {
		return response;
	}
	
	@JsonProperty
	public void setResponse(String response) {
		this.response = response;
	}

    @JsonProperty
    public String getDefaultHtmlResourceLocation() {
        return defaultHtmlResourceLocation;
    }

    @JsonProperty
    public void setDefaultHtmlResourceLocation(String defaultHtmlResourceLocation) {
        this.defaultHtmlResourceLocation = defaultHtmlResourceLocation;
    }
}