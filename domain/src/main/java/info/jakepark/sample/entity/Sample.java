package info.jakepark.sample.entity;

import info.jakepark.common.AbstractIdAuditable;
import info.jakepark.sample.dto.SampleCreatable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table("sample")
public class Sample extends AbstractIdAuditable {

  @NotBlank
  @Size(min = 1, max = 255)
  private String sampleName;

  @Builder
  private Sample(SampleCreatable creatable) {
    this.sampleName = creatable.getSampleName();
  }
}
