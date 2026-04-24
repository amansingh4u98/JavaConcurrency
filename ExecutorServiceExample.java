import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Callable<String> task1 = () -> "Task 1 ran on " + Thread.currentThread().getName();
        Callable<String> task2 = () -> "Task 2 ran on " + Thread.currentThread().getName();
        Callable<String> task3 = () -> "Task 3 ran on " + Thread.currentThread().getName();

        Future<String> result1 = pool.submit(task1);
        Future<String> result2 = pool.submit(task2);
        Future<String> result3 = pool.submit(task3);

        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println(result3.get());

        pool.shutdown();
    }
}
