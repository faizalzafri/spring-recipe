package com.faizal.springrecipe.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizal.springrecipe.commands.IngredientCommand;
import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.services.IngredientService;
import com.faizal.springrecipe.services.RecipeService;

@Controller
public class IngredientController {

	RecipeService recipeService;

	IngredientService ingredientService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredient(@PathVariable String id, Model model) {

		RecipeCommand recipeCommand = recipeService.getRecipeCommandById(new Long(id));
		model.addAttribute("recipe", recipeCommand);

		return "recipe/ingredient/list";
	}

	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String showIngredient(@PathVariable("recipeId") String recipeId,
			@PathVariable("ingredientId") String ingredientId, Model model) {

		IngredientCommand ingredientCommand = ingredientService.getByRecipeIdAndIngredientId(new Long(recipeId),
				new Long(ingredientId));
		model.addAttribute("ingredient", ingredientCommand);

		return "recipe/ingredient/show";
	}

}
