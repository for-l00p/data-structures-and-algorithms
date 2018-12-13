

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// import org.junit.*;

/**
 * Junit test cases to test our Sieve of Eratosthenes algorithm 
 * for generating prime numbers upto a given integer. 
 * @author WINDOWS 8
 *
 */
public class PrimeNumberGeneratorTest{
    
    public PrimeNumberGeneratorTest(){
        System.out.println("Generator prime numbers using"
                + " Sieve of Eratosthenes algorithm");
    }
    
    @Test
    public void testPrimes(){
        int[] primeUptoZero = PrimeNumberGenerator.generatePrimeNumbersUpto(0);
        assertEquals(0, primeUptoZero.length);
        
        int[] primeUptoTwo = PrimeNumberGenerator.generatePrimeNumbersUpto(2);
        assertEquals(1, primeUptoTwo.length);
        assertEquals(2, primeUptoTwo[0]);
        
        int[] primeUptoThree = PrimeNumberGenerator.generatePrimeNumbersUpto(3);
        assertEquals(2, primeUptoThree.length);
        assertEquals(2, primeUptoThree[0]);
        assertEquals(3, primeUptoThree[1]);
        
        int[] primeUptoHundred = PrimeNumberGenerator.generatePrimeNumbersUpto(100);
        assertEquals(25, primeUptoHundred.length);
        assertEquals(97, primeUptoHundred[24]);
        
    }
    
    @Test
    public void testExhaustive(){
        for(int i = 2; i < 700; i++){
            verifyPrimeList(PrimeNumberGenerator.generatePrimeNumbersUpto(i));
        }
    }

    private void verifyPrimeList(int[] listOfPrimes) {
       for(int i = 0; i<listOfPrimes.length; i++){
           verifyPrime(listOfPrimes[i]);
       }
        
    }

    private void verifyPrime(int number) {
        for (int factor = 2; factor < number; factor++){
            assertTrue(number%factor != 0);
        }
        
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PrimeNumberGeneratorTest.class);
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
         }
    }
}