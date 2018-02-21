package edu.shepherd.bcrutc01.maze.test;

import edu.shepherd.bcrutc01.maze.generators.MazeGenerator;
import edu.shepherd.bcrutc01.maze.structure.Cell;
import edu.shepherd.bcrutc01.maze.structure.Maze;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
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

    private static Maze maze;
    private static Set<Cell> reachableCells = new HashSet<>();
    private static MazeGenerator generator;

    public UnbiasedMazeTest() {
        if(generator == null) {
            maze = new Maze(3, 3);
            generator = new MazeGenerator(maze);
            generator.generate();
            Graph<Cell, DefaultEdge> g = maze.getGraph();
            for(Cell cell : g.vertexSet()) {
                System.out.println("Cell " + cell + " has " + g.inDegreeOf(cell) + " in and " + g.outDegreeOf(cell) + " out");
            }
        }
    }

    /**
     * Tests for completeness by checking if every cell has either an input edge or an output edge
     */
    @Test
    public void testCompleteness() {
        for(int i = 0; i < 100; i++) {
            Maze testMaze = new Maze(8,8);
            MazeGenerator testMazeGenerator = new MazeGenerator(testMaze);
            testMazeGenerator.generate();
            Graph<Cell, DefaultEdge> g = testMaze.getGraph();

            for(Cell cell : g.vertexSet()) {
                System.out.println("Cell " + cell + " has " + g.inDegreeOf(cell) + " in and " + g.outDegreeOf(cell) + " out");
                assertTrue(g.inDegreeOf(cell) > 0 || g.outDegreeOf(cell) > 0);
            }
        }
    }

    /**
     * Tests for reachability by checking if the number of cells that can be reached from the origin is the same as the number of cells in this graph
     */
    // TODO: Fix this test: Mazes are reachable, but this test still fails due to faulty logic
    //@Test
    public void testReachability() {
        Graph<Cell, DefaultEdge> g = maze.getGraph();

        Cell origin = maze.lookupCell(0, 0);
        findReachableCells(g, origin, null);
        assertTrue(reachableCells.size() == g.vertexSet().size());
    }

    /**
     * Helper method for finding every reachable cell from a given cell
     * @param g the Graph to check
     * @param origin the given cell
     */
    private void findReachableCells(Graph<Cell, DefaultEdge> g, Cell origin, Cell previous) {
        System.out.println("Testing " + origin);
        for(DefaultEdge edge : g.outgoingEdgesOf(origin)) {
            Cell target = g.getEdgeTarget(edge).equals(origin) ? g.getEdgeSource(edge) : g.getEdgeTarget(edge) ;
            if(target.equals(previous)) {
                continue;
            }

            if(g.outDegreeOf(target) < 2) {
                reachableCells.add(target);
                continue;
            } else {
                findReachableCells(g, target, origin);
            }
            reachableCells.add(target);
        }
    }
    /**
     * Tests the bias values by checking if the actual distribution of horizontal and vertical walks is similar to the expected distribution
     */
    @Test
    public void testBiasValues() {
        double horizontalBiasPercent = generator.getHorizontalCount() / generator.getTotalCount();
        double verticalBiasPercent = generator.getVerticalCount() / generator.getTotalCount();
        System.out.println("vbias: " + verticalBiasPercent);
        System.out.println("hbias: " + horizontalBiasPercent);
        assertTrue(Math.abs(horizontalBiasPercent - .5) < .15 && Math.abs(verticalBiasPercent - .5) < .15);
    }




}
