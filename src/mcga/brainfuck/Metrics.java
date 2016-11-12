package mcga.brainfuck;

/**
 * Created by user on 09/11/2016.
 */
public class Metrics {
    static long PROG_SIZE = 0;
    static double EXEC_TIME = 0;
    static long EXEC_POS = 0;
    static long DATA_MOVE = 0;
    static long DATA_WRITE = 0;
    static long DATA_READ = 0;
    static long EXEC_MOVE = 0;

    public static void logMetrics() {
        System.err.println("Exec step => " + Double.toString(Metrics.EXEC_POS)
                + '\t' + '\t' + "Data pointer loc => " + Brainfuck.memory.getCurrentIndex()
                + '\t' + '\t' + "Memory => " + Brainfuck.memory);
    }

    public static String printMetrics() {
        return "PROG_SIZE = " + PROG_SIZE + '\n' +
                "EXEC_TIME = " + EXEC_TIME + " ms" + '\n' +
                "EXEC_MOVE = " + EXEC_MOVE + '\n' +
                "DATA_MOVE = " + DATA_MOVE + '\n' +
                "DATA_READ = " + DATA_READ + '\n' +
                "DATA_WRITE = " + DATA_WRITE + '\n';
    }
}
