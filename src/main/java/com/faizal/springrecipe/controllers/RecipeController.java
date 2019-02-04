package com.faizal.springrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.service.RecipeService;

@Controller
public class RecipeController {

	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String getRecipe(@PathVariable("id") String id, Model model) {
		Recipe recipe = recipeService.getRecipeById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "recipe/show";
	}

}
