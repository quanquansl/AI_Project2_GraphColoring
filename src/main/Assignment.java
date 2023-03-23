package main;

import java.util.Objects;

public class Assignment {
    private int vertexID;
    private int colorID;

    public int getColorID() {
        return colorID;
    }

    public int getVertexID() {
        return vertexID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public void setVertexID(int vertexID) {
        this.vertexID = vertexID;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        Assignment a = (Assignment)obj;
        if(vertexID == a.getVertexID()) return true;
        else return false;
    }
}
