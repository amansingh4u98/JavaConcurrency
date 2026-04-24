import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 3;

        private final int[] values;
        private final int start;
        private final int end;

        SumTask(int[] values, int start, int end) {
            this.values = values;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += values[i];
                }
                return sum;
            }

            int mid = (start + end) / 2;
            SumTask left = new SumTask(values, start, mid);
            SumTask right = new SumTask(values, mid, end);

            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        ForkJoinPool pool = new ForkJoinPool();

        long result = pool.invoke(new SumTask(numbers, 0, numbers.length));
        System.out.println("Sum = " + result);
    }
}
