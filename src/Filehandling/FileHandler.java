/**
 * * @author Christoffer
 */

package Filehandling;

import Entities.WordPair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler {
    
    public static LinkedList<WordPair> loadFile(String filename)
    {
        Scanner file_scanner = null;
        LinkedList<WordPair> list = new LinkedList<WordPair>();

        try {
            file_scanner = new Scanner(new File(filename));  //Connection to the file using the Scanner object
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find the file to load from! Returning null.");
            ex.printStackTrace();
            return null;  //If something goes wrong the method returns null
        }
        
        while (file_scanner.hasNextLine()) {  //File found. Reading one line. 
            String linje = file_scanner.nextLine();
            Scanner sc = new Scanner(linje).useDelimiter(",");
            String question = sc.next().toLowerCase();
            String answer = sc.next().toLowerCase();
            double priority = 0.5;
            double normalized = 0.0;
//-------------------------------------------------------------
//Use these for testing!
//          double priority = Double.parseDouble(sc.next());
//          double normalized = Double.parseDouble(sc.next());
//-------------------------------------------------------------
            
            WordPair c = new WordPair(question, answer, priority, normalized);
            list.add(c);  //Reading in a single line and saving in the ArrayList
        }

        file_scanner.close();  //Closing the file
        return list;    //returning the arraylist
    }

    /**
     * This method saves an ArrayList of strings to disk.
     * Each string object in this array will be one line in the text file. 
     * The text file is overwritten, what ever was in there is lost. 
     *
     * @param stringArray ArrayList<String> Each String object in this array will be 
     * saved as one line in the text file. 
     * @params filename String the name of the file (that is located in the project folder).
     * @return true if everything went well. False if an exception was raised.
     */
       
    public static boolean saveFile(LinkedList<WordPair> wordsArray, String filename)
    {
        if( wordsArray == null ) { 
            return false;
        }  //Checking parameter for null.
        FileWriter output;  //Creating reference for filewriter.
        
        try {
                output = new FileWriter(new File(filename));  //Opening connection to file.
                for (WordPair combination : wordsArray) {   //running through the ArrayList.                    
                    output.write(combination.getWordpair() + "\n");  //Each String object is written as a line in file.
//------------------------------------------------------------------------------------------------ 
//Used for testing!
//output.write(combination.toString() + "\n");  //Each String object is written as a line in file.
            }

            output.close();  //Closing the file
        } catch (IOException ex) {  //If something goes wrong everything is send to system out.
            System.out.println("Could not save to file!");
            System.out.println(ex.toString());
            ex.printStackTrace();
            return false;  //If something goes wrong false is returned!
        }
        return true;
    }    
    
    public static boolean saveFileWithWeights(LinkedList<WordPair> wordsArray, String filename)
    {
        if( wordsArray == null ) { 
            return false;
        }  //Checking parameter for null.
        FileWriter output;  //Creating reference for filewriter.
        
        try {
                output = new FileWriter(new File(filename));  //Opening connection to file.
                for (WordPair combination : wordsArray) {   //running through the ArrayList.                    
                    output.write(combination.toString() + "\n");  //Each String object is written as a line in file.
//------------------------------------------------------------------------------------------------ 
//Used for testing!
//output.write(combination.toString() + "\n");  //Each String object is written as a line in file.
            }

            output.close();  //Closing the file
        } catch (IOException ex) {  //If something goes wrong everything is send to system out.
            System.out.println("Could not save to file!");
            System.out.println(ex.toString());
            ex.printStackTrace();
            return false;  //If something goes wrong false is returned!
        }
        return true;
    }    
}
