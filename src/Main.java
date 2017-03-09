public class Main {

    public static void main(String[] args) {

        System.out.println("Creating simulated elevator hardware...");

        final ISimulatedElevator simulatedElevator = new SimulatedElevator();

        System.out.println("Starting elevator software...");

        // ************************************
        // Start of implementation
        // ************************************

        // Example elevator that can doesn't work right
        // Only goes to floor 1
        // You should delete this example and replace w/ a correctly working elevator
        simulatedElevator.registerButtonHandler(new ButtonHandler() {
            @Override
            public void onButtonPush(int floorNumber, Direction direction) {
                if (floorNumber == 1)
                    simulatedElevator.startMovingUp();
            }
        });

        for (;;)
        {
            if (simulatedElevator.getLocation() == 1)
                simulatedElevator.stop();

            try {
                Thread.sleep(0);
            } catch (InterruptedException e)
            {
                System.out.println("InterruptedException. Quitting...");
                break;
            }
        }

        // ************************************
        // End of implementation
        // ************************************

        System.out.println("Stopping elevator software...");
    }
}
