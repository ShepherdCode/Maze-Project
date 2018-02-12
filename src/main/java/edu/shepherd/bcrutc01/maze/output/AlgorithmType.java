package edu.shepherd.bcrutc01.maze.output;

/**
 * Represents the type of algorithm
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public enum AlgorithmType {

        /**
         * Dijkstra's Algorithm
         */
        DIJKSTRAS(),
        /**
         * A-star
         */
        ASTAR(),
        /**
         * Bellman-Ford
         */
        BELLMAN_FORD();

        public static AlgorithmType of(int value) {
            switch(value) {
                case 0: return DIJKSTRAS;
                case 1: return ASTAR;
                case 2: return BELLMAN_FORD;
            }

            throw new IllegalArgumentException("Invalid value: " + value);
        }

}