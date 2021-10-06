import edu.duke.Point;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KivaMoveTest {
    // Define the FloorMap we'll use for all the tests
    String defaultLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **       *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    @Before
    public void setup() {
        System.out.println("\n---------------------\nKivaMoveTest\n---------------------");
    }
    //Moving Forward Test Cases
    @Test
    public void testForwardFromUp() {
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        // WHEN
        // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        // THEN
        // The Kiva has moved one space up
        verifyKivaState("testForwardFromUp", 
            kiva, new Point(2, 3), FacingDirection.UP, false, false);
    }
    
    //Turning Left Test Cases
    @Test
    public void testTurnLeftFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromUp", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    @Test
    public void testTurnLeftFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromDown", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    @Test
    public void testTurnLeftFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromLeft", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    @Test
    public void testTurnLeftFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromRight", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    //Updating Moving Forward Test Cases
    @Test
    public void testForwardWhileFacingLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardWhileFacingLeft", 
            kiva, new Point(1, 4), FacingDirection.LEFT, false, false);
    }
    
    @Test
    public void testForwardWhileFacingDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardWhileFacingDown", 
            kiva, new Point(2, 5), FacingDirection.DOWN, false, false);
    }
    
    @Test
    public void testForwardWhileFacingRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardWhileFacingRight", 
            kiva, new Point(3, 4), FacingDirection.RIGHT, false, false);
    }
    
    //Implement Turning Right, Taking, and Dropping
    @Test
    public void testTurnRightFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromUp", 
            kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }
    
    @Test
    public void testTurnRightFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromLeft", 
            kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }
    
    @Test
    public void testTurnRightFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromDown", 
            kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }
    
    @Test
    public void testTurnRightFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromRight", 
            kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }
    
    @Test
    public void testTakeOnPod() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TURN_RIGHT);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.FORWARD);
        kiva.move(KivaCommand.TAKE);
        verifyKivaState("testTakeOnPod", 
            kiva, new Point(8, 1), FacingDirection.RIGHT, true, false);
    }
    
    @Test
    public void testDropOnDropZone() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TAKE);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.DROP);
            verifyKivaState("testDropOnDropZone",kiva, new Point(10, 4), FacingDirection.RIGHT, false, true);
        } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testDropOnDropZone FAIL!");
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        }
    }
    
    //Handle Invalid Moves
    @Test 
    public void testMoveOutOfBounds() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_LEFT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);        
            kiva.move(KivaCommand.FORWARD);
       } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testMoveOutOfBounds: (expect " + e.getClass().getName() + ")");
            System.out.println("Moved outside the FloorMap!");
        }
    }
    
    @Test 
    public void testMoveIntoPodWhileCarryingPod() {
        try {          
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TAKE);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            
       } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testMoveIntoPodWhileCarryingPod: (expect " + e.getClass().getName() + ")");
            //System.out.println("Move into a pod while carrying a pod!");
            System.out.println(e.getMessage());
        }
    }
    
    @Test 
    public void testTakeAtLocationNotPod() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.TAKE);
            
       } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testTakeAtLocationNotPod: (expect " + e.getClass().getName() + ")");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testDropNotOnDropZone() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TAKE);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.DROP);
            verifyKivaState("testDropOnDropZone",kiva, new Point(9, 1), FacingDirection.RIGHT, false, true);
        } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testDropNotOnDropZone: (expect " + e.getClass().getSimpleName() + ")");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testDropWithoutCarryingPod() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.DROP);
            verifyKivaState("testDropWithoutCarryingPod",kiva, new Point(2, 4), FacingDirection.UP, false, true);
        } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testDropWithoutCarryingPod: (expect " + e.getClass().getSimpleName() + ")");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testTakeWhileCarryingPod() {
        try {
            Kiva kiva = new Kiva(defaultMap);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TURN_RIGHT);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TAKE);
            //kiva.move(KivaCommand.FORWARD);
            //kiva.move(KivaCommand.TURN_RIGHT);
            //kiva.move(KivaCommand.TURN_RIGHT);
            //kiva.move(KivaCommand.FORWARD);
            kiva.move(KivaCommand.TAKE);
            verifyKivaState("testTakeWhileCarryingPod",kiva, new Point(8, 1), FacingDirection.LEFT, true, false);
        } catch (Exception e) {
            // This only runs if no exception was thrown
            System.out.println("testTakeWhileCarryingPod: (expect " + e.getClass().getSimpleName() + ")");
            System.out.println(e.getMessage());
        }
    }

    // For you: create all the other tests and call verifyKivaState() for each

    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped) {

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)) {
            System.out.println(
                    String.format("%s: current location SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: current location FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.println(
                    String.format("%s: facing direction SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: facing direction FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(
                    String.format("%s: carrying pod SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: carrying pod FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(
                    String.format("%s: successfully dropped SUCCESS", testName));
        }
        else {
            System.out.println(
                    String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
    }
}

