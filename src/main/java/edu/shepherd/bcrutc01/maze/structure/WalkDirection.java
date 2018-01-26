package edu.shepherd.bcrutc01.maze.structure;

public enum WalkDirection {
    /**
     * Up = y - 1
     */
    UP(0),
    /**
     * Down = y + 1
     */
    DOWN(1),
    /**
     * Left = x - 1
     */
    LEFT(2),
    /**
     * Right = x + 1
     */
    RIGHT(3);

    private int value;

    WalkDirection(int value) {
        this.value = value;
    }

    public static WalkDirection of(int value) {
        switch(value) {
            case 0: return UP;
            case 1: return DOWN;
            case 2: return LEFT;
            case 3: return RIGHT;
        }

        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
