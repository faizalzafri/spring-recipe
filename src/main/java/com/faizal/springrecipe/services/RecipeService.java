package com.faizal.springrecipe.services;

import java.util.Set;

import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> findAll();
	
	Recipe findById(Long id);

	RecipeCommand findCommandById(Long id);
		
	RecipeCommand save(RecipeCommand command);
	
	void deleteById(Long id);
}
