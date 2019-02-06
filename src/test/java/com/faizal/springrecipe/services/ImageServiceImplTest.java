package com.faizal.springrecipe.services;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faizal.springrecipe.controllers.ImageController;

public class ImageServiceImplTest {/*

	@Mock
	ImageService imageService;

	ImageController imageController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(imageService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
	}

	@Test
	public void testHandleImagePost() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("file", "texting.txt", "text/plain", "testFile2".getBytes());
		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile ))
				.andExpect(status().isFound())
				.andExpect(header().string("Location", "/"));
	}

*/}
