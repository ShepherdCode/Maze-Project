package edu.shepherd.bcrutc01.maze.output;

import edu.shepherd.bcrutc01.maze.structure.Cell;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.ArrayList;
import java.util.List;

public class GraphUtils {

    /**
     * Utility class. No constructor needed
     */
    private GraphUtils() { }

    public static int calculateComplexity2(Graph<Cell, DefaultEdge> graph, GraphPath<Cell, DefaultEdge> path) {
        List<Cell> vertexList = path.getVertexList();
        int complexity = 0;
        for(Cell cell : vertexList) {
            for(DefaultEdge edge : new ArrayList<>(graph.outgoingEdgesOf(cell))) {
                if(vertexList.contains(graph.getEdgeTarget(edge))) {
                    continue;
                }

                Cell source = graph.getEdgeSource(edge);
                Cell target = graph.getEdgeTarget(edge);
                System.out.println("Removing edge:" + edge);
                graph.removeEdge(edge);

                DepthFirstIterator<Cell, DefaultEdge> iterator = new DepthFirstIterator<>(graph, target);
                while(iterator.hasNext()) {
                    iterator.next();
                    complexity++;
                }

                graph.addEdge(source, target);
            }
        }
        return complexity;
    }

    public static int calculateComplexity(Graph<Cell, DefaultEdge> graph, GraphPath<Cell, DefaultEdge> path) {
        List<Cell> vertexList = path.getVertexList();
        int complexity = 1;
        for(Cell cell : vertexList) {

            for(DefaultEdge edge : graph.outgoingEdgesOf(cell)) {
                if(vertexList.contains(graph.getEdgeTarget(edge))) {
                    continue;
                }
                complexity += countDepth(graph, edge, 0);
            }
        }
        return complexity;
    }

    private static int countDepth(Graph<Cell, DefaultEdge> graph, DefaultEdge edge, int depth) {
        Cell target = graph.getEdgeTarget(edge);
        int actualDepth = depth;

        for(DefaultEdge nextEdge : graph.outgoingEdgesOf(target)) {
            if(graph.getEdgeTarget(nextEdge).equals(target)) {
                continue;
            }

            if(edge.equals(nextEdge)) {
                continue;
            }

            actualDepth += countDepth(graph, nextEdge, depth) + 1;
        }

        return actualDepth;
    }



}
