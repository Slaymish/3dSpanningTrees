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

    /**
     * spanning tree based on distance (MST)
     * Using Kruskal's algorithm
     * @return
     * @param double
     */
    public double getWeight1() {
        return Math.sqrt(
                Math.pow(node1.getX() - node2.getX(), 2) +
                        Math.pow(node1.getY() - node2.getY(), 2) +
                        Math.pow(node1.getZ() - node2.getZ(), 2));
    }

    /**
     * spanning tree based on angle of world origin
     * @return
     * @param double
     */
    public double getWeight2() {
        int xO = 0;
        int yO = 0;
        int zO = 0;

        // TODO: return distance from world coordinates
        // TODO: angle of constructed triange from points

        int x1 = (int) node1.getX();
        int y1 = (int) node1.getY();
        int z1 = (int) node1.getZ();

        int x2 = (int) node2.getX();
        int y2 = (int) node2.getY();
        int z2 = (int) node2.getZ();


        double dist01 = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) + Math.pow(z1-z2, 2));
        double dist02 = Math.sqrt(Math.pow(x1-xO, 2) + Math.pow(y1-yO, 2) + Math.pow(z1-zO, 2));
        double dist12 = Math.sqrt(Math.pow(x2-xO, 2) + Math.pow(y2-yO, 2) + Math.pow(z2-zO, 2));

        double angle = Math.acos((Math.pow(dist02, 2) + Math.pow(dist12, 2) - Math.pow(dist01, 2)) / (2 * dist02 * dist12));

        return angle;
    }

}