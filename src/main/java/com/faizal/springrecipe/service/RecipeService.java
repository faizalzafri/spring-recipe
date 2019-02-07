package com.faizal.springrecipe.service;

import java.util.Set;

import com.faizal.springrecipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe getRecipeById(Long id);
}
