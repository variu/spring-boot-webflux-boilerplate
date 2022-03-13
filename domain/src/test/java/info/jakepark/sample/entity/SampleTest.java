package info.jakepark.sample.entity;

import info.jakepark.common.FixtureMonkeyUtils;
import info.jakepark.sample.dto.SampleCreatable;
import info.jakepark.sample.dto.SampleUpdatable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

public class SampleTest {
  @RepeatedTest(10)
  void create() {
    SampleCreatable creatable = FixtureMonkeyUtils.monkey().giveMeOne(SampleCreatable.class);
    Sample entity = Sample.builder().creatable(creatable).build();

    Assertions.assertNotNull(entity);
    Assertions.assertEquals(creatable.getSampleName(), entity.getSampleName());
  }

  @RepeatedTest(10)
  void update() {
    SampleCreatable creatable = FixtureMonkeyUtils.monkey().giveMeOne(SampleCreatable.class);
    Sample entity = Sample.builder().creatable(creatable).build();

    SampleUpdatable updatable = FixtureMonkeyUtils.monkey().giveMeOne(SampleUpdatable.class);
    Sample updatedEntity = entity.update(updatable);

    Assertions.assertNotNull(updatedEntity);
    Assertions.assertEquals(updatable.getSampleName(), updatedEntity.getSampleName());
  }
}
