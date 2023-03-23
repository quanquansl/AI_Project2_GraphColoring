package main;

public class Arc{
    private Vertex v1;
    private Vertex v2;

    public Arc(Vertex va, Vertex vb){
        v1 = va;
        v2 = vb;
    }

    public Vertex getVertex1(){
        return v1;
    }

    public Vertex getVertex2(){
        return v2;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        Arc a = (Arc)obj;
        Vertex va = a.getVertex1();
        Vertex vb = a.getVertex2();
        if(v1.getIndex() == va.getIndex() && v2.getIndex() == vb.getIndex()) return true;
        else if(v1.getIndex() == vb.getIndex() && v2.getIndex() == va.getIndex()) return true;
        else return false;
    }
}
