package info.jakepark.business.controller;

import info.jakepark.common.WebTestClientUtils;
import info.jakepark.sample.entity.Sample;
import info.jakepark.sample.repository.SampleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest extends WebTestClientUtils {
  @Autowired
  private SampleRepository sampleRepository;

  private Sample sample = null;

  @Test
  void test01_create() {
    String sampleName = "jakeSample";
    String json = """
        {
          "sampleName": "%s"
        }
        """.formatted(sampleName);
    sample = this.post()
        .uri("/api/v1/sample").body(BodyInserters.fromValue(json)).getResponseBody(new ParameterizedTypeReference<>() {});

    Assertions.assertNotNull(sample);
    Assertions.assertEquals(sampleName, sample.getSampleName());
  }

  @Test
  void test02_update() {
    String sampleName = "jakeSampleUpdated";
    String json = """
        {
          "id": %d,
          "sampleName": "%s"
        }
        """.formatted(sample.getId(), sampleName);
    sample = this.put()
        .uri("/api/v1/sample").body(BodyInserters.fromValue(json)).getResponseBody(new ParameterizedTypeReference<>() {});

    Assertions.assertNotNull(sample);
    Assertions.assertEquals(sampleName, sample.getSampleName());
  }

  @Test
  void test03_findAll() {
    List<Sample> response =
        this.get()
            .uri(uriBuilder -> uriBuilder.path("/api/v1/sample").build())
            .getResponseBody(new ParameterizedTypeReference<>() {});

    Assertions.assertNotNull(response);
  }

  @Test
  void test04_findById() {
    Sample response =
        this.get()
            .uri("/api/v1/sample/{id}", sample.getId())
            .getResponseBody(new ParameterizedTypeReference<>() {});

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getSampleName());
  }

  @Test
  void test05_delete() {
    this.delete()
        .uri("/api/v1/sample/{id}", sample.getId())
        .getResponseOk();

    Sample entity = sampleRepository.findById(sample.getId()).block();
    Assertions.assertNull(entity);
  }
}
