package com.faizal.springrecipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.faizal.springrecipe.commands.IngredientCommand;
import com.faizal.springrecipe.converters.IngredientToIngredientCommand;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private IngredientToIngredientCommand irCommand;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand irCommand) {
		this.recipeRepository = recipeRepository;
		this.irCommand = irCommand;

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

	/*
	 * 
	 * private final RecipeRepository recipeRepository; private final
	 * RecipeCommandToRecipe recipeCommandToRecipe; private final
	 * RecipeToRecipeCommand recipeToRecipeCommand;
	 * 
	 * public IngredientServiceImpl(RecipeRepository recipeRepository,
	 * RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand
	 * recipeToRecipeCommand) { this.recipeRepository = recipeRepository;
	 * this.recipeCommandToRecipe = recipeCommandToRecipe;
	 * this.recipeToRecipeCommand = recipeToRecipeCommand; }
	 * 
	 * @Override public Set<Recipe> getRecipes() { log.info("Executing getRecipes");
	 * 
	 * Set<Recipe> recipeSet = new HashSet<>();
	 * recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
	 * return recipeSet; }
	 * 
	 * @Override public Recipe getRecipeById(Long id) {
	 * 
	 * Optional<Recipe> optRec = recipeRepository.findById(id); if
	 * (optRec.isPresent()) return optRec.get(); else return null; }
	 * 
	 * @Override
	 * 
	 * @Transactional public RecipeCommand saveRecipeCommand(RecipeCommand command)
	 * { Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
	 * 
	 * Recipe savedRecipe = recipeRepository.save(detachedRecipe);
	 * log.debug("Saved RecipeId:" + savedRecipe.getId()); return
	 * recipeToRecipeCommand.convert(savedRecipe); }
	 * 
	 * @Override public RecipeCommand getRecipeCommandById(Long id) { return
	 * recipeToRecipeCommand.convert(getRecipeById(id)); }
	 * 
	 * @Override public void deleteById(Long id) { recipeRepository.deleteById(id);
	 * }
	 * 
	 */}