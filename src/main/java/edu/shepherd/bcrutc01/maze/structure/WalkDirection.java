package edu.shepherd.bcrutc01.maze.structure;

public enum WalkDirection {
    /**
     * Up = y - 1
     */
    UP(),
    /**
     * Down = y + 1
     */
    DOWN(),
    /**
     * Left = x - 1
     */
    LEFT(),
    /**
     * Right = x + 1
     */
    RIGHT();

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
