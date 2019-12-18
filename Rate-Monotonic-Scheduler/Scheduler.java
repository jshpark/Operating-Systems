import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.System;

public class Scheduler extends TimerTask{
  private long startUnit = 0;
  private int timeUnit = 0;
  private int period = 1;
  private int threadOneCounter = 0;
  private int threadTwoCounter = 0;
  private int threadThreeCounter = 0;
  private int threadFourCounter = 0;
  private int overrunThreadTwo = 0;
  private int overrunThreadThree = 0;
  private int overrunThreadFour = 0;
  private long totalRunTime = 0;
  private boolean stop = true;

  private Threads threadOne;
  private Threads threadTwo;
  private Threads threadThree;
  private Threads threadFour;
  private Timer timer = new Timer(true);

  public Scheduler(Threads threadOne, Threads threadTwo, Threads threadThree, Threads threadFour){
    this.threadOne = threadOne;
    this.threadTwo = threadTwo;
    this.threadThree = threadThree;
    this.threadFour = threadFour;
    this.threadOne.setPriority(10);
    this.threadTwo.setPriority(9);
    this.threadThree.setPriority(8);
    this.threadFour.setPriority(7);
    startThreadOne();
    startThreadTwo();
    startThreadThree();
    startThreadFour();
  }
  
  public void run(){
    startUnit = System.currentTimeMillis();
    if (timeUnit % 160 == 0){
      threadOne.StartWork(0);
      incrementThreadOne();
      totalRunTime += threadOne.getExecutionTime();
      if (!threadTwo.StartWork(totalRunTime)){
        overrunThreadTwo++;
      }
      else{
        incrementThreadTwo();
      }
      totalRunTime += threadTwo.getExecutionTime();
      if (!threadThree.StartWork(totalRunTime)){
        overrunThreadThree++;
      }
      else{
        incrementThreadThree();
      }
      totalRunTime += threadThree.getExecutionTime();
      if(!threadFour.StartWork(totalRunTime)){
        overrunThreadFour++;
      }
      else{
        incrementThreadFour();
      }
      totalRunTime += threadFour.getExecutionTime();
      stop = false;
    }
    if((timeUnit % 40 == 0) && (stop == true)){
      threadOne.StartWork(0);
      incrementThreadOne();
      totalRunTime += threadOne.getExecutionTime();
      if (!threadTwo.StartWork(totalRunTime)){
        overrunThreadTwo++;
      }
      else{
        incrementThreadTwo();
      }
      totalRunTime += threadTwo.getExecutionTime();
      if (!threadThree.StartWork(totalRunTime)){
        overrunThreadThree++;
      }
      else{
        incrementThreadThree();
      }
      totalRunTime += threadThree.getExecutionTime();
      stop = false;
    }
    if ((timeUnit % 20 == 0) && (stop == true)){
      threadOne.StartWork(0);
      incrementThreadOne();
      totalRunTime += threadOne.getExecutionTime();
      if (!threadTwo.StartWork(totalRunTime)){
        overrunThreadTwo++;
      }
      else{
        incrementThreadTwo();
      }
      totalRunTime += threadTwo.getExecutionTime();
      stop = false;
    }
    if ((timeUnit % 10 == 0) && (stop == true)){
      threadOne.StartWork(0);
      incrementThreadOne();
      totalRunTime += threadOne.getExecutionTime();
      stop = false;
    }
    incrementTimeUnit(1);
    totalRunTime = 0;
    stop = true;
    if (timeUnit >= 1600){
      System.out.println("Thread one ran " + threadOneCounter + " times");
      System.out.println("Thread two ran " + threadTwoCounter + " times");
      System.out.println("Thread three ran " + threadThreeCounter + " times");
      System.out.println("Thread four ran " + threadFourCounter + " times");
      System.out.println("There were " + overrunThreadTwo + " overruns for Thread two.");
      System.out.println("There were " + overrunThreadThree + " overruns for Thread three.");
      System.out.println("There were " + overrunThreadFour + " overruns for Thread four.");
      cancel();
    }
  }


//get current execution time
  private long currentExecutionTime(){
    return (System.currentTimeMillis() - startUnit);
  }

//functions to start threads
  private void startThreadOne(){
    threadOne.start();
  }

  private void startThreadTwo(){
    threadTwo.start();
  }

  private void startThreadThree(){
    threadThree.start();
  }

  private void startThreadFour(){
    threadFour.start();
  }

//functions to increment thread counters
  private void incrementThreadOne(){
    threadOneCounter++;
  }

  private void incrementThreadTwo(){
    threadTwoCounter++;
  }

  private void incrementThreadThree(){
    threadThreeCounter++;
  }

  private void incrementThreadFour(){
    threadFourCounter++;
  }

//function to increment time unit
  private void incrementTimeUnit(int period){
    timeUnit += (10 * period);
  }
}
