package com.micro.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator BANKRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/immo/microtest1/**")
						.filters(f -> f.rewritePath("/immo/microtest1/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())

								/*.retry(retryConfig -> retryConfig.setRetries(5).setMethods(HttpMethod.GET).setBackoff(Duration.ofMillis(600),Duration.ofMillis(1000),2,true)
								)*/

								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(userKeyResolver()))

								.circuitBreaker(config -> config.setName("microtest1CircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
								.uri("lb://MICROTEST1"))

				.route(p -> p.path("/immo/microtest2/**")
						.filters(f -> f.rewritePath("/immo/microtest2/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(userKeyResolver()))
								.retry(retryConfig -> retryConfig.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
						.uri("lb://MICROTEST2"))
				.build();
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver ipKeyResolver() {
		return exchange -> {

			String ipAddress = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");


			if (ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = exchange.getRequest().getRemoteAddress() != null ?
						exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() : "unknown";
			}


			return Mono.just(ipAddress);
		};
	}

}
