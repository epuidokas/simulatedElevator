public interface ButtonHandler {

    void onButtonPush(int floorNumber, Direction direction);

    public enum Direction
    {
        Up, // The up arrow outside the elevator
        Down, // The down arrow outside the elevator
        None // aka button was inside the elevator
    }
}
