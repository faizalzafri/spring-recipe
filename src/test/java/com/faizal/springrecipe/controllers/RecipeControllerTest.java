package com.faizal.springrecipe.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faizal.springrecipe.commands.RecipeCommand;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetNewRecipe() throws Exception {
		mockMvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testPostNewRecipe() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

		mockMvc.perform(post("/recipe/save").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
				.param("description", "Some Description"))

				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/recipe/show/1"));
	}

	@Test
	public void testGetUpdateRecipe() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);

		when(recipeService.getRecipeCommandById(any())).thenReturn(recipeCommand);

		mockMvc.perform(get("/recipe/update/2")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(get("/recipe/delete/2")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

}
