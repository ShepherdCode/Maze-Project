package edu.shepherd.bcrutc01.maze.test;

import edu.shepherd.bcrutc01.maze.generators.MazeGenerator;
import edu.shepherd.bcrutc01.maze.output.GraphUtils;
import edu.shepherd.bcrutc01.maze.structure.Cell;
import edu.shepherd.bcrutc01.maze.structure.Maze;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Class to handle the testing for Wilson's algorithm.
 * The goal of this class is to test that the Mazes generated are valid (as detailed in the readme)
 *
 * @author Brian Crutchley
 * @version 1.0
 */
public class UnbiasedMazeTest {

    /**
     * Tests for completeness by checking if every cell has either an input edge or an output edge
     */
    @Test
    public void testCompleteness() {
        for(int i = 0; i < 1000; i++) {
            Maze testMaze = new Maze(100,100);
            MazeGenerator testMazeGenerator = new MazeGenerator(testMaze);
            testMazeGenerator.generate();
            Graph<Cell, DefaultEdge> g = testMaze.getGraph();

            for(Cell cell : g.vertexSet()) {
                assertTrue(g.inDegreeOf(cell) > 0 || g.outDegreeOf(cell) > 0);
            }
        }
    }

    /**
     * Tests for reachability by checking if the number of cells that can be reached from the origin is the same as the number of cells in this graph
     */
    @Test
    public void testReachability() {
        for(int i = 0; i < 1000; i++) {
            Maze testMaze = new Maze(100,100);
            MazeGenerator testMazeGenerator = new MazeGenerator(testMaze);
            testMazeGenerator.generate();
            Graph<Cell, DefaultEdge> graph = testMaze.getGraph();
            Cell startNode = testMaze.lookupCell(0, 0);

            DepthFirstIterator<Cell, DefaultEdge> iterator = new DepthFirstIterator<>(graph, startNode);

            int count = 0;
            while(iterator.hasNext()) {
                count++;
                iterator.next();
            }

            assertTrue(count == testMaze.getLength() * testMaze.getHeight());
        }
    }
    /**
     * Tests the bias values by checking if the actual distribution of horizontal and vertical walks is similar to the expected distribution
     */
    @Test
    public void testBiasValues() {
        double biasTotal = 0;
        double total = 0;

        for(int i = 0; i < 1000; i++) {
            Maze testMaze = new Maze(100,100);
            MazeGenerator testMazeGenerator = new MazeGenerator(testMaze);
            testMazeGenerator.generate();

            biasTotal += testMazeGenerator.getHorizontalCount() / testMazeGenerator.getTotalCount();
            total++;
        }

        assertTrue(Math.abs((biasTotal / total) - .50) < 5);
    }

    /**
     * Test basic complexity structures using a preset maze.
     *
     * The preset maze is as follows:
     *
     *    0   1   2
     * 0  x - x   x
     *        |   |
     * 1  x - x - x
     *        |   |
     * 2  x - x   x
     *
     */
    @Test
    public void testComplexityValues() {
        Maze maze = new Maze(3, 3);
        Cell[] cells = {maze.lookupCell(0, 0),
                maze.lookupCell(0, 1),
                maze.lookupCell(1, 0),
                maze.lookupCell(0, 2),
                maze.lookupCell(1, 1),
                maze.lookupCell(1,2),
                maze.lookupCell(2, 1),
                maze.lookupCell(2, 0),
                maze.lookupCell(2, 2)};

        for(Cell c : cells) {
            maze.visitCell(c);
        }

        maze.addConnection(cells[0], cells[2]);
        maze.addConnection(cells[2], cells[4]);
        maze.addConnection(cells[4], cells[1]);
        maze.addConnection(cells[4], cells[6]);
        maze.addConnection(cells[4], cells[5]);
        maze.addConnection(cells[5], cells[3]);
        maze.addConnection(cells[6], cells[7]);
        maze.addConnection(cells[6], cells[8]);


        assertTrue(maze.getAStarShortestPathData(cells[0], cells[2], 0.50).getComplexity() == 0);
        assertTrue(maze.getAStarShortestPathData(cells[0], cells[3], 0.50).getComplexity() == 4);
        assertTrue(maze.getAStarShortestPathData(cells[0], cells[4], 0.50).getComplexity() == 0);
        assertTrue(maze.getAStarShortestPathData(cells[0], cells[6], 0.50).getComplexity() == 3);
    }




}
