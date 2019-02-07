package com.faizal.springrecipe.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faizal.springrecipe.controllers.IndexController;
import com.faizal.springrecipe.controllers.RecipeController;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.service.RecipeService;

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

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
	}

}
