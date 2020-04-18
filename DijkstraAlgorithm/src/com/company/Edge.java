package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Edge {
    private Node node1;
    private Node node2;
    private int length;

    public Edge(Node node1, Node node2, int length) {
        this.node1 = node1;
        this.node2 = node2;
        this.length = length;
    }

    public static List<Edge> createEdges(String filePath) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
        List<Edge> createdEdges=new ArrayList<>();
        Node nodeA=null;
        Node nodeB=null;
        int lengthEdge=0;
        Edge createdEdge=null;
        String line;
        while((line=bufferedReader.readLine())!=null) {
            try {
                String[] data = line.split(" ");
                nodeA = Algorithm.findNodeByIndex(Integer.parseInt(data[0]));
                nodeB = Algorithm.findNodeByIndex(Integer.parseInt(data[1]));
                lengthEdge = Integer.parseInt(data[2]);
                createdEdge = new Edge(nodeA, nodeB, lengthEdge);
                createdEdges.add(createdEdge);
                nodeA.addEdge(createdEdge);
                nodeB.addEdge(createdEdge);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Out of bound expcetion");
            } catch (NullPointerException ex) {
                System.out.println("Null pointer exception");
            }
        }
        return createdEdges;
    }




    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
