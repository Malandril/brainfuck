package mcga.brainfuck;

/**
 * Creates and controls the metrics related to the execution of the program
 *
 * @author Team Make Coding Great Again
 */
public class Metrics {
    private static long progSize = 0;
    private static double execTime = 0;
    private static long execStep = 0;
    private static long dataMove = 0;
    private static long dataWrite = 0;
    private static long dataRead = 0;
    private static long execMove = 0;
    
    /**
     * Getters and setters of the metrics
     */

    public static long getProgSize() {
        return progSize;
    }

    public static void setProgSize(long progSize) {
        Metrics.progSize = progSize;
    }

    public static double getExecTime() {
        return execTime;
    }

    public static void setExecTime(double execTime) {
        Metrics.execTime = execTime;
    }
    
    public static long getExecStep() {
        return execStep;
    }
    
    public static void setExecStep(long execStep) {
        Metrics.execStep = execStep;
    }

    public static long getDataMove() {
        return dataMove;
    }

    public static void setDataMove(long dataMove) {
        Metrics.dataMove = dataMove;
    }

    public static long getDataWrite() {
        return dataWrite;
    }

    public static void setDataWrite(long dataWrite) {
        Metrics.dataWrite = dataWrite;
    }

    public static long getDataRead() {
        return dataRead;
    }

    public static void incrDataRead() {
        dataRead++;
    }

    public static long getExecMove() {
        return execMove;
    }

    public static void setExecMove(long execMove) {
        Metrics.execMove = execMove;
    }

    public static void incrExecPos(long i) {
        execStep += i;
    }

    public static void incrProgSize() {
        progSize++;
    }
}
