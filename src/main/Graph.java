package main;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;
    private Set<Edge> edges;

    public Graph() {
        nodes = new HashSet<>();
        edges = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        if (nodes.contains(edge.getNode1()) && nodes.contains(edge.getNode2())) {
            edges.add(edge);
        } else {
            throw new IllegalArgumentException("Both nodes must be in the graph.");
        }
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }
}
