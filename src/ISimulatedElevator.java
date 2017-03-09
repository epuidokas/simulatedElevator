public interface ISimulatedElevator {

    // This must be called whenever there is a person to let on or off an elevator
    // You can assume the elevator manages the actual doors and that people board/leave the elevator instantly
    public void stop();

    // Tells the elevator to start moving up
    // It won't stop moving until you call stop(), startMovingDown() or it has reached the top of the building
    public void startMovingUp();

    // Tells the elevator to start moving down
    // It won't stop moving until you call stop(), startMovingUp() or it has reached the bottom of the building
    public void startMovingDown();

    // Register a handler that is triggered whenever any buttons are pushed
    public void registerButtonHandler(ButtonHandler buttonHandler);

    // Returns the current location of the elevator
    // Partial floors means the elevator is currently travelling between floors
    public float getLocation();
}
