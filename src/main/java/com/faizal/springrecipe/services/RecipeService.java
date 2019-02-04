package com.faizal.springrecipe.services;

import java.util.Set;

import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe getRecipeById(Long id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
}
