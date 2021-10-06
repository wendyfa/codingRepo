
/**
 * List all types of KivaCommand with directionKey.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum KivaCommand {
    
  FORWARD ("F"),
  TURN_LEFT("L"),
  TURN_RIGHT("R"),
  TAKE("T"),
  DROP("D");
  
  private String directionKey;
  
  KivaCommand(String directionKey) {
      this.directionKey = directionKey;
  }
  
  public String getDirectionKey(){
      return directionKey;
  }

}
