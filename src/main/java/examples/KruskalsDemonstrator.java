package examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algorithms.kruskals;
import configs.AppConfiguration;
import helpers.Edge;

public final class KruskalsDemonstrator {
    public static void run(){
        List<Edge> edges = new ArrayList<>();
        HashMap<String, Integer> vertexMap = new HashMap<>();
        int num_Of_Vertices = 1;
        
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfiguration.map))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String v1 = values[0];
                vertexMap.put(v1, num_Of_Vertices);
                num_Of_Vertices++;
                int i=1;
                while(i+1 < values.length){
                    if(values[i].compareTo(v1) > 0){
                        Edge e = new Edge(v1, values[i], Integer.parseInt(values[i+1]));
                        edges.add(e);
                    }
                    i+=2;
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error reading the file: " + ex.toString());

        }

        List<Edge> minimumSpanningTree = kruskals.runKruskals(edges, vertexMap);
        int totalDistance = 0;
        for(Edge e : minimumSpanningTree){
            System.out.println(e.vertex1 + ", " +e.vertex2 + " " + e.weight.toString());
            totalDistance+= e.weight;
        }
        System.out.println();
        System.out.println("Total distance in the minimum spanning tree is " + totalDistance);
        

    }
}
