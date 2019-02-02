package com.faizal.springrecipe.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NotesTest {

	private Notes notes;

	@Before
	public void setUp() {
		notes = new Notes();
	}

	@Test
	public void getId() throws Exception {
		Long idValue = 4L;

		notes.setId(idValue);

		assertEquals(idValue, notes.getId());
	}
}
