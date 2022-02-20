package helpers;

import java.util.Scanner;

public class CommandLineInteractions {
    public static int[] GetDimensionFromUserInput(){
        
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
    
}
