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
        while(maze.getNumberOfVertices() < maze.getMaximumVertices()) {
            // Get starting cell
            Cell cell = maze.getRandomUnvisitedCell();

            // Get Cell to start walking from
            Cell originalWalkingCell = maze.getRandomUnvisitedCell();
            Cell walkingCell = originalWalkingCell;

            // Make sure we aren't trying to walk from a cell to itself to start with
            // This will only happen if getRandomUnvisitedCell() returns the same cell twice in a row
            if(cell.equals(walkingCell)) {

                // If there is only one cell left to generate with, we randomly assign a connection to neighbour cell
                if(maze.getMaximumVertices() - maze.getNumberOfVertices() == 1) {
                    WalkDirection direction;
                    Cell relative;

                    do {
                        direction = WalkDirection.of(maze.getRNG().nextInt(4));
                        relative = getRelativeCell(cell, direction);
                    } while(relative == null);

                    maze.visitCell(cell);
                    maze.addConnection(cell, relative);
                    return;
                }


                while(cell.equals(walkingCell)) {
                    walkingCell = maze.getRandomUnvisitedCell();
                }
            }

            // Preform random walk until you reach the starting cell
            while(!cell.equals(walkingCell)) {
               //System.out.print("Walking from: " + walkingCell);
               walkingCell = walk(walkingCell);
               //System.out.println("to: " + walkingCell);
            }

            maze.visitCell(originalWalkingCell);

            // Populate maze with walk
            while(!originalWalkingCell.equals(cell)) {
                Cell relative = getRelativeCell(originalWalkingCell, originalWalkingCell.getPreviousDirectionWalked());
                maze.visitCell(relative);
                maze.addConnection(originalWalkingCell, relative);
                originalWalkingCell =  relative;
            }
        }
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
}
