import java.util.concurrent.atomic.AtomicReference;

public class SharedMin {

    static class MinPair {
        final int value;
        final int index;

        MinPair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private final AtomicReference<MinPair> globalMin =
            new AtomicReference<>(new MinPair(Integer.MAX_VALUE, -1));

    public void updateIfLower(int localMin, int localMinIndex) {
        while (true) {
            MinPair current = globalMin.get();

            if (localMin >= current.value) {
                return;
            }

            MinPair newValue = new MinPair(localMin, localMinIndex);

            if (globalMin.compareAndSet(current, newValue)) {
                return;
            }
        }
    }

    public int getGlobalMin() {
        return globalMin.get().value;
    }

    public int getGlobalMinIndex() {
        return globalMin.get().index;
    }
}