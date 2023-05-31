package one.nxeu.vlcore.util;

import java.util.Arrays;

public class TpsHelper {
    public static double average(final long[] values) {
        return Arrays.stream(values).average().orElse(0);
    }
    public static double average(final double[] values) {
        return Arrays.stream(values).average().orElse(0);
    }

    public static double toMilliseconds(final long in) {
        return in * 1.0E-6D;
    }
    public static double toMilliseconds(final double in) {
        return in * 1.0E-6D;
    }
}
