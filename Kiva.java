import edu.duke.Point;
/**
 * Kiva class represents Kiva robot, implement the logic how to
 * move forward (F), turn left (L), turn right (R), take (T), and drop (D)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kiva {
    private FloorMap floorMap;
    private Point location;
    private FacingDirection directionFacing;
    private boolean carryingPod;
    private boolean successfullyDropped;
    private long motorLifetime;

    /**
     * Kiva constructor with 1 parameter
     * @param floorMap
     */
    public Kiva(FloorMap floorMap) {
        this.floorMap = floorMap;
        this.location = floorMap.getInitialKivaLocation();
        directionFacing = FacingDirection.UP;
        carryingPod = false;
        successfullyDropped = false;
    }

    /**
     * Kiva constructor with 2 parameters
     * @param floorMap
     * @param location
     */
    public Kiva(FloorMap floorMap, Point location) {
        this.floorMap = floorMap;
        directionFacing = FacingDirection.UP;
        carryingPod = false;
        successfullyDropped = false;
        this.location = location;
    }
    
    public Point getCurrentLocation() {
        return this.location;
    }
    
    public void setCurrentLocation(Point location) {
        this.location = location;
    }
    
    public FacingDirection getDirectionFacing() {
        return directionFacing;
    }
    
    public void setDirectionFacing(FacingDirection directionFacing) {
        this.directionFacing = directionFacing;
    }
    
    public boolean isCarryingPod() {
        return carryingPod;
    }
    
    public void setCarryingPod(boolean isCarryingPod) {
         carryingPod = isCarryingPod;
    }
    
    public boolean isSuccessfullyDropped() {
        return successfullyDropped;
    }
    
    public void setSuccessfullyDropped(boolean successfullyDropped) {
         this.successfullyDropped = successfullyDropped;
    }
    
    public long getMotorLifetime() {
        return motorLifetime;
    }
    
    public void incrementMotorLifetime() {
        motorLifetime += 1000;
    }

    /**
     * Implement logic for moving forward, turn left, turn right, take and drop
     * @param kivaCommand
     */
    public void move(KivaCommand kivaCommand) {
        // update location when moving forward
        if (kivaCommand == KivaCommand.FORWARD) {
            Point currentLocation = null;
            incrementMotorLifetime();
            if (this.directionFacing == FacingDirection.UP) {
                currentLocation = new Point(this.location.getX(), this.location.getY() - 1);
            } else if (this.directionFacing == FacingDirection.RIGHT) {
                currentLocation = new Point(this.location.getX() + 1, this.location.getY());
            } else if (this.directionFacing == FacingDirection.DOWN) {
                currentLocation = new Point(this.location.getX(), this.location.getY() + 1);
            } else if (this.directionFacing == FacingDirection.LEFT) {
                currentLocation = new Point(this.location.getX() -1, this.location.getY());
            } 
            if (validateMove(currentLocation, KivaCommand.FORWARD)) {
                setCurrentLocation(currentLocation);
            }
        }
        // update facing direction when turning left
        else if (kivaCommand == KivaCommand.TURN_LEFT) {
            incrementMotorLifetime();
            if (this.directionFacing == FacingDirection.UP) {
                this.setDirectionFacing(FacingDirection.LEFT);
            } else if (this.directionFacing == FacingDirection.RIGHT) {
                this.setDirectionFacing(FacingDirection.UP);
            } else if (this.directionFacing == FacingDirection.DOWN) {
                this.setDirectionFacing(FacingDirection.RIGHT);
            } else if (this.directionFacing == FacingDirection.LEFT) {
                this.setDirectionFacing(FacingDirection.DOWN);
            }  
        }
        // update facing direction when turning right
        else if (kivaCommand == KivaCommand.TURN_RIGHT) {
            incrementMotorLifetime();
            if (this.directionFacing == FacingDirection.UP) {
                this.setDirectionFacing(FacingDirection.RIGHT);
            } else if (this.directionFacing == FacingDirection.RIGHT) {
                this.setDirectionFacing(FacingDirection.DOWN);
            } else if (this.directionFacing == FacingDirection.DOWN) {
                this.setDirectionFacing(FacingDirection.LEFT);
            } else if (this.directionFacing == FacingDirection.LEFT) {
                this.setDirectionFacing(FacingDirection.UP);
            }
            
        }
        // update carryingPod and successfullyDropped status when taking or dropping a pod
        else if (kivaCommand == KivaCommand.TAKE) {
            if (validateMove(location, KivaCommand.TAKE)) {
                this.setCarryingPod(true);
                this.setSuccessfullyDropped(false);
            }
        } else if (kivaCommand == KivaCommand.DROP) {
            if (validateMove(location, KivaCommand.DROP)) {
                this.setSuccessfullyDropped(true);
                this.setCarryingPod(false);
            }
        }
        
    }

    /**
     * Handle exceptions for moving forward, take and drop
     * @param location
     * @param kivaCommand
     * @return
     */
    private boolean validateMove(Point location, KivaCommand kivaCommand) {
        boolean result = true;
        
        FloorMapObject terrain = floorMap.getObjectAtLocation(location);
        if (kivaCommand == KivaCommand.FORWARD && terrain == FloorMapObject.OBSTACLE) {
            result = false;
            throw new IllegalMoveException(String.format("Can't move onto an obstacle at %s!", location));
        } else if (kivaCommand == KivaCommand.FORWARD && carryingPod && terrain == FloorMapObject.POD) {
            result = false;
            throw new IllegalMoveException(String.format(
            "Can't move onto a pod at %s while carrying a pod!", location));

        } else if (kivaCommand == KivaCommand.TAKE && carryingPod ) {
            result = false;
            throw new IllegalMoveException("Can't take another pod while carrying a pod!");

        } else if (kivaCommand == KivaCommand.TAKE && terrain != FloorMapObject.POD) {
            result = false;
            throw new NoPodException(String.format(
            "Can't take nonexistent pod from location %s!", location));

        } else if (kivaCommand == KivaCommand.DROP && !carryingPod) {
            result = false;
            throw new IllegalMoveException("Can't DROP without carrying a pod!");

        } else if (kivaCommand == KivaCommand.DROP && terrain != FloorMapObject.DROP_ZONE) {
            result = false;
            throw new IllegalDropZoneException(String.format(
            "Can't just drop pods willy-nilly at %s!", location));

        } 
        
        return result;
    }
        

}
