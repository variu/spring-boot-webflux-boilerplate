package info.jakepark.business.service;

import info.jakepark.business.sample.service.SampleService;
import info.jakepark.common.FixtureMonkeyUtils;
import info.jakepark.sample.dto.SampleCreatable;
import info.jakepark.sample.dto.SampleDto;
import info.jakepark.sample.dto.SampleUpdatable;
import info.jakepark.sample.entity.Sample;
import info.jakepark.sample.utils.SampleTestData;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleServiceTest {
  @Autowired private SampleService service;
  private Sample sample = null;

  @BeforeAll
  void before() {}

  @Test
  void test01_create() {
    SampleCreatable creatable = SampleTestData.getCreatable();
    sample = service.create(creatable).block();

    Assertions.assertNotNull(sample);
    Assertions.assertEquals(creatable.getSampleName(), sample.getSampleName());
  }

  @Test
  void test02_update() {
    SampleUpdatable updatable =
        FixtureMonkeyUtils.monkey()
            .giveMeBuilder(SampleDto.UpdateDto.class)
            .set("id", sample.getId())
            .set("sampleName", Arbitraries.strings().alpha().ofMinLength(1).ofMaxLength(255))
            .sample();
    Sample updatedSample = service.update(updatable).block();

    Assertions.assertNotNull(updatedSample);
    Assertions.assertEquals(updatable.getSampleName(), updatedSample.getSampleName());
  }

  @Test
  void test03_findAll() {
    List<Sample> list = service.findAll().collectList().block();
    Assertions.assertNotNull(list);
    Assertions.assertTrue(list.size() > 0);
  }

  @Test
  void test04_findAllById() {
    service
        .findById(sample.getId())
        .as(StepVerifier::create)
        .consumeNextWith(
            result -> {
              Assertions.assertAll(
                  () -> Assertions.assertNotNull(result),
                  () -> Assertions.assertNotNull(result.getSampleName()));
            })
        .verifyComplete();
  }

  @Test
  void test05_delete() {
    service.delete(sample.getId()).block();

    Sample entity = service.findById(sample.getId()).block();
    Assertions.assertNull(entity);
  }
}
