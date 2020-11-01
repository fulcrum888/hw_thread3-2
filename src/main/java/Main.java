import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int MAX_VALUE = 1000;
        final int ARRAY_SIZE = 1000;

        LongAdder stat = new LongAdder();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        IntStream stream1 = IntStream.generate(() -> {return (int) (Math.random() * MAX_VALUE);});
        IntStream stream2 = IntStream.generate(() -> {return (int) (Math.random() * MAX_VALUE);});
        IntStream stream3 = IntStream.generate(() -> {return (int) (Math.random() * MAX_VALUE);});
        stream1.limit((ARRAY_SIZE)).forEach(i -> executorService1.submit(() -> stat.add(i)));
        stream2.limit((ARRAY_SIZE)).forEach(i -> executorService2.submit(() -> stat.add(i)));
        stream3.limit((ARRAY_SIZE)).forEach(i -> executorService3.submit(() -> stat.add(i)));

        executorService1.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(stat.sum());
        executorService1.shutdown();
        executorService2.shutdown();
        executorService3.shutdown();
    }
}
