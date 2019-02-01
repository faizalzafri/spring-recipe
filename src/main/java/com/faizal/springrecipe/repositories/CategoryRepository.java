package com.faizal.springrecipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.faizal.springrecipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
