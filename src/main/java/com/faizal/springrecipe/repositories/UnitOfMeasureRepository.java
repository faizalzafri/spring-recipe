package com.faizal.springrecipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.faizal.springrecipe.domain.Category;
import com.faizal.springrecipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String unitOfMeasure);
}
