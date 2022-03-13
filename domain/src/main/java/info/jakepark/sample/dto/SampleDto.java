package info.jakepark.sample.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class SampleDto {

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class CreateDto implements SampleCreatable {
    @NotBlank
    @Size(min = 1, max = 255)
    private String sampleName;

    @Builder
    private CreateDto(String sampleName) {
      this.sampleName = sampleName;
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class UpdateDto implements SampleUpdatable {
    @NotNull private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String sampleName;

    @Builder
    private UpdateDto(Long id, String sampleName) {
      this.id = id;
      this.sampleName = sampleName;
    }
  }
}
