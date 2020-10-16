package io.tacsio.circuitbreaker;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import io.netty.util.internal.ThreadLocalRandom;

@Path("/circuit")
public class CircuitResource {

	@Timeout(unit = ChronoUnit.MILLIS, value = 500)
	@Fallback(fallbackMethod = "fallback")
	@CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 2000, successThreshold = 2)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCircuit() throws InterruptedException {
		long delay = ThreadLocalRandom.current().nextLong(100L, 1000L);
		TimeUnit.MILLISECONDS.sleep(delay);

		return "Circuit Closed";
	}

	private String fallback() {
		return "Fallback response";
	}
}
