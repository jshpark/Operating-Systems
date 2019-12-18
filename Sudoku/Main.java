import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class Main{
  public static void main(String[] args) {
    String fileName = args[0];
    FileRead file = new FileRead(fileName);

    int[][] myArray = file.textToArray();

    //creation of semaphores
    Semaphore semColumn1 = new Semaphore(1);
    Semaphore semRow1 = new Semaphore(1);
    Semaphore semColumn2 = new Semaphore(1);
    Semaphore semRow2 = new Semaphore(1);

    //creation of threads
    ColumnThread checkColumnThread1 = new ColumnThread(myArray, semColumn1, "check");
    RowThread checkRowThread1 = new RowThread(myArray, semRow1, "check");
    ColumnThread fixColumnThread1 = new ColumnThread(myArray, semColumn1, "fix");
    RowThread fixRowThread1 = new RowThread(myArray, semRow1, "fix");
    ColumnThread checkColumnThread2 = new ColumnThread(myArray, semColumn2, "check");
    RowThread checkRowThread2 = new RowThread(myArray, semRow2, "check");
    ColumnThread fixColumnThread2 = new ColumnThread(myArray, semColumn2, "fix");
    RowThread fixRowThread2 = new RowThread(myArray, semRow2, "fix");
    checkRowThread1.start();
    checkColumnThread1.start();
    fixRowThread1.start();
    fixColumnThread1.start();
    //need to take semaphores away from second set of threads b/c these threads depend on the first thread to finish first
    try{
      semRow2.acquire();
      semColumn2.acquire();
    }
    catch(Exception e){
    }
    checkRowThread2.start();
    checkColumnThread2.start();
    fixRowThread2.start();
    fixColumnThread2.start();

    try{
      checkRowThread1.join();
      checkColumnThread1.join();
      fixRowThread1.join();
      fixColumnThread1.join();
      //fix the array for the second set of threads
      Board.fixArray(myArray, checkRowThread1.getRowNumber(), checkColumnThread1.getColumnNumber(), fixRowThread1.getCorrectNumber());
      Board.printSolution(checkRowThread1, checkColumnThread1, fixRowThread1, fixColumnThread1);
      //release the semaphore so the second set of threads can do their jobs
      semColumn2.release();
      semRow2.release();
      //second set of threads finished
      checkRowThread2.join();
      checkColumnThread2.join();
      fixRowThread2.join();
      fixColumnThread2.join();
      Board.printSolution(checkRowThread2, checkColumnThread2, fixRowThread2, fixColumnThread2);
    }
    catch(Exception e){
    }
  }
}
