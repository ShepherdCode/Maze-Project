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
         * A-star (plus Euclidean distance heuristic)
         */
        ASTAR_EUCLID(),
        /**
         * Bellman-Ford
         */
        BELLMAN_FORD(),
        /**
        * A-star (+ Manhattan distance heuristic)
        */
        ASTAR_MANHATTAN()

}