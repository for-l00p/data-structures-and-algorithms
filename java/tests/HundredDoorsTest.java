import org.junit.*;
import static org.junit.Assert.*;

public class HundredDoorsTest {

    @Test
    public void firstDoorShouldBeOpen() {
      HundredDoors.Status[] doorStatus = HundredDoors.answer();
      assertEquals(HundredDoors.Status.OPEN, doorStatus[0]);
    }
    
    @Test
    public void secondDoorShouldBeShut() {
       
       
        HundredDoors.Status[] doorStatus = HundredDoors.answer();
        assertEquals(HundredDoors.Status.CLOSED, doorStatus[1]);
        
    }
    
    @Test
    public void thirdDoorShouldBeShut() {
    
       HundredDoors.Status[] doorStatus = HundredDoors.answer();
        assertEquals(HundredDoors.Status.CLOSED, doorStatus[2]);
        
    }
    
     @Test
    public void fourthDoorShouldBeOpen() {
       HundredDoors.Status[] doorStatus = HundredDoors.answer();
         assertEquals(HundredDoors.Status.OPEN, doorStatus[3]);
        
    }
    
    @Test
    public void fortySecondDoorShouldBeClosed() {
        HundredDoors.Status[] doorStatus = HundredDoors.answer();
         assertEquals(HundredDoors.Status.CLOSED, doorStatus[41]);
        
    }
    
    @Test
    public void twentyFifthDoorShouldBeOpen() {
        HundredDoors.Status[] doorStatus = HundredDoors.answer();
         assertEquals(HundredDoors.Status.OPEN, doorStatus[24]);
        
    }
    
      @Test
    public void hundredthDoorShouldBeOpen() {
        HundredDoors.Status[] doorStatus = HundredDoors.answer();
        assertEquals(HundredDoors.Status.OPEN, doorStatus[99]);
        
    }
    
    
    
    
    
}
