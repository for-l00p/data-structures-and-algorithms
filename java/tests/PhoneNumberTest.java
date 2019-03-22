package tests;


import org.junit.Test;
import static org.junit.Assert.*;

import puzzles.PhoneNumber;
import java.util.Map;
import java.util.HashMap;

public class PhoneNumberTest {


	@Test
	public void shoudldReturnCorrect(){

		Map<String, String> directory = new HashMap<>();
		directory.put("Bob", "91 12 54 26");
		directory.put("Alice", "97 625 992");
		directory.put("Emergency", "911");
		assertEquals(false, PhoneNumber.areConsistent(directory));

	}

	@Test
	public void shouldReturnCorrect2(){

		Map<String, String> directory = new HashMap<>();
		directory.put("Bob", "91 12 54 26");
		directory.put("Alice", "97 625 992");
		directory.put("Emergency", "913");
		assertEquals(true, PhoneNumber.areConsistent(directory));

	}




}

