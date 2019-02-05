package com.faizal.springrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.services.RecipeService;

@Controller
public class IngredientController {

	RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String showIngredient(@PathVariable String id, Model model) {

		RecipeCommand recipeCommand = recipeService.getRecipeCommandById(new Long(id));
		model.addAttribute("recipe", recipeCommand);

		return "recipe/ingredient/list";
	}

}
