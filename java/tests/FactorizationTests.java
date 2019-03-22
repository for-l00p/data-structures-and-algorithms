package tests;

import org.junit.*;
import static org.junit.Assert.*;

public class FactorizationTests {
    @Test
    public void shouldReturnTwoForTwo() {
        int n = 2;
        int[] expected = new int[]{2};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnThreeForThree() {
        int n = 3;
        int[] expected = new int[]{3};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnDoubleTwoForFour() {
        int n = 4;
        int[] expected = new int[]{2,2};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnTwoThreeForSix() {
        int n = 6;
        int[] expected = new int[]{2,3};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnDoubleThreeForNine() {
        int n =  9;
        int[] expected = new int[]{3,3};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnCorrectForTwelve() {
        int n = 12;
        int[] expected = new int[]{2,2,3};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void shouldReturnCorrectForFifteen() {
        int n = 15;
        int[] expected = new int[]{3,5};
        int[] actual = Factorization.getFactors(n);
        assertArrayEquals(expected, actual);
    }
    
    
    
}
