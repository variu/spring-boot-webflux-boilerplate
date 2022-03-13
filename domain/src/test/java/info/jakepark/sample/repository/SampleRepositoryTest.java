package info.jakepark.sample.repository;

import info.jakepark.config.R2dbcAuditingConfig;
import info.jakepark.sample.entity.Sample;
import info.jakepark.sample.utils.SampleTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(R2dbcAuditingConfig.class)
public class SampleRepositoryTest {

  @Autowired private SampleRepository sampleRepository;

  @Test
  public void create() {
    Sample target = SampleTestData.getEntity();
    Sample entity = sampleRepository.save(target).block();

    Assertions.assertNotNull(entity);
    Assertions.assertNotNull(entity.getSampleName());
  }
}
