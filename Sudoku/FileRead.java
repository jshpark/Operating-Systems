import java.io.*;
import java.util.Scanner;

public class FileRead{
  //instance variables
  private String file;

  //constructor
  public FileRead(String fileName)
  {
    file = fileName;
  }

  //copy contents of text file into 2d arrays
  public int[][] textToArray()
  {
    int[][] myArray = new int[9][9];
    //read from file
    try{
      File txtFile = new File(file);
      BufferedReader reader = new BufferedReader(new FileReader(txtFile));
      String line;
      int row = 0;
      int column = 0;
      while ((line = reader.readLine()) != null){ //read each row
        for (int i = 0; i < line.length(); ++i){ //read each column
          if (line.charAt(i) != ','){ //if character is a number, put in array
            myArray[row][column++] = Integer.parseInt(String.valueOf(line.charAt(i)));
          }
        }
        row++;
        column = 0;
      }
    }
    catch(Exception e){
    }
    return myArray;
  }
}
