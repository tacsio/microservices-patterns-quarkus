package io.tacsio.externalconfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/config")
public class ConfigResource {

	@ConfigProperty(name = "config", defaultValue = "world")
	String config;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getConfig() {
		return "Hello, " + config;
	}
}
