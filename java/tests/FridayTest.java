package tests;

import org.junit.Test;
import static org.junit.Assert.*;


import puzzles.Friday;


public class FridayTest{
	
	@Test
	public void shouldReturnForLeapYear(){
		assertEquals(true, Friday.isLeapYear(4));
		assertEquals(true, Friday.isLeapYear(400));
		assertEquals(false, Friday.isLeapYear(100));
		assertEquals(true, Friday.isLeapYear(1996));
		assertEquals(true, Friday.isLeapYear(2004));
		assertEquals(false, Friday.isLeapYear(2019));
		assertEquals(true, Friday.isLeapYear(2012));
		assertEquals(true, Friday.isLeapYear(2000));
		assertEquals(false, Friday.isLeapYear(1900));
	}





}