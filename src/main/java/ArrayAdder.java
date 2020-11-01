import java.util.concurrent.atomic.LongAdder;

public class ArrayAdder extends Thread {
    private int[] transactions;
    LongAdder stat;

    public ArrayAdder(int[] transactions, LongAdder stat) {
        this.stat = stat;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        for (int i : transactions) {
            stat.add(i);
        }
    }

}
