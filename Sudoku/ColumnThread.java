import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class ColumnThread extends Thread{
  private Semaphore sem;
  private int[][] myArray;
  private int correctNumber;
  private String threadName;
  private ArrayList<Integer> listCheck;

  //constructor to create the default arraylist with correct numbers
  public ColumnThread(int[][] myArray, Semaphore sem, String threadName){
    this.myArray = myArray;
    this.sem = sem;
    this.threadName = threadName;
    listCheck = new ArrayList<Integer>(9);
    listCheck.add(1);
    listCheck.add(2);
    listCheck.add(3);
    listCheck.add(4);
    listCheck.add(5);
    listCheck.add(6);
    listCheck.add(7);
    listCheck.add(8);
    listCheck.add(9);
    Collections.sort(listCheck);
  }

  public void run(){
    if (threadName.equals("check")){ //check column thread
      try{
        sem.acquire();
        ArrayList<Integer> myList = new ArrayList<Integer>(9);
        for (int i = 0; i < 9; ++i){
          for (int j = 0; j < 9; ++j){
            if (!myList.contains(myArray[j][i])){ //put column numbers into arraylist if not already in it
              myList.add(myArray[j][i]);
            }
            else{ //if already in it, that means theres an error at the column number
              ColumnNumber.columnNumber = i;
            }
          }
          myList.clear();
        }
      }
      catch(Exception e){
      }
      sem.release();
    }
    else{ //fix column thread
      try{
        sem.acquire();
        ArrayList<Integer> myList = new ArrayList<Integer>(9);
        correctNumber = -1; //if correct number stays -1, then there is no error!
        for (int i = 0; i < 9; ++i){
          myList.add(myArray[i][ColumnNumber.columnNumber]); //add numbers in column to list
        }
        Collections.sort(myList); //sort list to compare to default array list
        for (int i = 0; i < 9; ++i){
          if (myList.contains(1)){ //if the list contains 1, that means we can proceed to check for the correct solution
            if (myList.get(i) != listCheck.get(i)){
              correctNumber = listCheck.get(i);
            }
          }
          else{ //if the list does not contain 1, the correct solution is 1
            correctNumber = 1;
          }
        }
      }
      catch(Exception e){
      }
      sem.release();
    }
  }

  public int getCorrectNumber(){
    return correctNumber;
  }

  public int getColumnNumber(){
    return ColumnNumber.columnNumber;
  }
}
