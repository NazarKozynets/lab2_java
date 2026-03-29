public class SharedMin {
    private int globalMin;
    private int globalMinIndex;

    SharedMin(int globalMin, int globalMinIndex) {
        this.globalMin = globalMin;
        this.globalMinIndex = globalMinIndex;
    }

    public synchronized void updateIfLower(int localMin, int localMinIndex) {
        if (localMin < globalMin) {
            globalMin = localMin;
            globalMinIndex = localMinIndex;
        }
    }

    public synchronized int getGlobalMin() {
        return globalMin;
    }

    public synchronized int getGlobalMinIndex() {
        return globalMinIndex;
    }
}