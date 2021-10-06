import edu.duke.Point;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Write a description of KivaConstructorTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KivaConstructorTest {
    String defaultLayout = "" 
                            + "-------------\n" 
                            + "        P   *\n"
                            + "   **       *\n"
                            + "   **       *\n"
                            + "  K       D *\n"
                            + " * * * * * **\n"
                            + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        System.out.println("\n---------------------\nKivaConstructorTest\n---------------------");
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
    public void testSingleArgumentConstructor() {
        // We create a Kiva with the single-argument constructor         
        Kiva kiva = new Kiva(defaultMap); 

        // THEN 
        // The initial Kiva location is (2, 4) 
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testSingleArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testSingleArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        }
    }
    
    @Test
    public void testTwoArgumentConstructor() {
        // We create a Kiva with the single-argument constructor         
        Point location = new Point(5, 6);
        Kiva kiva = new Kiva(defaultMap, location); 

        // THEN 
        // The initial Kiva location is (2, 4) 
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(5, 6);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testTwoArgumentConstructor SUCCESS"); 
        } else { 
            System.out.println(String.format( "testTwoArgumentConstructor FAIL: %s != (5, 6)!", initialLocation)); 
        }
    }
   

    private boolean sameLocation(Point a, Point b) { 
        return a.getX() == b.getX() && a.getY() == b.getY(); 
    } 

    // For you: create a test for the constructor taking two arguments. 
}

