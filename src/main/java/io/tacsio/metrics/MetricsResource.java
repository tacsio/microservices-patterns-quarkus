package io.tacsio.metrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

//! metrics path is already used project metrics endpoints
@Path("custom-metrics")
public class MetricsResource {

  @Path("counted")
  @Counted
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    return "Counted";
  }

  @Path("timed")
  @Timed
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getTimed() {
    return "Timed";
  }
}
