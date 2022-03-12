package sample.repository;

import config.R2dbcAuditingConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import sample.entity.Sample;
import sample.utils.SampleTestUtils;

@DataR2dbcTest
@Import(R2dbcAuditingConfig.class)
public class SampleRepositoryTest {

  @Autowired private SampleRepository sampleRepository;

  @Test
  public void create() {
    Sample target = SampleTestUtils.getEntity();
    Sample entity = sampleRepository.save(target).block();

    Assertions.assertNotNull(entity);
    Assertions.assertNotNull(entity.getSampleName());
  }
}
