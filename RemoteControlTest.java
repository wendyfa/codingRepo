
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


/**
 * Write a description of RemoteControlTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RemoteControlTest {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        System.out.println("\n---------------------\nRemoteControlTest\n---------------------");
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
    public void testConvertToKivaCommandsWithValidInput() {
        try {
            
            RemoteControl remoteControl = new RemoteControl();
            String input = "FFFTRF";
            KivaCommand[] kivaCommands = remoteControl.convertToKivaCommands(input);
            System.out.println(Arrays.toString(kivaCommands));
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
        }
        
        
    }
    
    @Test
    public void testConvertToKivaCommandsWithInvalidInput() {
        try {
            
            RemoteControl remoteControl = new RemoteControl();
            String input = "B";
            KivaCommand[] kivaCommands = remoteControl.convertToKivaCommands(input);
            System.out.println(Arrays.toString(kivaCommands));
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        
    }
    
    @Test
    public void testKivaCommandsWithInvalidInput() {
        try {
            
            RemoteControl remoteControl = new RemoteControl();
            String input = "FFFBTRF";
            KivaCommand[] kivaCommands = remoteControl.convertToKivaCommands(input);
            System.out.println(Arrays.toString(kivaCommands));
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        
    }
}
