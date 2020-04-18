package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Algorithm algorithm=new Algorithm("C:\\Users\\Pawel\\Desktop\\nodes.txt","C:\\Users\\Pawel\\Desktop\\edges.txt");
        List<Integer> results=algorithm.Dijkstra(0,6);
        System.out.println("The shortest path:");
        for(Integer result: results)
        {
            System.out.print(result + " ");
        }
    }
}
