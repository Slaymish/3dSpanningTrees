package main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<Node, Node> parent;
    private Map<Node, Integer> rank;

    public UnionFind(Collection<Node> nodes) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (Node node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }


    public Node find(Node node) {
        if (parent.get(node).equals(node)) {
            return node;
        } else {
            Node result = find(parent.get(node));
            parent.put(node, result);
            return result;
        }
    }

    public void union(Node node1, Node node2) {
        Node root1 = find(node1);
        Node root2 = find(node2);

        if (root1.equals(root2)) {
            return;
        }

        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}
