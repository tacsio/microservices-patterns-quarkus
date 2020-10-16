package io.tacsio.distributedtrace;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;

import io.netty.util.internal.ThreadLocalRandom;

@Traced
@Path("/traced")
public class TracedResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public double price() {
    return ThreadLocalRandom.current().nextDouble(40.1, 74.23);
  }

}
