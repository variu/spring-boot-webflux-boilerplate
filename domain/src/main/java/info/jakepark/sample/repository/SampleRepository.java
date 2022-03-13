package info.jakepark.sample.repository;

import info.jakepark.sample.entity.Sample;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SampleRepository extends R2dbcRepository<Sample, Long> {}
