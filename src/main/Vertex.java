package main;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>, Cloneable{
    private int index;
    private int color;
    public List<Vertex> neighbors= new ArrayList<Vertex>();
    public List<Integer> domain = new ArrayList<Integer>();
    private int noc;

    public Vertex(Vertex v){
        index = v.getIndex();
        color = v.getColor();
        neighbors = v.neighbors;
        for (Integer i:v.domain) {
            domain.add(i);
        }
        noc = v.noc;
    }

    public Vertex(int id, int numofcolor){
        index = id;
        color = 0;
        for(int i=0; i<numofcolor; i++){ // initialize domain
            domain.add(i, i+1);
        }
        noc = numofcolor;
    }

    public int getIndex() {
        return index;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        color = c;
    }

    @Override
    public int compareTo(Vertex v){
        if(v == null) return -1;

        if(v.getIndex() == index) return 0;

        // heuristic for variables: MRV(min remaining values)
        int d1 = domain.size();
        int d2 = v.domain.size();
        if(d1 < d2) return -1;
        else if(d1 > d2) return 1;
        else{
            // choose the variable that is involved in more constraints
            int n1 = neighbors.size();
            int n2 = v.neighbors.size();
            if(n1 > n2) return -1;
            else if(n1 < n2) return 1;
            else return 0;
        }
    }

    public void selectColor(){
        List<Integer> ns = new ArrayList<Integer>(); // numbers of constrained variables of each value
        for(int i=0; i<noc; i++){ // initialize
            ns.add(i, 0);
        }
        for(int i=0; i<neighbors.size(); i++){
            Vertex v = neighbors.get(i);
            for(int j=0;j<v.domain.size();j++){
                int id = v.domain.get(j) - 1;
                ns.set(id, ns.get(id) + 1);
            }
        }
        int minNum = neighbors.size();
        int indexOfSelectedColor = domain.size();
        for(int i=0; i<domain.size();i++){
            int currentNum = ns.get(domain.get(i) - 1);
            if(currentNum < minNum){
                minNum = currentNum;
                indexOfSelectedColor = i;
            }
        }
        if(indexOfSelectedColor >= domain.size()){
            color = domain.remove(0);
        }else{
            color = domain.remove(indexOfSelectedColor); // least constrained value
        }
    }

    @Override
    public Object clone(){
        Vertex v = null;
        try{
            v = (Vertex)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return v;
    }
}
