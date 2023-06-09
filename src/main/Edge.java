package main;

public class Edge {
    private Node node1;
    private Node node2;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public double getWeight() {
        return Math.sqrt(
                Math.pow(node1.getX() - node2.getX(), 2) +
                        Math.pow(node1.getY() - node2.getY(), 2) +
                        Math.pow(node1.getZ() - node2.getZ(), 2));
    }

}