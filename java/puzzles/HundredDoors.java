    
public class HundredDoors {

    public enum Status {
       OPEN,
       CLOSED
    }
    
    
    public static Status[] answer(){
        Status[] doorStatus = initDoors(100);
        makeMultiplePasses(100, doorStatus);
        return doorStatus;
    }
    
       
    private static Status[] initDoors(int numDoors){
        Status[] doors = new Status[numDoors];
        for (int i = 0; i < numDoors; i ++){
            doors[i] = Status.CLOSED;
        }
        return doors;
    } 
    
    private static Status[] makeMultiplePasses(int numPasses, Status[] doors) {
        for (int i = 1; i <= numPasses; i++) {
            doors = makeAPass(i, doors);
        }
        return doors;
    }
    
    private static Status[] makeAPass(int visitGap, Status[] doors) {
  
        for(int i = visitGap-1; i < doors.length; i = i + visitGap){
            doors = toggleDoor(i, doors );
        } 
        return doors;
    }
    
    private static Status[] toggleDoor(int i, Status[] doors){
        if (doors[i].equals(Status.CLOSED)){
            doors[i] = Status.OPEN;
        } else {
            doors[i] = Status.CLOSED;
        }
        return doors;
    }
    
    
}
