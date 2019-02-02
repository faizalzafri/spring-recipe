package com.faizal.springrecipe.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.faizal.springrecipe.controllers.IndexController;
import com.faizal.springrecipe.service.RecipeService;

public class IndexControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	IndexController Controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		Controller = new IndexController(recipeService);

	}

	@Test
	public void getIndexPage() throws Exception {
		String viewName = Controller.getIndexPage(model);
		assertEquals("index", viewName);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute("recipes", new HashSet());
		// verify(model, times(1)).addAttribute(eq("recipes"), anySet());

	}
}
