# microservices project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

# Microservices Patterns

## Externalize Configuration

```java
@ConfigProperty(name = "config", defaultValue = "world")
String config;
```

We can use application.properties files or pass through enviroment variable

```shell
config=Quarkus

./mvnw compile quarkus:dev -Dconfig=external
```

## Circuit Break

```java
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
```

## Health Check

1. Liveness
2. Readness

## Distributed Trace

Just by annotating resource with `@Traced` it will collect and me available to trancing application (like Zipkin or Jaeger).

### Jaeger example

> Run Jaeger on Docker (http://localhost:16686/)

```sql
docker run -it --name jaeger \
  -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.20
```

## Metrics

Metrics collected by microprofile
> http://localhost:8080/metrics/base

Metrics collected by Smallrye
> http://localhost:8080/metrics/vendor

Metrics collected by my application
> http://localhost:8080/metrics/application

# Command Mode

Guide: https://quarkus.io/guides/command-mode-reference


## References

https://www.youtube.com/watch?v=DOKscFylOwY