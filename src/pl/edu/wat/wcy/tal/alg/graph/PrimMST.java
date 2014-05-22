package pl.edu.wat.wcy.tal.alg.graph;

import java.util.*;

/**
 * Created by Konrad on 11.05.14.
 */
public class PrimMST {

    public static Graph getMST(Graph in){

        Graph mst = new Graph();

        List<Vertex> inVertex = in.getVertices();
        List<Edge> inEdges = in.getEdges();

        List<Vertex> verticesMST = new ArrayList<Vertex>();
        List<Edge> edgesMST = new ArrayList<Edge>();

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();

        //Losowanie wierzchołka początkoweego

        Random random = new Random();

        Vertex currentVertex = inVertex.get(random.nextInt(inVertex.size()));
        currentVertex.setMarked(true);
        verticesMST.add(currentVertex);

        while(verticesMST.size()!=inVertex.size()){

            //dodawanie krawędzi przyległych do MST do kolejki
            for (Vertex v : verticesMST){
                for (Edge e : inEdges){
                    if  (!e.isMarked() && ((e.getFrom().equals(v) && !e.getTo().isMarked()) || (e.getTo().equals(v) && !e.getFrom().isMarked())))
                        queue.add(e);
                }
            }

            //wybieramoe majtanszej krawedzi....
            Edge currentEdge = queue.poll();
            currentEdge.setMarked(true);
            edgesMST.add(currentEdge);
            //i przyleglego do niej wierzcholka
            currentVertex = currentEdge.getFrom().isMarked() ? currentEdge.getTo() : currentEdge.getFrom();
            currentVertex.setMarked(true);
            verticesMST.add(currentVertex);

            queue.clear();

        }


        /*System.out.println("MST\n\n\n");
        for (Vertex v : verticesMST)
            System.out.println(v.getName());
        System.out.println("\n\n\n\n\n\n\n");
        for (Edge e: edgesMST)
            System.out.println(e.toString());
        System.out.println("\n!MST\n\n");*/

        return in;
    }

}