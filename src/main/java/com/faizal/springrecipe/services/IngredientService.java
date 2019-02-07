package com.faizal.springrecipe.services;

import com.faizal.springrecipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long id);

	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

	void deleteById(Long recipeId, Long id);
}
