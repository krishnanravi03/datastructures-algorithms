package com.datastructures;

import org.json.simple.JSONObject;
import projects.WordPuzzle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<String> puzzle = WordPuzzle.run(10, 10, false);

        URL res = App.class.getClassLoader().getResource("dictionary.txt");
        List<String> solution = new ArrayList<>();
        for(int i=1; i<puzzle.size(); i++){
            solution.add(puzzle.get(i));
        }
        JSONObject response = new JSONObject();
        response.put("puzzle", puzzle.get(0));
        response.put("solution", solution);
        System.out.println(response.toJSONString());


    }
}
