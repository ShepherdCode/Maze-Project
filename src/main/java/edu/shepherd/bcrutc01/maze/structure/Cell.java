package edu.shepherd.bcrutc01.maze.structure;

/**
 * A Cell in the Maze
 * @author Brian Crutchley
 * @version 1.0
 */
public class Cell {
    private int lengthPosition;
    private int heightPosition;

    private WalkDirection previousDirectionWalked;

    /**
     * A cell in the Maze
     *
     * @param lengthPosition the length (or x) position, where 0 is the leftmost cell in the maze
     * @param heightPosition the height (or y) position, where 0 is the topmost cell in the maze
     */
    public Cell(int lengthPosition, int heightPosition) {
        this.lengthPosition = lengthPosition;
        this.heightPosition = heightPosition;
    }

    /**
     * Get the length (or x) position of this cell
     * @return the length position of this cell
     */
    public int getLengthPosition() {
        return lengthPosition;
    }

    /**
     * Get the height (or x) position of this cell
     * @return the height position of this cell
     */
    public int getHeightPosition() {
        return heightPosition;
    }

    /**
     * Get the previous direction walked for this cell
     * @return the previous direction walked for this cell
     */
    public WalkDirection getPreviousDirectionWalked() {
        return previousDirectionWalked;
    }

    /**
     * Set the previous direction walked for this cell
     * @param previousDirectionWalked the previous direction walked to set
     */
    public void setPreviousDirectionWalked(WalkDirection previousDirectionWalked) {
        this.previousDirectionWalked = previousDirectionWalked;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Cell)) return false;
        Cell other = (Cell) o;
        return lengthPosition == other.lengthPosition && heightPosition == other.heightPosition;
    }

    @Override
    public String toString() {
        return String.format("%d,%d", lengthPosition, heightPosition);
    }




}
