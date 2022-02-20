package projects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import configs.AppConfiguration;
import datastructures.QuadraticProbingHashTable;
import helpers.CommandLineInteractions;

public final class WordPuzzle {

    private static QuadraticProbingHashTable<String> _prefixTable = new QuadraticProbingHashTable<>();
    private static QuadraticProbingHashTable<String> _wordsTable = new QuadraticProbingHashTable<>();

    public static List<String> run(){
        //Get User input
        List<String> result = new ArrayList<String>();
        int[] dimensions = CommandLineInteractions.GetDimensionFromUserInput();
        char[][] puzzle = GenerateWordPuzzle(dimensions[0], dimensions[1]);
        result.add(PuzzletoString(puzzle));
        result.addAll(solvePuzzle(puzzle));
        return result;
    }

    public static List<String> run(int rows, int columns){
        List<String> result = new ArrayList<String>();
        char[][] puzzle = GenerateWordPuzzle(rows, columns);
        result.add(PuzzletoString(puzzle));
        result.addAll(solvePuzzle(puzzle));
        return result;
    }

    public static List<String> SolveWordPuzzle(char[][] puzzle){
        Set<String> wordset = solvePuzzle(puzzle);
        List<String> words = new ArrayList<>();
        for(String word : wordset){
            words.add(word);
        }
        return words;
    }

    private static char[][] GenerateWordPuzzle(int rows, int columns){
        char[][] puzzle = new char[rows][columns];
        Random r = new Random();

        //Generating puzzle
        for(int i = 0; i<rows; i++){
            for(int j =0; j<columns; j++){
                puzzle[i][j] = (char) ('a' + r.nextInt(26));
            }
        }

        return puzzle;

    }

    private static Set<String> solvePuzzle(char[][] puzzle){
        if(_prefixTable.size() == 0 || _wordsTable.size() == 0) refreshTablesFromDictionary();
        long startTime = System.currentTimeMillis();

        String currString = "";
        StringBuilder sb = new StringBuilder();
        PriorityQueue<String> foundWords = new PriorityQueue<>();
        int maxWordLength = 0;
        int rows = puzzle.length;
        int columns = puzzle[0].length;


        //Checking puzzle
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                //accross
                sb.setLength(0);
                sb.append(puzzle[i][j]);
                if(_prefixTable.contains(sb.toString())){
                    for(int k = j+1; k<columns && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[i][k]);
                        currString = sb.toString();
                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            //System.out.println("Found from: " + i + " " + j +" " + i + " "+ k);
                        }
                    }
                    
                    sb.setLength(1);

                    for(int k = j-1; k>=0 && sb.length() < maxWordLength; k--){
                        sb.append(puzzle[i][k]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            //System.out.println("Found from: " + i + " " + j +" " + i + " "+ k);
                        }
                    }

                    sb.setLength(1);

                    for(int k = i+1; k<rows && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[k][j]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            //System.out.println("Found from: " + i + " " + j +" " + i + " "+ k);
                        }
                    }

                    sb.setLength(1);

                    for(int k = i-1; k>=0 && sb.length() < maxWordLength; k--){
                        sb.append(puzzle[k][j]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            //System.out.println("Found from: " + i + " " + j +" " + i + " "+ k);
                        }
                    }

                    sb.setLength(1);

                    for(int k = 1; (i+k < rows && j+k < columns) && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[i+k][j+k]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            /*int idx1 = i+k;
                            int idx2 = j+k;
                            System.out.println("Found from: " + i + " " + j +" " + idx1 + " "+ idx2);*/
                        }
                    }

                    sb.setLength(1);

                    for(int k = 1; (i-k >= 0 && j-k >= 0) && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[i-k][j-k]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            /*int idx1 = i-k;
                            int idx2 = j-k;
                            System.out.println("Found from: " + i + " " + j +" " + idx1 + " "+ idx2);*/
                        }
                    }

                    sb.setLength(1);

                    for(int k = 1; (i+k < rows && j-k >= 0) && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[i+k][j-k]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            /*int idx1 = i+k;
                            int idx2 = j-k;
                            System.out.println("Found from: " + i + " " + j +" " + idx1 + " "+ idx2);*/
                        }
                    }

                    sb.setLength(1);

                    for(int k = 1; (i-k >= 0 && j+k < columns) && sb.length() < maxWordLength; k++){
                        sb.append(puzzle[i-k][j+k]);
                        currString = sb.toString();

                        if(!_prefixTable.contains(currString)){
                            break;
                        }
                        if(_wordsTable.contains(currString)){
                            foundWords.add(currString);
                            /*int idx1 = i-k;
                            int idx2 = j+k;
                            System.out.println("Found from: " + i + " " + j +" " + idx1 + " "+ idx2);*/
                        }
                    }
                }   
            }
        }

        //QuadraticProbingHashTable<String> foundWordsSet = new QuadraticProbingHashTable<>();
        Set<String> foundWordsSet = new HashSet<>();
        System.out.println("Found words: ");
        
        //Remove duplicates and single letter words
        while(!foundWords.isEmpty()){
            String currWord = foundWords.remove();
            if(currWord.length() > 1 && !foundWordsSet.contains(currWord)){
                foundWordsSet.add(currWord);
                //System.out.println(currWord);
            }
        }

        //end timer
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " milliseconds.");

        return foundWordsSet;

    }

    private static String PuzzletoString(char[][] puzzle){
        
        int rows = puzzle.length;
        int columns = puzzle[0].length;
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<rows; i++){
            for(int j =0; j<columns; j++){
                sb.append(puzzle[i][j] + "  ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    private static void refreshTablesFromDictionary(){
        // Project 3 code
        int maxWordLength = 0, currWordLength;
        //File location
        File file = new File(AppConfiguration.dictionary);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;
            while ((st = reader.readLine()) != null) {
                currWordLength = st.length();
                _wordsTable.insert(st);
                maxWordLength = Math.max(maxWordLength, currWordLength);
                for (int i = 0; i <= currWordLength; i++) {
                    String prefix = st.substring(0, i);
                    _prefixTable.insert(prefix);
                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}

