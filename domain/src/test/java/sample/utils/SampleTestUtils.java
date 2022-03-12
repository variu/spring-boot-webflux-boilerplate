package sample.utils;

import common.FixtureMonkeyUtils;
import net.jqwik.api.Arbitraries;
import sample.entity.Sample;

public abstract class SampleTestUtils {

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
