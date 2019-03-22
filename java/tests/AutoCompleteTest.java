/******************************************************************************
Compilation: javac -cp .:classes:classes/junit-4.12.jar tests/AutoCompleteTest.java 
Execution: java -cp classes:classes/junit-4.12.jar:classes/hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.AutoCompleteTest

******************************************************************************/



package tests;

import static org.junit.Assert.*;
import org.junit.Test;

// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;


import puzzles.AutoComplete;

public class AutoCompleteTest{

	private static String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
	
	// public AutoCompleteTest(){
	// 	System.out.println("Generate all words which start with the given prefix");
	// }


	@Test
	public void emptyStringShouldReturnAllWords(){
		

	//String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
		assertEquals(inputWords.length, AutoComplete.getWords("", inputWords).size());
	}


	@Test
	public void notPresentPrefixShouldReturnEmptySet(){

		//String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
		assertEquals(0, AutoComplete.getWords("bad", inputWords).size());

	}

	@Test
	public void wordInputShouldIncludeWordOutput(){
		//String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
		assertEquals(true, AutoComplete.getWords("batch", inputWords).contains("batch"));

	}


	@Test
	public void prefixInputReturnsWordsWithPrefix(){
		//String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
		assertEquals(3, AutoComplete.getWords("bat", inputWords).size());
	}

	public static void main(String[] args){

		// Result result = JUnitCore.runClasses(AutoCompleteTest.class);
		// for (Failure failure: result.getFailures()){
		// 	System.out.println(failure.toString());
		// }

	}








}