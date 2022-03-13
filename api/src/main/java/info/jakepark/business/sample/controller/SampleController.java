package info.jakepark.business.sample.controller;

import info.jakepark.business.sample.service.SampleService;
import info.jakepark.sample.dto.SampleDto;
import info.jakepark.sample.entity.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/sample")
@RestController
public class SampleController {
  private final SampleService sampleService;

  @GetMapping
  public Flux<Sample> findAll() {
    return sampleService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Sample> findById(@PathVariable Long id) {
    return sampleService.findById(id);
  }

  @PostMapping
  public Mono<Sample> create(@Valid @RequestBody SampleDto.CreateDto creatable) {
    return sampleService.create(creatable);
  }

  @PutMapping
  public Mono<Sample> update(@Valid @RequestBody SampleDto.UpdateDto updatable) {
    return sampleService.update(updatable);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable Long id) {
    return sampleService.delete(id);
  }
}
