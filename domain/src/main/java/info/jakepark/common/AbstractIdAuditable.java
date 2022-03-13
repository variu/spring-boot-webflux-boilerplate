package info.jakepark.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
public abstract class AbstractIdAuditable extends AbstractAuditable {
  @Min(0)
  @Id
  protected Long id;
}
