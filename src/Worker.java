public class Worker extends Thread {
    int[] array;
    int startIndex;
    int endIndex;
    int threadIndex;
    SharedMin sharedMin;

    Worker(int[] array, int startIndex, int endIndex, int threadIndex, SharedMin sharedMin) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadIndex = threadIndex;
        this.sharedMin = sharedMin;
    }

    @Override
    public void run() {
        int globalMin = sharedMin.getGlobalMin();

        int localMin = array[startIndex];
        int localMinIndex = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < localMin) {
                localMin = array[i];
                localMinIndex = i;
            }
        }

        sharedMin.updateIfLower(localMin, localMinIndex);

        System.out.println("Thread #" + (threadIndex + 1) + ": indexes [" + startIndex + ".." + endIndex + "], local min = " + localMin + ", index = " + localMinIndex);
    }
}

