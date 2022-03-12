package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties("spring.r2dbc")
public record DataBaseProperties(@NotBlank String url) {
}
