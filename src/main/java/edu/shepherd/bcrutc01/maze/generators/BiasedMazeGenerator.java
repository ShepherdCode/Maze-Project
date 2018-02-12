package edu.shepherd.bcrutc01.maze.generators;

import edu.shepherd.bcrutc01.maze.structure.Maze;
import edu.shepherd.bcrutc01.maze.structure.WalkDirection;

/**
 * Class to handle a biased maze generator
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class BiasedMazeGenerator extends MazeGenerator {
    private double biasFactor;

    /**
     * Create a biased Maze generator
     * @param maze the maze to generate
     * @param biasFactor how biased the generator will be (.5 - 1.0, where .5 is unbiased and 1.0 is fully biased)
     */
    public BiasedMazeGenerator(Maze maze, double biasFactor) {
        super(maze);
    }

    /**
     * Get a random walk direction with a horizontal bias
     * @return a random walk direction
     */
    @Override
    public WalkDirection getRandomWalkDirection() {
        if(maze.getRNG().nextDouble() < biasFactor) {
            return WalkDirection.of(maze.getRNG().nextInt(2) + 2);
        } else {
            return WalkDirection.of(maze.getRNG().nextInt(2));
        }
    }

}
