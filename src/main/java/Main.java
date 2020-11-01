import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int MAX_VALUE = 1000;
        final int ARRAY_SIZE = 1000;
        LongAdder stat = new LongAdder();
        ArrayAdder thread1 = new ArrayAdder(generateArray(ARRAY_SIZE, MAX_VALUE), stat);
        ArrayAdder thread2 = new ArrayAdder(generateArray(ARRAY_SIZE, MAX_VALUE), stat);
        ArrayAdder thread3 = new ArrayAdder(generateArray(ARRAY_SIZE, MAX_VALUE), stat);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(stat.sum());
    }

    public static int[] generateArray(int arraySize, int maxValue) {
        Random random = new Random();
        int[] result = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            result[i] = random.nextInt(maxValue);
        }
        return result;
    }
}
