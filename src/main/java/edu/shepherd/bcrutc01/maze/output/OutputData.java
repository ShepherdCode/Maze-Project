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

    public OutputData(int complexity, long elapsedTime, double biasValue) {
        this.complexity = complexity;
        this.elapsedTime = elapsedTime;
        this.biasValue = biasValue;
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

}
