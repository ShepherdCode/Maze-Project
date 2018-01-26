package edu.shepherd.bcrutc01.maze;

import edu.shepherd.bcrutc01.maze.generators.HorizontalBiasedMazeGenerator;
import edu.shepherd.bcrutc01.maze.generators.MazeGenerator;
import edu.shepherd.bcrutc01.maze.generators.VerticalBiasedMazeGenerator;
import edu.shepherd.bcrutc01.maze.structure.Maze;

public class Main {

    public static void main(String[] args) {
        // TODO: Accept commandline arguments

        long curr = System.currentTimeMillis();

        for(int i = 0; i < 64; i++) {
            Maze maze = new Maze(16, 16);
            MazeGenerator generator = new MazeGenerator(maze);

            generator.generate();

            System.out.println("Finished running Maze " + i);
        }


        System.out.printf("Took %d ms to generate.%n", System.currentTimeMillis() - curr);

    }



}
