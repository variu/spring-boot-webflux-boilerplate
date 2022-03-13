package info.jakepark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;

@Configuration(proxyBeanMethods = false)
@EnableR2dbcAuditing
public class R2dbcAuditingConfig {
  @Bean
  ReactiveAuditorAware<String> auditorAware() {
    return () -> Mono.just("system");
  }
}
