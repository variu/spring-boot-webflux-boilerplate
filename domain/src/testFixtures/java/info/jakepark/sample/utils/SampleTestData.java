package info.jakepark.sample.utils;

import info.jakepark.common.FixtureMonkeyUtils;
import info.jakepark.sample.dto.SampleCreatable;
import info.jakepark.sample.dto.SampleDto;
import info.jakepark.sample.entity.Sample;
import net.jqwik.api.Arbitraries;

public abstract class SampleTestData {
  public static SampleCreatable getCreatable() {
    return FixtureMonkeyUtils.monkey()
        .giveMeBuilder(SampleDto.CreateDto.class)
        .set("sampleName", Arbitraries.strings().alpha().ofMinLength(1).ofMaxLength(255))
        .sample();
  }

  public static Sample getEntity() {
    return FixtureMonkeyUtils.monkey()
        .giveMeBuilder(Sample.class)
        .set("sampleName", Arbitraries.strings().alpha().ofMinLength(1).ofMaxLength(255))
        .setNull("id")
        .setNull("createdAt")
        .setNull("createdBy")
        .setNull("modifiedAt")
        .setNull("modifiedBy")
        .sample();
  }
}
