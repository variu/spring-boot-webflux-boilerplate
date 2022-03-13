package info.jakepark.sample.utils;

import info.jakepark.common.FixtureMonkeyUtils;
import info.jakepark.sample.entity.Sample;
import net.jqwik.api.Arbitraries;

public abstract class SampleTestData {

  public static Sample getEntity() {
    return FixtureMonkeyUtils.monkey()
        .giveMeBuilder(Sample.class)
        .set("sampleName", Arbitraries.strings().alpha().ofMaxLength(255))
        .setNull("id")
        .setNull("createdAt")
        .setNull("createdBy")
        .setNull("modifiedAt")
        .setNull("modifiedBy")
        .sample();
  }
}
