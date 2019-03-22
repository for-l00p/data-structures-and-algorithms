/******************************************************************************
Compilation: javac -cp .:classes:classes/junit-4.12.jar -d classes tests/ShuffleTest.java
-cp: Class Path (where to find the needed classes for compilaton)
Dependencies: 
org.junit  (Location: classes/j-unit-4.12.jar)
puzzles.Shuffle (Location : classes)

Execution: java -cp classes:classes/junit-4.12.jar:classes/hamcrest-core-1.3.jar  org.junit.runner.JUnitCore tests.ShuffleTest

Class path helps locate the classes from the root folder. 
ShuffleTest has a dependency on puzzles.Shuffle compiled class. so classes needs to be on the class path.
The class org.junit.Assert has a dependency on Hamcrest core library. As it is part of the static method signature assertThat(), it has to be on the classpath.

Have to run test runner class: as there is no Main in ShuffleTest. A test runner collects all the @test methods and executes them. 



******************************************************************************/


package tests;

import org.junit.*;
import static org.junit.Assert.*;

import puzzles.Shuffle;

//

public class ShuffleTest {

    @Test
    public void shouldShuffleArray() {
        int[] input = {2,3,1,6,4};     
        //int[] inputShuffled = Shuffle.answer(input, new MyRandom());
        int[] inputShuffled = Shuffle.answer(input, (i, j) -> {
            return (j+i)/2;
        });
        int[] expectedShuffled = new int[]{1,6,3,4,2}; //n = 5
        assertArrayEquals(expectedShuffled, inputShuffled);
        
    }
}
