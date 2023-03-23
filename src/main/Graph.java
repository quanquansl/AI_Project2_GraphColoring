package main;

import java.util.PriorityQueue;
import java.util.Queue;

public class Graph implements Cloneable{
    public Queue<Vertex> graph = new PriorityQueue<>();

    public Graph(){}

    public Graph(Graph g){
        for (Vertex v:g.graph) {
            Vertex newVertex = new Vertex(v);
            graph.add(newVertex);
        }
    }

    public boolean contains(int id){
        for (Vertex v:graph) {
            if(v.getIndex() == id){
                return true;
            }
        }
        return false;
    }

    public void addVertex(Vertex v){
        graph.add(v);
    }

    public void addEdge(Vertex v1, Vertex v2){
        graph.forEach(v -> {
            if(v.getIndex() == v1.getIndex()){
                v.neighbors.add(v2);
            }
            if(v.getIndex() == v2.getIndex()){
                v.neighbors.add(v1);
            }
        });
    }

    @Override
    public Object clone(){
        Graph g = null;
        try{
            g = (Graph)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return g;
    }
}
