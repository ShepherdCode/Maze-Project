package edu.shepherd.bcrutc01.maze.generators;

import edu.shepherd.bcrutc01.maze.structure.Cell;
import edu.shepherd.bcrutc01.maze.structure.Maze;
import edu.shepherd.bcrutc01.maze.structure.WalkDirection;


/**
 * Implements a Maze generator using Wilson's algorithm
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class MazeGenerator {

    protected Maze maze;
    private double verticalCount;
    private double horizontalCount;
    private double totalCount;

    /**
     * Construct a Maze generator for the given maze
     * @param maze maze to generate
     */
    public MazeGenerator(Maze maze) {
        this.maze = maze;
    }

    /**
     * Generate the maze
     */
    public void generate() {
        // Visit 1 cell to give the walk a place to hit
        Cell originalCell = maze.getRandomUnvisitedCell();
        maze.visitCell(originalCell);
        System.out.println("Original Cell; " + originalCell);

        // Run until there are no vertices left to visit
        while(maze.getNumberOfVertices() < maze.getMaximumVertices()) {
            Cell startingCell = maze.getRandomUnvisitedCell();
            System.out.println("Starting cell: " + startingCell);
            Cell nextCell = startingCell;

            // Walk until we hit a visited cell
            do {
                nextCell = walk(nextCell);
            } while(!maze.hasBeenVisited(nextCell));
            maze.visitCell(startingCell);

            // Register walks in the Maze
            do  {
                WalkDirection direction = startingCell.getPreviousDirectionWalked();
                Cell relative = getRelativeCell(startingCell, direction);
                maze.visitCell(relative);
                maze.addConnection(startingCell, relative);
                System.out.print("Walking from " + startingCell);
                startingCell = relative;
                System.out.println(" to " + startingCell);
            } while(!startingCell.equals(nextCell));
        }

        System.out.println("Finished generating");
    }

    /**
     * Take a 'step' with the given cell in a random direction
     * @param cell the parameter to step from
     * @return the cell that was 'stepped' into
     */
    private Cell walk(Cell cell) {
        WalkDirection direction;
        Cell relative;

        do {
           direction = getRandomWalkDirection();
           relative = getRelativeCell(cell, direction);
        } while(relative == null);

        cell.setPreviousDirectionWalked(direction);
        logDirectionWalked(direction);
        return relative;
    }

    /**
     * Get a random direction to walk in
     * @return a random direction to walk in
     */
    public WalkDirection getRandomWalkDirection() {
        return WalkDirection.of(maze.getRNG().nextInt(4));
    }

    /**
     * Get the cell relative to the specified cell in the specified direction
     *
     * @param cell the specified cell
     * @param direction the direction to go in
     * @return the relative cell
     */
    private Cell getRelativeCell(Cell cell, WalkDirection direction) {
        Cell relative = null;
        switch(direction) {
            case UP: {
                relative = maze.lookupCell(cell.getLengthPosition(), cell.getHeightPosition() - 1);
                break;
            }
            case DOWN: {
                relative = maze.lookupCell(cell.getLengthPosition(), cell.getHeightPosition() + 1);
                break;
            }
            case LEFT: {
                relative = maze.lookupCell(cell.getLengthPosition() - 1, cell.getHeightPosition());
                break;
            }
            case RIGHT: {
                relative = maze.lookupCell(cell.getLengthPosition() + 1, cell.getHeightPosition());
                break;
            }
        }

        return relative;
    }

    /**
     * Log a direction walked to the counter. Used for testing only
     * @param direction direction to log
     */
    private void logDirectionWalked(WalkDirection direction) {
        switch(direction) {
            case DOWN:
            case UP:
                verticalCount++;
                break;
            case LEFT:
            case RIGHT:
                horizontalCount++;
                break;
        }

        totalCount++;
    }

    /**
     * Gets the logged count for times a vertical direction was walked
     * @return the count
     */
    public double getVerticalCount() {
        return verticalCount;
    }

    /**
     * Gets the logged count for times a horizontal direction was walked
     * @return the count
     */
    public double getHorizontalCount() {
        return horizontalCount;
    }

    /**
     * Gets the logged count for the total number of walks
     * @return the count
     */
    public double getTotalCount() {
        return totalCount;
    }
}
