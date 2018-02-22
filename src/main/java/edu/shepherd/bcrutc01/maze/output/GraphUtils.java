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

    public static int calculateComplexity(Graph<Cell, DefaultEdge> graph, GraphPath<Cell, DefaultEdge> path) {
        List<Cell> vertexList = path.getVertexList();
        int complexity = 0;
        for(Cell cell : vertexList) {

            if(cell.equals(path.getStartVertex()) || cell.equals(path.getEndVertex())) {
                continue;
            }

            complexity += exploreCell(graph, path, cell, null);
        }
        return complexity;
    }

    private static int exploreCell(Graph<Cell, DefaultEdge> graph, GraphPath<Cell, DefaultEdge> path,  Cell cell, Cell previousCell) {
        int complexity = 0;

        if(isHubCell(graph, cell)) {
            // This is a hub, we want to explore every connection of it


            // If this is a hub that isn't on the path, increase complexity
            if(!path.getVertexList().contains(cell)) {
                complexity++;
            }

            // Explore all connected nodes that aren't on the path and that we haven't already explored
            for(Cell c1 : getConnectedCells(graph, cell)) {
                if(!c1.equals(previousCell) && !path.getVertexList().contains(c1)) {
                    complexity += exploreCell(graph, path, c1, cell);
                }
            }

        } else if(isTransitionCell(graph, cell)) {

            // If this is a hub that isn't on the path, increase complexity
            if(!path.getVertexList().contains(cell)) {
                complexity++;
            }

            // Explore all connected nodes that aren't on the path and that we haven't already explored
            for(Cell c1 : getConnectedCells(graph, cell)) {
                if(!c1.equals(previousCell) && !path.getVertexList().contains(c1)) {
                    complexity += exploreCell(graph, path, c1, cell);
                }
            }
        } else {
            // This is an end point, add one
            if(!path.getVertexList().contains(cell)) {
                complexity++;
            }
        }

        return complexity;
    }

    private static boolean isTransitionCell(Graph<Cell, DefaultEdge> graph, Cell cell) {
        return graph.outDegreeOf(cell) == 2;
    }

    private static List<Cell> getConnectedCells(Graph<Cell, DefaultEdge> graph, Cell cell) {
        List<Cell> connected = new ArrayList<>();

        for(DefaultEdge edge : graph.outgoingEdgesOf(cell)) {
            Cell source = graph.getEdgeSource(edge);
            Cell target = graph.getEdgeTarget(edge);

            if(!target.equals(cell)) {
                if(!connected.contains(target)) {
                    connected.add(target);
                }
            }

            if(!source.equals(cell)) {
                if(!connected.contains(source)) {
                    connected.add(source);
                }
            }

        }

        return connected;
    }

    private static boolean isHubCell(Graph<Cell, DefaultEdge> graph, Cell cell) {
        return graph.outDegreeOf(cell) > 2;
    }

}
