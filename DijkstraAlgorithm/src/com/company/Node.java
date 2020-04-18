package com.company;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Edge> edges;
    private int id;

    public Node(int id)
    {
        this.id=id;
        edges=new ArrayList<>();
    }

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }
    public List<Node> findNeighbours()
    {
        List<Node> neighbours=new ArrayList<>();
        for(Edge edge: edges)
        {
            if(edge.getNode1().equals(this))
                neighbours.add(edge.getNode2());
            else
                neighbours.add(edge.getNode1());
        }
        return neighbours;
    }






    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
