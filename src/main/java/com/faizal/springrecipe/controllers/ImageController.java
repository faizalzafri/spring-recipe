package com.faizal.springrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.faizal.springrecipe.services.ImageService;
import com.faizal.springrecipe.services.RecipeService;

@Controller
public class ImageController {

	private ImageService imageService;
	private RecipeService recipeService;

	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable("id") String id, @RequestParam("imagefile") MultipartFile file) {

		imageService.saveImageFile(Long.valueOf(id), file);

		return "redirect:/recipe/" + id + "/show";

	}

	@GetMapping("/recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(new Long(id)));
		return "recipe/imageuploadform";
	}
}
