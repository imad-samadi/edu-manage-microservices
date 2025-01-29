package com.micro.gatewayserver;

import io.netty.channel.ChannelOption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;

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
				.route(p -> p.path("/school/students/**")
						.filters(f -> f.rewritePath("/school/students/(?<segment>.*)", "/students/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())


								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))

								.circuitBreaker(config -> config.setName("microtest1CircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
								.uri("lb://STUDENTSERVICE"))

				.route(p -> p.path("/school/teachers/**")
						.filters(f -> f.rewritePath("/school/teachers/(?<segment>.*)", "/teachers/${segment}")  // Rewriting path

								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.filter((exchange, chain) -> {
									System.out.println("Request received at: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								})
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
								)
						.uri("lb://TEACHERSERVICE"))

				.route(p -> p.path("/school/modules/**")
						.filters(f -> f.rewritePath("/school/modules/(?<segment>.*)", "/modules/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
								)
						.uri("lb://MODULESERVICE"))

				.route(p -> p.path("/school/enrollments/**")
						.filters(f -> f.rewritePath("/school/enrollments/(?<segment>.*)", "/enrollments/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
								)
						.uri("lb://ENROLLMENTSERVICE"))

				.route(p -> p.path("/school/auth2/**")
						.filters(f -> f.rewritePath("/school/auth2/(?<segment>.*)", "/api/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
						)
						.uri("lb://AUTHSERVER"))


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
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create()
				.responseTimeout(Duration.ofSeconds(60))  // Set response timeout to 10 seconds
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);  // Set connection timeout to 5 seconds
	}

}
