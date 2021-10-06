
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Write a description of KivaCommandTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KivaCommandTest {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        System.out.println("\n---------------------\nKivaCommandTest\n---------------------");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }
    
    @Test
    public void testForward(){
    KivaCommand command = KivaCommand.FORWARD;
    System.out.println(command);
    System.out.println(command.getDirectionKey());
    }
    
    @Test
    public void testTurnLeft(){
    KivaCommand command = KivaCommand.TURN_LEFT;
    System.out.println(command);
    System.out.println(command.getDirectionKey());
    }
    
    @Test
    public void testTurnRight(){
    KivaCommand command = KivaCommand.TURN_RIGHT;
    System.out.println(command);
    System.out.println(command.getDirectionKey());
    }
    
    @Test
    public void testTake(){
    KivaCommand command = KivaCommand.TAKE;
    System.out.println(command);
    System.out.println(command.getDirectionKey());
    }
   

    @Test
    public void testDrop(){
    KivaCommand command = KivaCommand.DROP;
    System.out.println(command);
    System.out.println(command.getDirectionKey());

    }}
    
    