import edu.duke.Point;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Write a description of KivaMotorLifetimeTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KivaMotorLifetimeTester {

    String defaultLayout =   "-----\n"
                           + "|K D|\n"
                           + "| P |\n"
                           + "|* *|\n"
                           + "-----\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    @Before
    public void setup() {
        System.out.println("\n---------------------\nKivaMotorLifetimeTester\n---------------------");
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
    public void testMotorLifetime() {
        Kiva kiva = new Kiva(defaultMap);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TAKE);
        System.out.println("kiva motor lifetime: " + kiva.getMotorLifetime());
        
    }
}
