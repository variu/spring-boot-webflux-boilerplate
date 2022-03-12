package sample.entity;

import common.FixtureMonkeyUtils;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import sample.dto.SampleCreatable;

public class SampleTest {
  @RepeatedTest(10)
  void create() {
    SampleCreatable creatable =
        FixtureMonkeyUtils.monkey()
            .giveMeBuilder(SampleCreatable.class)
            .set("sampleName", Arbitraries.strings().ofMaxLength(255))
            .sample();
    Sample entity = Sample.builder().creatable(creatable).build();

    Assertions.assertNotNull(entity);
    Assertions.assertEquals(creatable.getSampleName(), entity.getSampleName());
  }
}
