package sample.entity;

import common.AbstractIdAuditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table("sample")
public class Sample extends AbstractIdAuditable {

  @NotBlank
  @Length(min = 1, max = 255)
  private String sampleName;

  @Builder
  private Sample(String sampleName) {
    this.sampleName = sampleName;
  }
}
