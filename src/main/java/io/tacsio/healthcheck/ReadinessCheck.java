package io.tacsio.healthcheck;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class ReadinessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

    Client client = ClientBuilder.newClient();
    boolean ready = false;
    try {

      Response response = client.target("https://www.google.com").request().get();
      ready = (response.getStatus() == Status.OK.getStatusCode());
    } catch (Exception e) {
      ready = false;
    }

    if (ready) {
      return HealthCheckResponse.up("I'm ready to respond");
    } else {
      return HealthCheckResponse.down("I'm not ready to respond");
    }
  }

}
