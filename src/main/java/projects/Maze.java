package projects;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

import datastructures.DisjSets;
import helpers.Cell;


public class Maze {
    
    //run with user prompt
    public static String run()
    {
        //Get Inputs 
        int[] dimension = GetDimensionFromUserInput();
        return GetMaze(dimension[0], dimension[1]);

    }

    //run with passed inputs

    public static String run(int rows, int columns){
        return GetMaze(rows, columns);

    }

    private static String GetMaze(int rows, int columns){
        //Generate Maze
        List<List<Cell>> maze = BuildMaze(rows, columns);

        //Maze to string
        String mazeOutput = MazetoString(maze);

        return mazeOutput;

    }


    private static int[] GetDimensionFromUserInput(){
        
        int[] dimensions = new int[2];
        
        int rows = 0, columns = 0;
        boolean rowsRecieved = false, columnsRecieved = false;

        Scanner scn = new Scanner(System.in); // Create a Scanner object
        while(!rowsRecieved){
            System.out.println("Enter number of rows:");
            rows = scn.nextInt();
            if(rows > 20 || rows <= 0){
                System.out.println("Too many rows/invalid input!! Give a number between 0 and 40");
            }
            else rowsRecieved = true;
        }

        while(!columnsRecieved){
            System.out.println("Enter number of columns:");
            columns = scn.nextInt();
            if(columns > 20 || columns <= 0){
                System.out.println("Too many columns/invalid input!! Give a number between 1 and 40");
            }
            else columnsRecieved = true;
        }

        scn.close();
        dimensions[0] = rows;
        dimensions[1] = columns;
        return dimensions;

    }

    private static List<List<Cell>> BuildMaze(int rows, int columns){
        //Maze construction
        List<List<Cell>> maze = new ArrayList<>();
        int count = 0;
        for(int i=0; i<rows; i++){
            ArrayList<Cell> cells = new ArrayList<>();
            for(int j=0; j<columns; j++){
                Cell c = new Cell(count);
                cells.add(c);
                count++;
            }
            maze.add(cells);
        }

        int totalCells = rows*columns;

        DisjSets set = new DisjSets(totalCells);
        int topCorner = 0, bottomCorner = (rows*columns) -1;
        Random rand = new Random();
        int randomRow, randomColumn, neighbourRoot, currCellRoot;
        Cell randomCell;
        Boolean removeBottomWall = false;

        //Constructing path connecting start and end
        while(set.find(topCorner) != set.find(bottomCorner)){
            //Pick a random row
            randomRow  = rand.nextInt(rows);
            //Pick a random column
            randomColumn = rand.nextInt(columns);

            //No walls to remove in bottommost left cell
            if(randomRow == rows-1 && randomColumn == 0){
                continue;
            }

            //Get the cell associated with it
            randomCell = maze.get(randomRow).get(randomColumn);

            //Leftmost columns' left wall cannot be removed
            if(randomColumn == 0) {
                neighbourRoot = set.find(randomCell.value+columns);
                removeBottomWall = true;
            }

            //Bottommost rows' down wall cannot be removed
            else if(randomRow == rows-1){
                neighbourRoot = set.find(randomCell.value-1);
            }

            //All other cells
            else{
                //Pick a random neighbour
                int r = rand.nextInt(2);
                if(r==1){ // 1 can be the bottom neighbour
                    neighbourRoot = set.find(randomCell.value+columns); 
                    removeBottomWall = true;
                }
                else{
                    neighbourRoot = set.find(randomCell.value-1);
                }
            }

            currCellRoot = set.find(randomCell.value);

            if(currCellRoot != neighbourRoot){
                set.union(currCellRoot, neighbourRoot);
                if(removeBottomWall){
                    randomCell.bottomWall = false;
                }
                else{
                    randomCell.leftWall = false;
                }
            }
            removeBottomWall = false;

        }
        return maze;
    }

    private static String MazetoString(List<List<Cell>> maze ){
       
        int columns = maze.get(0).size();
        
        
        StringBuilder sb = new StringBuilder();

        sb.append(" | START HERE!!\n");
        sb.append(" V");

        for(int i=0; i<columns; i++){
            if(i==0) sb.append(" ");
            else sb.append(" __");
        }
        sb.append("\n");
        for(List<Cell> cells : maze){
            for(Cell c : cells){
                if(c.leftWall) sb.append("|");
                else sb.append(" ");
                if(c.bottomWall) sb.append("__");
                else sb.append("  ");
            }
            sb.append("|");
            sb.append("\n");
        }

        return sb.toString();
    }
}
