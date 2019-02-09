package tests;

/**
 * 
 */
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import ds.HashTable;

public class HashTableTest{
	
	@Test
	public void shouldCreateEmptyHashTable(){
		HashTable testTable = new HashTable();
		assertTrue(testTable.isEmpty());
	}

	@Test
	public void shouldInsertEelement(){
		HashTable<Integer,String> testTable = new HashTable<>();
		testTable.add(3, "new");
		assertFalse(testTable.isEmpty());
		assertEquals("new", testTable.get(3));
		assertEquals(1, testTable.size());
	}

	public static void main(String args[]){
		Result result = JUnitCore.runClasses(HashTableTest.class);
		//System.out.println("here");
		for (Failure failure: result.getFailures()){
			System.out.println(failure.toString());
		}
	}
}

