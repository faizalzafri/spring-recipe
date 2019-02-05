package com.faizal.springrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizal.springrecipe.commands.IngredientCommand;
import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.services.IngredientService;
import com.faizal.springrecipe.services.RecipeService;
import com.faizal.springrecipe.services.UnitOfMeasureService;

@Controller
public class IngredientController {

	RecipeService recipeService;

	IngredientService ingredientService;

	UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
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

	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateRecipeIngredient(@PathVariable("recipeId") String recipeId,
			@PathVariable("ingredientId") String ingredientId, Model model) {

		model.addAttribute("ingredient",
				ingredientService.getByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

		return "recipe/ingredient/ingredientform";
	}

	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}

}
