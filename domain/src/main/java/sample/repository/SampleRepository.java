package sample.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import sample.entity.Sample;

public interface SampleRepository extends R2dbcRepository<Sample, Long> {}
