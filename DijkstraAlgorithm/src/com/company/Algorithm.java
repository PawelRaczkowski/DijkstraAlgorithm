package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {
    private static List<Node> nodes;
    private static List<Edge> edges;

    public Algorithm(String nodesFilePath, String edgesFilePath) throws IOException {
        this.nodes=new ArrayList<>();
        this.nodes=Algorithm.createNodes(nodesFilePath);
        this.edges=new ArrayList<>();
        this.edges=Edge.createEdges(edgesFilePath);
    }

    public static Node findNodeByIndex(int parseInt) {
        for(Node node : nodes)
        {
            if(node.getId()==parseInt)
            {
                return node;
            }
        }
        return null;
    }
    public void printNetwork()
    {
        for(Edge edge:edges)
        {
            System.out.println(edge.getNode1().getId() + " " + edge.getNode2().getId() + " " + edge.getLength());
        }
    }
    public static List<Node> createNodes(String filePath)
    {
        List<Node> createdNodes=new ArrayList<>();
        Node createdNode=null;
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            line=bufferedReader.readLine();
            String[] data=line.split(" ");
            int numberOfNodes=Integer.parseInt(data[0]);
            for(int i=0;i<numberOfNodes;i++) {
                createdNode = new Node(i);
                createdNodes.add(createdNode);
            }
        }
        catch(IOException ex)
        {
            System.out.println("IO exception");
        }
        catch(IndexOutOfBoundsException ex)
        {
            System.out.println("Out of bound");
        }
        return createdNodes;
    }

    public List<Integer> Dijkstra(int start, int finish)
    {
        List<Node> observedNodes=new ArrayList<>();
        List<Node> notObservedNodes=new ArrayList<>(nodes);

        int [] costsToNodes=new int[notObservedNodes.size()];
        int [] previousNodes=new int[notObservedNodes.size()];
        for(int i=0; i<costsToNodes.length;i++)
        {
            previousNodes[i]=-1;
            if(i==start)
                costsToNodes[i]=0;
            else
                costsToNodes[i]=Integer.MAX_VALUE;
        }
        while(notObservedNodes.size()>0)
        {
           // System.out.println("Go go power rangers");
            int min=Integer.MAX_VALUE;
            int chosenIndex=-1;
            for(int i=0;i<costsToNodes.length;i++)
            {
                if(isInCollection(notObservedNodes,Algorithm.findNodeByIndex(i))) {
                    if (costsToNodes[i] < min) {
                        min = costsToNodes[i];
                        chosenIndex = i;
                    }
                }
            }
            Node chosenNode=Algorithm.findNodeByIndex(chosenIndex);
            observedNodes.add(chosenNode);
            notObservedNodes.remove(chosenNode);
            List<Node> neighbours=chosenNode.findNeighbours();

            for(Node node: neighbours)
            {
               // System.out.println(node.getId());
                if(!isInCollection(notObservedNodes,node))
                    continue;
                else
                {
                    int wageBetweenNodes=returnEdgeBetweenNodes(chosenNode,node);
                    if(costsToNodes[node.getId()]>costsToNodes[chosenNode.getId()]+wageBetweenNodes)
                    {
                        costsToNodes[node.getId()]=costsToNodes[chosenNode.getId()]+wageBetweenNodes;
                        previousNodes[node.getId()]=chosenNode.getId();
                    }
                }
            }
        }
        List<Integer> chosenPath=new ArrayList<>();
        int indexNode=finish;

        while(true)
        {
            chosenPath.add(indexNode);
            indexNode=previousNodes[indexNode];
            if(indexNode==start)
            {
                chosenPath.add(indexNode);
                break;
            }
        }
        return chosenPath;
    }

    private boolean isInCollection(List<Node> notObservedNodes, Node node) {
        for(Node node1: notObservedNodes)
        {
            if(node1.getId()==node.getId())
                return true;
        }
        return false;
    }

    private int returnEdgeBetweenNodes(Node chosenNode, Node node) {
        for(Edge edge:edges)
        {
            if((edge.getNode1().equals(chosenNode) && edge.getNode2().equals(node) ) ||
                    (edge.getNode2().equals(chosenNode) && edge.getNode1().equals(node)))
            {
                return edge.getLength();
            }
        }
        return -1;
    }


}
