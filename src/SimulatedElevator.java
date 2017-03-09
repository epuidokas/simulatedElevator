import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulatedElevator implements ISimulatedElevator{

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private ScheduledThreadPoolExecutor buttons = new ScheduledThreadPoolExecutor(1);
    private volatile float floor = 0f;
    private volatile Boolean moveUp = null;
    private volatile ButtonHandler buttonHandler;

    private DecimalFormat df = new DecimalFormat();

    public SimulatedElevator()
    {
        df.setMaximumFractionDigits(1);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                System.out.println("Elevator on floor " + df.format(floor));

                if (moveUp != null) {
                    if ((floor >= 3.9f && moveUp) || (floor <= 0.1 && !moveUp)) // elevator stops automatically if it reaches min or max floor
                    {
                        stop();
                    }
                    else if (moveUp)
                        floor += .2f;
                    else
                        floor -= .2f;
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        buttons.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (new Random().nextInt(10) < 7)
                {
                    if (buttonHandler != null)
                    {
                        int randomFloor = new Random().nextInt(5);
                        if (randomFloor != floor)
                        {
                            ButtonHandler.Direction direction = ButtonHandler.Direction.None;
                            if (randomFloor < 4 || randomFloor > 0)
                            {
                                direction = (new Random().nextBoolean()) ? ButtonHandler.Direction.None : (new Random().nextBoolean()) ? ButtonHandler.Direction.Down : ButtonHandler.Direction.Up;
                            }
                            System.out.println("Button pushed! Floor " + randomFloor + ", Direction:" + ((direction == ButtonHandler.Direction.None) ? "None":(direction == ButtonHandler.Direction.Up) ? "Up" : "Down"));
                            buttonHandler.onButtonPush(randomFloor, direction);
                        }
                    }
                }
            }
        }, 5, 5, TimeUnit.SECONDS);
    }


    public void stop()
    {
        if (moveUp != null)
            System.out.println("Stopped! Floor " + df.format(floor));
        moveUp = null;
    }

    public void startMovingUp()
    {
        System.out.println("Moving Up");
        moveUp = true;
    }

    public void startMovingDown()
    {
        System.out.println("Moving Down");
        moveUp = false;
    }

    public void registerButtonHandler(ButtonHandler buttonHandler)
    {
        this.buttonHandler = buttonHandler;
    }

    public float getLocation()
    {
        return floor;
    }
}
