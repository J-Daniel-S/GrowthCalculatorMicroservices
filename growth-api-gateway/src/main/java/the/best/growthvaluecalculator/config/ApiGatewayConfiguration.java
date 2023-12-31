package the.best.growthvaluecalculator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApiGatewayConfiguration {
	
	@Autowired
	public Environment env;

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
				.uri("http://httpbin.org:80"))
				.route(p -> p
					.host("*.circuitbreak.com")
					.filters(f -> f.circuitBreaker(config -> config.setName("jabroni")))
					.uri("http://httpbin.org:80"))
				.route(r -> r.path("/fixed-asset-provider/**")
						.filters(f -> f.rewritePath(
								"/fixed-asset-provider(?<segment>.*)", 
								"/fixed${segment}"))
						.uri("lb://fixed-asset-provider"))
				.route(r -> r.path("/stock-provider/**")
						.filters(f -> f.rewritePath(
								"/stock-provider(?<segment>.*)", 
								"/stock${segment}"))
						.uri("lb://stock-provider"))
				.build();
	
	}
	
}
