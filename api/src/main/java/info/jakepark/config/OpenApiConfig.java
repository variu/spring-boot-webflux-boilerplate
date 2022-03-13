package info.jakepark.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi apiV1() {
    return GroupedOpenApi.builder()
        .group("api.v1")
        .pathsToMatch("/api/v1/**")
        .addOpenApiCustomiser(openApiCustomiser())
        .build();
  }

  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi -> openApi.info(new Info().title("Jake Park`s API").version("v1"));
  }
}
