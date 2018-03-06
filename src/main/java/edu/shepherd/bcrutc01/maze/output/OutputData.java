package edu.shepherd.bcrutc01.maze.output;

/**
 * Class to handle the passing of data from test results
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class OutputData {

    private int complexity;
    private long elapsedTime;
    private double biasValue;
    private long usedMemory;
    private int size;

    public OutputData(int complexity, long elapsedTime, double biasValue, long usedMemory, int size) {
        this.complexity = complexity;
        this.elapsedTime = elapsedTime;
        this.biasValue = biasValue;
        this.usedMemory = usedMemory;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("[Complexity: %s, ElapsedTime: %s, BiasValue %s]", complexity, elapsedTime, biasValue);
    }

    public double getBiasValue() {
        return biasValue;
    }

    public int getComplexity() {
        return complexity;
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
