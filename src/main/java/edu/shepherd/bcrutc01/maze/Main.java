package edu.shepherd.bcrutc01.maze;


import edu.shepherd.bcrutc01.maze.generators.MazeGenerator;
import edu.shepherd.bcrutc01.maze.output.AlgorithmType;
import edu.shepherd.bcrutc01.maze.output.OutputData;
import edu.shepherd.bcrutc01.maze.output.OutputHandler;
import edu.shepherd.bcrutc01.maze.structure.Cell;
import edu.shepherd.bcrutc01.maze.structure.Maze;

import java.util.Random;

public class Main {

    private static Random rand = new Random();

    public static void main(String[] args) {
        int minSize, maxSize, runs, increment;
        double bias;
        if(args.length != 5) {
            System.out.println("Error: Incorrect arguments specified. Using default arguments");

            minSize = 10;
            maxSize = 200;
            increment = 20;
            runs = 5;
            bias = 0.50;
        } else {
            minSize = Integer.valueOf(args[0]);
            maxSize = Integer.valueOf(args[1]);
            bias = Double.valueOf(args[2]);
            runs = Integer.valueOf(args[3]);
            increment = Integer.valueOf(args[4]);
        }

        OutputHandler handler = new OutputHandler();

        // min to max by increment

        for(int i = minSize; i < maxSize; i += increment) {
            System.out.println("Starting tests for size: " + i);
            for(int j = 0; j < runs; j++) {
                System.out.println("Run " + j + " for size " + i);
                Maze maze = new Maze(i, i);
                MazeGenerator generator = new MazeGenerator(maze, bias);
                generator.generate();

                for(int k = 0; k < getNumberOfPoints(i); k++) {
                    Cell start = getRandomCell(maze);
                    Cell end = getRandomCell(maze);

                    OutputData data1 = maze.getDijstraShortestPathData(start, end);
                    OutputData data2 = maze.getAStarEuclidShortestPathData(start, end);
                    OutputData data3 = maze.getBellmanFordShortestPathData(start, end);
                    OutputData data4 = maze.getAStarManhattanShortestPathData(start, end);

                    handler.writeEntry(AlgorithmType.DIJKSTRAS, data1);
                    handler.writeEntry(AlgorithmType.ASTAR_EUCLID, data2);
                    handler.writeEntry(AlgorithmType.BELLMAN_FORD, data3);
                    handler.writeEntry(AlgorithmType.ASTAR_MANHATTAN, data4);
                }

            }

        }

        handler.writeToFile();
    }

    public static int getNumberOfPoints(int size) {
        return 100;
    }

    public static Cell getRandomCell(Maze maze) {

        int len = rand.nextInt(maze.getLength());
        int height = rand.nextInt(maze.getHeight());


        return maze.lookupCell(len, height);
    }


}
