import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.lang.System;

public class Threads extends Thread{
  private double[][] workArray;
  private int threadNumber;
  private Semaphore sem;
  private long startUnit = 0;
  private int threadTwoI = 0;
  private int threadThreeI = 0;
  private int threadFourI = 0;
  private long runTime = 0;

  public Threads(int threadNumber, Semaphore sem){
    workArray = fillWorkArray();
    this.threadNumber = threadNumber;
    this.sem = sem;
  }

  public void run(){
  }

  public boolean StartWork(long currentTime){
    //switch statement to see which thread is running
    try{
      switch(threadNumber){
        case 1:
        sem.acquire();
        startUnit = System.currentTimeMillis();
        for (int i = 1; i <= 100; ++i){ //thread one runs doWork 100 times
          doWork();
        }
        runTime = currentExecutionTime();
        sem.release();
        return true;
        case 2:
        sem.acquire();
        startUnit = System.currentTimeMillis();
        if (threadTwoI > 0){
          for (int i = 1; i <= threadTwoI; ++i){ //finish the remaining work
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){ //
              threadTwoI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadTwoI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
        else{
          for (int i = 1; i <= 200; ++i){ //thread two runs doWork 200 times
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){
              threadTwoI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadTwoI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
        case 3:
        sem.acquire();
        startUnit = System.currentTimeMillis();
        if (threadThreeI > 0){
          for (int i = 1; i  <= threadThreeI; ++i){
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){
              threadThreeI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadThreeI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
        else{
          for (int i = 1; i <= 400; ++i){
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){
              threadThreeI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadThreeI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
        case 4:
        sem.acquire();
        startUnit = System.currentTimeMillis();
        if (threadFourI > 0){
          for (int i = 1; i <= threadFourI; ++i){
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){
              threadFourI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadFourI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
        else{
          for (int i = 1; i <= 1600; ++i){
            doWork();
            if ((currentExecutionTime() + currentTime) > 10){
              threadFourI = i;
              runTime = currentExecutionTime();
              sem.release();
              return false;
            }
          }
          threadFourI = 0;
          runTime = currentExecutionTime();
          sem.release();
          return true;
        }
      }
    }
    catch(Exception e){}
      return true;
  }

  public void doWork(){
    //pattern +5 , -4
    boolean trigger = true;  //boolean to see if we +5 or -4
    int i = 0;
    double product = 1.0;
    while(i <= 9){
      for (int j = 0; j < 10; ++j){ //iterate down the column
        product *= workArray[i][j]; //computation
      }
      if (i == 9){ //if column is 9, then we are done .. break
        break;
      }
      if (trigger){ //adding 5 turn
        i += 5;
        trigger = false;
      }
      else{ //subtracting 4 turn
        i -= 4;
        trigger = true;
      }
    }
  }

  //get current execution time
    private long currentExecutionTime(){
      return (System.currentTimeMillis() - startUnit);
    }

  public long getExecutionTime(){
    return runTime;
  }

  public double[][] fillWorkArray(){ //just filling column with double values of 1
    double[][] array = new double[10][10];
    for (int i = 0; i < 10; ++i){
      for (int j = 0; j < 10; ++j){
        array[i][j] = 1.0;
      }
    }
    return array;
  }
}
