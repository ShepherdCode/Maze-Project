package edu.shepherd.bcrutc01.maze.structure;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Random;

/**
 * Class to represent a Maze in memory
 *
 * The basic structure of the maze will be comprised of tiles and walls, where each tile is bordered by a wall on each of the 4 sides
 * Tiles next to each other will share a wall
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class Maze {

    private Graph<Cell, DefaultEdge> graph;
    // Provides the ability to look up a Cell by position without having to search through every vertex in the graph
    private Cell[][] lookupMatrix;
    private int length;
    private int height;
    private Random random;

    /**
     * Construct a maze object of the specified size. By default, all walls are filled
     *
     * @param length the length of the maze, in tiles. 0 is the leftmost tile
     * @param height the height of the maze, in tiles. 0 is the topmost tile
     */
    public Maze(int length, int height) {
        this.length = length;
        this.height = height;
        graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        lookupMatrix = new Cell[length][height];
        this.random = new Random();

        // Initialize the lookup matrix
        for(int l = 0; l < length; l++) {
            for(int h = 0; h < height; h++) {
                Cell cell = new Cell(l, h);
                lookupMatrix[l][h] = cell;
            }
        }
    }

    /**
     * Visit the given cell, adding it to the Maze
     *
     * @param cell the cell to visit
     */
    public void visitCell(Cell cell) {
        graph.addVertex(cell);
    }

    /**
     * Connect two cells in the Maze
     *
     * @param a the first cell
     * @param b the second cell
     */
    public void addConnection(Cell a, Cell b) {
        graph.addEdge(a, b);
    }

    /**
     * Looks up a Cell in the Maze
     *
     * @param lengthPos the length/x position of the cell
     * @param heightPos the height/y position of the cell
     * @return the cell at the given position, or null if out of bounds
     */
    public Cell lookupCell(int lengthPos, int heightPos) {
        if(!isInBounds(lengthPos, heightPos)) return null;


        return lookupMatrix[lengthPos][heightPos];
    }

    /**
     * Checks if a given coordinate is inside of the Maze
     *
     * @param lengthPos the length/x coordinate
     * @param heightPos the height/y coordinate
     * @return true if it is bounds
     */
    public boolean isInBounds(int lengthPos, int heightPos) {
        return lengthPos >= 0 && lengthPos < length && heightPos >= 0 && heightPos < height;
    }

    /**
     * Get a random unvisited cell in this Maze
     * @return a randomly chosen unvisited cell
     */
    public Cell getRandomUnvisitedCell() {
        Cell cell;
        do {
            int randX = random.nextInt(length);
            int randY = random.nextInt(height);

            cell = lookupCell(randX, randY);
        } while (hasBeenVisited(cell));

        return cell;
    }

    /**
     * Returns whether or not a cell has been 'visited' (A Cell has been 'visited' if it is a valid part of the maze)
     * @param cell the cell to check
     * @return whether or not the cell has been 'visited'
     */
    public boolean hasBeenVisited(Cell cell) {
        return graph.containsVertex(cell);
    }

    /**
     * Get this Maze's random number generator
     * @return the random number generator for this maze
     */
    public Random getRNG() {
        return random;
    }

    /**
     * Get the current number of vertices for this Maze
     * @return the current number of vertices for this Maze
     */
    public int getNumberOfVertices() {
        return graph.vertexSet().size();
    }

    /**
     * Get the maximum number of vertices for this Maze
     * @return the maximum number of vertices for this Maze
     */
    public int getMaximumVertices() {
        return length * height;
    }

    /**
     * Get the length of this maze
     * @return length of this maze
     */
    public int getLength() {
        return length;
    }

    /**
     * Get the height of this maze
     * @return height of this maze
     */
    public int getHeight() {
        return height;
    }

}
