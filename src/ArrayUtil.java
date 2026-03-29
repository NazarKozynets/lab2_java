import java.util.concurrent.ThreadLocalRandom;

interface IArrayUtil {
    // TODO: generate array
    public int[] GenerateArray(int length);

    // TODO: get thread bounds
    public int[] GetThreadBounds(int threadIndex, int threadsAmount, int totalLength);
}

public class ArrayUtil implements IArrayUtil {
    public int[] GenerateArray(int length) {
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 999999);
        }

        return array;
    }

    public int[] GetThreadBounds(int threadIndex, int threadsAmount, int totalLength) {
        int baseSize = totalLength / threadsAmount;
        int remainder = totalLength % threadsAmount;

        int startIndex = threadIndex * baseSize + Math.min(threadIndex, remainder);
        int partSize = baseSize + (threadIndex < remainder ? 1 : 0);
        int endIndex = startIndex + partSize - 1;

        return new int[] { startIndex, endIndex };
    }
}
