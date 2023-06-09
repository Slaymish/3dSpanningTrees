package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

public class SpanningTrees {

    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;
    private List<Edge> minimumSpanningTree;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String filename = getFilename(args);
            SpanningTrees spanningTrees = new SpanningTrees();
            spanningTrees.run(filename);
        });
    }

    /**
     * Get filename from the user input.
     * @param args command line arguments
     * @return filename
     */
    private static String getFilename(String[] args) {
        String filename;
        if (args.length != 1) {
            filename = loadFileName();
            System.out.println("Loading file: " + filename);
        } else {
            System.out.println("Loading file: " + args[0]);
            filename = args[0];
        }
        return filename;
    }

    /**
     * Open file dialog to get file from user.
     * @return file path
     */
    private static String loadFileName() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Obj Files", "obj");
        chooser.setFileFilter(filter);
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        chooser.setCurrentDirectory(desktop);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No file selected");
            System.exit(0);
        }
        return "";
    }

    /**
     * Run the process of building and saving the minimum spanning tree.
     * @param filename input file name
     */
    public void run(String filename){
        nodes = new HashMap<>();
        edges = new HashMap<>();
        minimumSpanningTree = new ArrayList<>();
        try {
            loadObjFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graph graph = createGraph();
        minimumSpanningTree = findMinimumSpanningTree(graph);
        saveObjFile();
        System.out.println("Done");
        System.exit(0);
    }

    /**
     * Load OBJ file and parse its lines to vertices and edges.
     * @param filename input file name
     * @throws IOException if file not found or not accessible
     */
    private void loadObjFile(String filename) throws IOException {
        File file = new File(filename);
        if(!file.exists()) {
            System.out.println("Obj File not found");
            return;
        }
        List<String> lines = Files.readAllLines(file.toPath());
        int vertexIndex = 1;
        int edgeIndex = 1;
        for(String line : lines) {
            if(line.startsWith("v ")) {
                parseVertex(line, vertexIndex++);
            } else if(line.startsWith("l ")) {
                parseLine(line, edgeIndex++);
            }
        }
        System.out.println("Loaded " + nodes.size() + " verts and " + edges.size() + " edges");
    }

    /**
     * Parse vertex line and add it to nodes map.
     * @param line line from the input file
     * @param index vertex index
     */
    private void parseVertex(String line, int index) {
        String[] parts = line.split(" ");
        if(parts.length != 4) {
            System.out.println("Invalid vertex format at line: " + index);
            return;
        }
        Node node = new Node(index, Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
        nodes.put(index, node);
    }

    /**
     * Parse edge line and add it to edges map.
     * @param line line from the input file
     * @param index edge index
     */
    private void parseLine(String line, int index) {
        String[] parts = line.split(" ");
        if(parts.length != 3) {
            return;
        }
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
            return;
        }
        edges.put(index, new Edge(nodes.get(a), nodes.get(b)));
    }

    /**
     * Create a graph using nodes and edges map.
     * @return graph
     */
    private Graph createGraph() {
        System.out.println("Creating graph");
        Graph graph = new Graph();
        nodes.values().forEach(graph::addNode);
        edges.values().forEach(graph::addEdge);
        return graph;
    }

    /**
     * Find minimum spanning tree using Kruskal's algorithm.
     * @param graph graph
     * @return list of edges that forms the minimum spanning tree
     */
    private List<Edge> findMinimumSpanningTree(Graph graph) {
        System.out.println("Finding minimum spanning tree");
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        queue.addAll(graph.getEdges());
        UnionFind uf = new UnionFind(graph.getNodes());
        List<Edge> mst = new ArrayList<>();
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            Node node1 = edge.getNode1();
            Node node2 = edge.getNode2();
            if(uf.find(node1) != uf.find(node2)) {
                mst.add(edge);
                uf.union(node1, node2);
            }
        }
        System.out.println("Found " + mst.size() + " edges in minimum spanning tree");
        return mst;
    }

    /**
     * Save the minimum spanning tree to OBJ file.
     */
    private void saveObjFile() {
        System.out.println("Saving file");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OBJ files", "obj");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String filename = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filename.toLowerCase().endsWith(".obj")) {
            filename += ".obj";
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(new File(filename)));
            minimumSpanningTree.forEach(edge -> {
                writer.println("v " + edge.getNode1().toString());
                writer.println("v " + edge.getNode2().toString());
                writer.println("l " + edge.getNode1().getId() + " " + edge.getNode2().getId());
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved file to " + filename);
    }
}
