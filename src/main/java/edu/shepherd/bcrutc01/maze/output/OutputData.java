package edu.shepherd.bcrutc01.maze.output;

/**
 * Class to handle the passing of data from test results
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class OutputData {

    private long elapsedTime;
    private long usedMemory;
    private int size;

    public OutputData(long elapsedTime, long usedMemory, int size) {
        this.elapsedTime = elapsedTime;
        this.usedMemory = usedMemory;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("");
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public int getSize() {
        return size;
    }

}
