package mcga.brainfuck;

/**
 * Created by user on 09/11/2016.
 */
public class Metrics {
    private static long PROG_SIZE = 0;
    private static double EXEC_TIME = 0;
    private static long EXEC_POS = 0;
    private static long DATA_MOVE = 0;
    private static long DATA_WRITE = 0;
    private static long DATA_READ = 0;
    private static long EXEC_MOVE = 0;

    public static void logMetrics() {
        System.err.println("Exec step => " + Double.toString(Metrics.getExecPos())
                + '\t' + '\t' + "Data pointer loc => " + Brainfuck.getMemory().getCurrentIndex()
                + '\t' + '\t' + "Memory => " + Brainfuck.getMemory());
    }

    public static String printMetrics() {
        return "PROG_SIZE = " + getProgSize() + '\n' +
                "EXEC_TIME = " + getExecTime() + " ms" + '\n' +
                "EXEC_MOVE = " + getExecMove() + '\n' +
                "DATA_MOVE = " + getDataMove() + '\n' +
                "DATA_READ = " + getDataRead() + '\n' +
                "DATA_WRITE = " + getDataWrite() + '\n';
    }

    public static long getProgSize() {
        return PROG_SIZE;
    }

    public static void setProgSize(long progSize) {
        PROG_SIZE = progSize;
    }

    public static double getExecTime() {
        return EXEC_TIME;
    }

    public static void setExecTime(double execTime) {
        EXEC_TIME = execTime;
    }

    public static long getExecPos() {
        return EXEC_POS;
    }

    public static void setExecPos(long execPos) {
        EXEC_POS = execPos;
    }

    public static long getDataMove() {
        return DATA_MOVE;
    }

    public static void setDataMove(long dataMove) {
        DATA_MOVE = dataMove;
    }

    public static long getDataWrite() {
        return DATA_WRITE;
    }

    public static void setDataWrite(long dataWrite) {
        DATA_WRITE = dataWrite;
    }

    public static long getDataRead() {
        return DATA_READ;
    }

    public static void setDataRead(long dataRead) {
        DATA_READ = dataRead;
    }

    public static long getExecMove() {
        return EXEC_MOVE;
    }

    public static void setExecMove(long execMove) {
        EXEC_MOVE = execMove;
    }
}
