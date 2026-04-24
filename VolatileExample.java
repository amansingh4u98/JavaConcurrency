public class VolatileExample {
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            while (running) {
                // Simulate work while checking the shared flag.
            }
            System.out.println("Worker stopped");
        });

        worker.start();

        Thread.sleep(2000);
        running = false;
    }
}
