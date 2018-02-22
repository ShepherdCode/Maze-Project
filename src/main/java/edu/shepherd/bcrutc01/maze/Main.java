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
        if(args.length != 5) {
            System.out.println("Error: Incorrect arguments specified. Please read documentation for details.");
            System.exit(0);
        }

        int length = Integer.valueOf(args[0]);
        int height = Integer.valueOf(args[1]);
        double bias = Double.valueOf(args[2]);
        int runs = Integer.valueOf(args[3]);
        int points = Integer.valueOf(args[4]);

        OutputHandler handler = new OutputHandler();

        for(int i = 0; i < runs; i++) {
            System.out.println("Starting Maze Tests for Maze: " + i);
            Maze maze = new Maze(length, height);
            MazeGenerator generator = new MazeGenerator(maze, bias);
            generator.generate();

            for(int j = 0; j < points; j++) {
                System.out.println("Testing point: " + j);
                Cell start = getRandomCell(maze);
                Cell end = getRandomCell(maze);

                OutputData dijkstraOutputData = maze.getDijstraShortestPathData(start, end, bias);
                OutputData aStarOutputData = maze.getAStarShortestPathData(start, end, bias);
                OutputData bellmanFordOutputData = maze.getBellmanFordShortestPathData(start, end, bias);

                handler.writeEntry(AlgorithmType.DIJKSTRAS, dijkstraOutputData);
                handler.writeEntry(AlgorithmType.ASTAR, aStarOutputData);
                handler.writeEntry(AlgorithmType.BELLMAN_FORD, bellmanFordOutputData);
            }
        }

        handler.writeToFile();
    }



    public static Cell getRandomCell(Maze maze) {

        int len = rand.nextInt(maze.getLength());
        int height = rand.nextInt(maze.getHeight());


        return maze.lookupCell(len, height);
    }


}
