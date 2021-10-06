import edu.duke.FileResource;
import java.util.Arrays;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;
    
  

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
   
        
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        
        // prompt for input of a map file
        System.out.println("Please Select a map file");
        String mapFile = keyboardResource.getLine();                          
        FileResource fileResource = new FileResource(mapFile);
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        Kiva kiva = new Kiva(floorMap);
        System.out.println(String.format("Start location of the kiva: %s", kiva.getCurrentLocation()));
        System.out.println(String.format("Direction of the kiva: %s\n", kiva.getDirectionFacing()));

        // prompt for input of kiva moving directions
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        
        try {
            KivaCommand[] commands = convertToKivaCommands(directions);
            if (commands[commands.length-1] != KivaCommand.DROP) {
                System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
            } else {
                for (int i = 0; i < commands.length; i++) {
                    kiva.move(commands[i]);
                }
                System.out.println("Successfully picked up the pod and dropped it off. Thank you!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (IllegalDropZoneException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (NoPodException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }catch (IllegalMoveException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
        }
    }

    /**
     * This method converts each character into KivaCommand from input string
     * @param  input
     * @return an array of KivaCommand
     */
    public KivaCommand[] convertToKivaCommands(String input) {
        KivaCommand[] commands = new KivaCommand[input.length()];
        KivaCommand[] kivaCommands = KivaCommand.values();
       
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < kivaCommands.length; j++) {
                if (kivaCommands[j].getDirectionKey().equals(Character.toString(input.charAt(i)))) {
                    commands[i] = kivaCommands[j];
                    break;
                } 
            }
            if (i < input.length() && commands[i] == null) {
                throw new IllegalArgumentException("Character '" + input.charAt(i) + "' does not correspond to a command!");
            }
        }
        
        return commands;
    }
        
}
