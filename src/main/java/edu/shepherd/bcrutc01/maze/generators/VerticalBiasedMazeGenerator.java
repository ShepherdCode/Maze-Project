package edu.shepherd.bcrutc01.maze.generators;

import edu.shepherd.bcrutc01.maze.structure.Maze;
import edu.shepherd.bcrutc01.maze.structure.WalkDirection;

public class VerticalBiasedMazeGenerator extends MazeGenerator {

    public VerticalBiasedMazeGenerator(Maze maze) {
        super(maze);
    }

    /**
     * Get a random walk direction with a vertical bias
     * @return a random walk direction
     */
    @Override
    public WalkDirection getRandomWalkDirection() {
        if(maze.getRNG().nextDouble() < .8) {
            return WalkDirection.of(maze.getRNG().nextInt(2));
        } else {
            return WalkDirection.of(maze.getRNG().nextInt(2) + 2);
        }
    }

}
