package com.faizal.springrecipe.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.faizal.springrecipe.commands.IngredientCommand;
import com.faizal.springrecipe.converters.IngredientCommandToIngredient;
import com.faizal.springrecipe.converters.IngredientToIngredientCommand;
import com.faizal.springrecipe.domain.Ingredient;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;
import com.faizal.springrecipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private IngredientToIngredientCommand irCommand;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand irCommand,
			UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.irCommand = irCommand;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ing -> ing.getId().equals(ingredientId)).map(irCommand::convert).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			throw new RuntimeException("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

		if (!recipeOptional.isPresent()) {
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ing -> ing.getId().equals(ingredientCommand.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient foundIngredient = ingredientOptional.get();
				foundIngredient.setDescription(ingredientCommand.getDescription());
				foundIngredient.setAmount(ingredientCommand.getAmount());
				foundIngredient.setUom(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure().getId())
						.orElseThrow(() -> new RuntimeException("No UOM")));

			} else {
				recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);
			return irCommand.convert(savedRecipe.getIngredients().stream()
					.filter(ing -> ing.getId().equals(ingredientCommand.getId())).findFirst().get());
		}
	}

}