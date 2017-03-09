public interface ISimulatedElevator {

    public void stop();

    public void startMovingUp();

    public void startMovingDown();

    public void registerButtonHandler(ButtonHandler buttonHandler);

    public float getLocation();
}
