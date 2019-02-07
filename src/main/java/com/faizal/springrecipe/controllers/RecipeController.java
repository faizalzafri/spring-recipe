package com.faizal.springrecipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.exceptions.NotFoundException;
import com.faizal.springrecipe.services.RecipeService;

@Controller
public class RecipeController {

	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String getRecipe(@PathVariable("id") String id, Model model) {
		Recipe recipe = recipeService.getRecipeById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "recipe/show";
	}

	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@RequestMapping(value = "/recipe/save", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@RequestMapping(value = "/recipe/{id}/update")
	public String updateRecipe(@PathVariable("id") String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(new Long(id)));
		return "recipe/recipeform";
	}

	@RequestMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable("id") String id) {
		recipeService.deleteById(new Long(id));
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception.getMessage());
		
		return modelAndView;
	}
	
}
