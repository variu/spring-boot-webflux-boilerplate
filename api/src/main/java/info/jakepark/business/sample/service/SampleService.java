package info.jakepark.business.sample.service;

import info.jakepark.sample.dto.SampleCreatable;
import info.jakepark.sample.dto.SampleUpdatable;
import info.jakepark.sample.entity.Sample;
import info.jakepark.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
public class SampleService {
  private final SampleRepository sampleRepository;

  public Flux<Sample> findAll() {
    return sampleRepository.findAll();
  }

  public Mono<Sample> findById(Long id) {
    return sampleRepository.findById(id);
  }

  public Mono<Sample> create(SampleCreatable creatable) {
    return sampleRepository.save(Sample.builder().creatable(creatable).build());
  }

  public Mono<Sample> update(SampleUpdatable updatable) {
    return findById(updatable.getId())
        .switchIfEmpty(
            Mono.defer(
                () -> Mono.error(new IllegalArgumentException()))) // TODO: apply common error
        .flatMap(sample -> sampleRepository.save(sample.update(updatable)));
  }

  public Mono<Void> delete(Long id) {
    return sampleRepository.deleteById(id);
  }
}
