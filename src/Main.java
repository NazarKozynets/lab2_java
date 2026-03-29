import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Enter number of threads: ");
        int threadCount = scanner.nextInt();

        System.out.print("Enter array length for each thread: ");
        int sizeForThread = scanner.nextInt();

        int arrayLength = threadCount * sizeForThread;

        ArrayUtil arrayUtil = new ArrayUtil();
        int[] array = arrayUtil.GenerateArray(arrayLength);

        int randomIndex = ThreadLocalRandom.current().nextInt(0, array.length);
        array[randomIndex] = -Math.abs(array[randomIndex]); // setting up the same value but negative

        SharedMin sharedMin = new SharedMin(Integer.MAX_VALUE, -1);

        Worker[] workers = new Worker[threadCount];

        for (int i = 0; i < threadCount; i++) {
            int[] bounds = arrayUtil.GetThreadBounds(i, threadCount, arrayLength);

            workers[i] = new Worker(
                    array,
                    bounds[0],
                    bounds[1],
                    i,
                    sharedMin
            );

            workers[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            workers[i].join();
        }

        System.out.println("Lowest value is: " + sharedMin.getGlobalMin());
        System.out.println("Index of lowest value is: " + sharedMin.getGlobalMinIndex());
    }
}
