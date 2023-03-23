package test;

import main.Arc;
import main.Graph;
import main.GraphColoring;
import main.Vertex;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphColoringTest {
    public static void main(String[] args) {
    }
    private int fileNumber;
    private int numOfColor;
    private GraphColoring g;
    private Graph graph;


    private void readText(String fileName) throws IOException {
        numOfColor = 0;
        graph = new Graph();
        try(Scanner sc = new Scanner(new FileReader(fileName))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(!line.isEmpty()){
                    if(line.charAt(0) != '#'){
                        if(numOfColor == 0){
                            String[] sourceArray = line.split(" = ");
                            numOfColor = Integer.valueOf(sourceArray[1]);
                        }else{
                            String[] sourceArray = line.split(",");
                            int id1 = Integer.valueOf(sourceArray[0]);
                            int id2 = Integer.valueOf(sourceArray[1]);
                            Vertex v1 = new Vertex(id1, numOfColor);
                            Vertex v2 = new Vertex(id2, numOfColor);
                            // initialize graph
                            if(!graph.contains(id1)){
                                graph.addVertex(v1);
                            }
                            if(!graph.contains(id2)){
                                graph.addVertex(v2);
                            }
                            graph.addEdge(v1, v2);
                        }
                    }
                }
            }
        }
    }

//    @Test
//    public void test0() throws IOException {
//        fileNumber = 0;
//        readText();
//        g = new GraphColoring();
//        Assert.assertEquals(true, g.CSP(graph));
//    }

    @Test
    public void test1() throws IOException {
        fileNumber = 1;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void test2() throws IOException {
        fileNumber = 2;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void test3() throws IOException {
        fileNumber = 3;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void test4() throws IOException {
        fileNumber = 4;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void test5() throws IOException {
        fileNumber = 5;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void test6() throws IOException {
        fileNumber = 6;
        String fileName = "src/test" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void demo1() throws IOException {
        fileNumber = 1;
        String fileName = "src/demo" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }

    @Test
    public void demo2() throws IOException {
        fileNumber = 2;
        String fileName = "src/demo" + fileNumber;
        readText(fileName);
        g = new GraphColoring();
        Assert.assertEquals(true, g.CSP(graph));
    }
}
