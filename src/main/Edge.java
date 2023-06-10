package main;

public class Edge {
    private Node node1;
    private Node node2;

    private static double xO;
    private static double yO;
    private static double zO;

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
        // TODO: return distance from world coordinates
        // TODO: angle of constructed triange from points

        double x1 = node1.getX();
        double y1 = node1.getY();
        double z1 = node1.getZ();

        double x2 = node2.getX();
        double y2 = node2.getY();
        double z2 = node2.getZ();


        double dist01 = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) + Math.pow(z1-z2, 2));
        double dist02 = Math.sqrt(Math.pow(x1-xO, 2) + Math.pow(y1-yO, 2) + Math.pow(z1-zO, 2));
        double dist12 = Math.sqrt(Math.pow(x2-xO, 2) + Math.pow(y2-yO, 2) + Math.pow(z2-zO, 2));

        double angle = Math.acos((Math.pow(dist02, 2) + Math.pow(dist12, 2) - Math.pow(dist01, 2)) / (2 * dist02 * dist12));

        return angle;
    }

    public static void setOrigin(double x, double y, double z) {
        xO = x;
        yO = y;
        zO = z;
    }

}