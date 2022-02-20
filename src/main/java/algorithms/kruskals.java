package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;

import datastructures.DisjSets;
import helpers.Edge;

public final class kruskals {
    public static List<Edge> runKruskals(List<Edge> edges, HashMap<String, Integer> vMap){
        int edgesAccepted = 0;
        int num_Of_Vertices = vMap.size();
        DisjSets ds = new DisjSets(num_Of_Vertices+1);
        PriorityQueue<Edge> pq  = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));

        for(Edge e : edges) pq.add(e);

        Edge e;
        List<Edge> result = new ArrayList<>();

        while (edgesAccepted < num_Of_Vertices - 1)
        {
            e = pq.remove();
            int uset = ds.find( vMap.get(e.vertex1) );
            int vset = ds.find( vMap.get(e.vertex2) );
            if (uset != vset) 
            {
                edgesAccepted++;
                ds.union(uset, vset);
                result.add(e);
            }

        }

        return result;

    }
    
}
