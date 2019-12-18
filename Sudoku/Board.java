import java.util.ArrayList;
import java.util.Collections;

public class Board{

  //fix the error in the array
  public static void fixArray(int[][] myArray, int row, int column, int number){
    myArray[row][column] = number;
  }

  //print array for testing
  public void printArray(int[][] array){
    for (int i = 0; i < 9; ++i){
      for (int j = 0; j < 9; ++j){
        System.out.print(array[i][j]);
      }
      System.out.println();
    }
  }

  //printing solution to console
  public static void printSolution(RowThread checkRowThread, ColumnThread checkColumnThread, RowThread fixRowThread, ColumnThread fixColumnThread){
    System.out.println("(" + (checkRowThread.getRowNumber() + 1) + ", " + (checkColumnThread.getColumnNumber() + 1) + ")");
    if (fixRowThread.getCorrectNumber() == -1 && fixColumnThread.getCorrectNumber() == -1){
      System.out.println("The Sudoku is now fixed.");
    }
    else{
      if (fixRowThread.getCorrectNumber() != -1){
        System.out.println("The correct number should be: " + fixRowThread.getCorrectNumber() + ".");
      }
      else{
        System.out.println("The correct number should be: " + fixColumnThread.getCorrectNumber() + ".");
      }
    }
  }
}
