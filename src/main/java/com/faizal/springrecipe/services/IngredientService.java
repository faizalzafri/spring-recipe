package com.faizal.springrecipe.services;

import com.faizal.springrecipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

	IngredientCommand save(IngredientCommand ingredientCommand);

	void deleteById(Long recipeId, Long id);
}
