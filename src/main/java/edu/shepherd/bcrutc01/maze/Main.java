package edu.shepherd.bcrutc01.maze;


import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import edu.shepherd.bcrutc01.maze.generators.MazeGenerator;
import edu.shepherd.bcrutc01.maze.output.AlgorithmType;
import edu.shepherd.bcrutc01.maze.output.OutputData;
import edu.shepherd.bcrutc01.maze.output.OutputHandler;
import edu.shepherd.bcrutc01.maze.structure.Cell;
import edu.shepherd.bcrutc01.maze.structure.Maze;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.util.Random;

public class Main {

    private static Random rand = new Random();

    public static void main2(String[] args) {
        int counter = 1;

        while (counter < 2) {
            Maze unbiasedMaze = new Maze(100, 100);
            MazeGenerator generator = new MazeGenerator(unbiasedMaze);
            generator.generate();

            //visualizeGraph(unbiasedMaze.getGraph());

            for (int i = 0; i < 1000; i++) {
                Cell start = getRandomCell(unbiasedMaze);
                Cell end = getRandomCell(unbiasedMaze);

                OutputData aStarData = unbiasedMaze.getAStarShortestPathData(start, end);
                System.out.printf("Output data for path %d from %s to %s : %s%n", i, start, end, aStarData);
                OutputHandler.getInstance().writeEntry(AlgorithmType.ASTAR, aStarData);
            }
            counter++;
        }

        OutputHandler.getInstance().writeToFile();
    }

    public static void main(String[] args) {
        Maze maze = new Maze(4, 4);
        Cell[] cells = {maze.lookupCell(0,0),
                maze.lookupCell(0,1),
                maze.lookupCell(0,2),
                maze.lookupCell(0,3),
                maze.lookupCell(1,1),
                maze.lookupCell(2,1),
                maze.lookupCell(3,1),
                maze.lookupCell(3, 2),
                maze.lookupCell(3, 0)};

        for(Cell c : cells) {
            maze.visitCell(c);
        }


        maze.addConnection(cells[0], cells[1]);
        maze.addConnection(cells[1], cells[2]);
        maze.addConnection(cells[2], cells[3]);
        maze.addConnection(cells[1], cells[4]);
        maze.addConnection(cells[4], cells[5]);
        maze.addConnection(cells[5], cells[6]);
        maze.addConnection(cells[6], cells[7]);
        maze.addConnection(cells[6], cells[8]);

        System.out.println(maze.getGraph().edgeSet());

        System.out.println(maze.getAStarShortestPathData(cells[0], cells[3]));
    }



    public static Cell getRandomCell(Maze maze) {

        int len = rand.nextInt(maze.getLength());
        int height = rand.nextInt(maze.getHeight());


        return maze.lookupCell(len, height);
    }

    public static void visualizeGraph(Graph<Cell, DefaultEdge> graph) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                mxGraph graphx = new JGraphXAdapter<>(graph);
                System.out.println(1);
                JFrame frame = new JFrame("Graph");
                frame.setSize(1000, 1000);
                frame.getContentPane().add(new mxGraphComponent(graphx));
                System.out.println(2);

                // define layout
                mxFastOrganicLayout layout = new mxFastOrganicLayout(graphx);
                System.out.println(3);

                // set some properties
                layout.setForceConstant(40); // the higher, the more separated
                layout.setDisableEdgeStyle( false); // true transforms the edges and makes them direct lines

                // layout graph
                layout.execute(graphx.getDefaultParent());
                frame.pack();
                frame.setVisible(true);
                System.out.println("Visible now");

            }
        };
        thread.start();
    }


}
