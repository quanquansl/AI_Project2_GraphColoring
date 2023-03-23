package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphColoring {
    public boolean CSP(Graph graph){
        Queue<Vertex> assignment = new LinkedList<Vertex>();
        return backtrack(graph, assignment);
    }

    public boolean backtrack(Graph graph, Queue<Vertex> assignment){
        if(graph.graph.isEmpty()){
            while(!assignment.isEmpty()){
                Vertex v = assignment.remove();
                System.out.println("Color of vertex" + v.getIndex() + ":" + v.getColor());
            }
            return true;
        }
        // Graph copiedGraph1 = new Graph((Graph)graph.clone());
        Graph copiedGraph1 = new Graph(graph);
        Queue<Vertex> copiedAssignment = new LinkedList<Vertex>();
        for (Vertex vv:assignment) {
            // Vertex vvv = new Vertex((Vertex)vv.clone());
            Vertex vvv = new Vertex(vv);
            copiedAssignment.add(vvv);
        }
        Vertex v = graph.graph.remove();
        // Graph copiedGraph2 = new Graph((Graph)graph.clone());
        Graph copiedGraph2 = new Graph(graph);
        while(!v.domain.isEmpty()){
            v.selectColor();
            // Vertex copiedVertex = new Vertex((Vertex)v.clone());
            Vertex copiedVertex = new Vertex(v);
            // check if constraint satisfied with assignment
            if(isConstraintSatisfied(v, assignment)){
                assignment.add(v);
                if(AC3(graph, assignment)){
                    if(backtrack(graph, assignment)) return true;
                }
                // v = new Vertex((Vertex)copiedVertex.clone());
                v = (Vertex)copiedVertex.clone();
                v.setColor(0);
            }
//            graph = new Graph((Graph)copiedGraph2.clone());
//            assignment.clear();
//            for (Vertex vv:copiedAssignment) {
//                Vertex vvv = new Vertex((Vertex)vv.clone());
//                assignment.add(vvv);
//            }
            graph = (Graph)copiedGraph2.clone();
            assignment.clear();
            for (Vertex vv:copiedAssignment) {
                Vertex vvv = (Vertex)vv.clone();
                assignment.add(vvv);
            }
        }
//        graph = new Graph((Graph)copiedGraph1.clone());
        graph = (Graph)copiedGraph1.clone();
//        assignment.clear();
//        for (Vertex vv:copiedAssignment) {
//            Vertex vvv = new Vertex((Vertex)vv.clone());
//            assignment.add(vvv);
//        }
        return false;
    }

    public boolean AC3(Graph graph, Queue<Vertex> assignment){
        for (Vertex v:assignment) {
            v.domain.clear();
            v.domain.add(v.getColor());
        }
        // initialize the queue of arcs
        Queue<Arc> arcs = new LinkedList<Arc>();
        for(Vertex v1:graph.graph){
            for(int i=0;i<v1.neighbors.size();i++){
                Vertex v2 = v1.neighbors.get(i);
                for (Vertex v:assignment) {
                    if(v.getIndex() == v2.getIndex()){
                        v2 = v;
                    }
                }
                Arc arc = new Arc(v1, v2);
                arcs.add(arc);
            }
        }

        while(!arcs.isEmpty()){
            Arc arc = arcs.remove();
            Vertex v = removeInconsistentValues(arc);
            if(v!=null){
                if(v.domain.isEmpty() && v.getColor() == 0){
                    return false;
                }
                Vertex v1 = arc.getVertex1();
                if(graph.graph.remove(v1)){
                    v1 = v;
                    graph.graph.add(v1);
                }
                v1 = v;
                for(int i=0;i<v1.neighbors.size();i++){
                    Vertex v2 = v1.neighbors.get(i);
                    Arc newArc = new Arc(v2, v1);
                    arcs.add(newArc);
                }
            }
        }
        return true;
    }

    public Vertex removeInconsistentValues(Arc a){
        Vertex v = null;
        Vertex v1 = a.getVertex1();
        Vertex v2 = a.getVertex2();
        for(int i=0;i<v1.domain.size();i++){
            int color1 = v1.domain.get(i);
            boolean consistent = false;
            for(int j=0;j<v2.domain.size();j++){
                int color2 = v2.domain.get(j);
                if(color2 != color1){
                    consistent = true;
                }
            }
            if(consistent == false){
                v1.domain.remove(i);
                v = v1;
            }
        }
        return v;
    }

    public boolean isConstraintSatisfied(Vertex v, Queue<Vertex> assignment){
        for(Vertex assignedVertex:assignment){
            if(v.neighbors.contains(assignedVertex)){
                if(assignedVertex.getColor() == v.getColor()) return false;
            }
        }
        return true;
    }

}
