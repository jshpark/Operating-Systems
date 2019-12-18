import java.lang.Thread;
import java.lang.System;
import java.util.concurrent.Semaphore;
import java.util.TimerTask;
import java.util.Timer;

public class main{
  public static void main(String[] args) {
    //create a scheduler object
    //create multiple thread objects w paramters of 1, 2, 3, 4
    //pass threads into scheduler object
    Semaphore semOne = new Semaphore(1); //creating semaphore for synchronization
    Semaphore semTwo = new Semaphore(1);
    Semaphore semThree = new Semaphore(1);
    Semaphore semFour = new Semaphore(1);

    //creating 4 threads
    Threads threadOne = new Threads(1, semOne);
    Threads threadTwo = new Threads(2, semTwo);
    Threads threadThree = new Threads(3, semThree);
    Threads threadFour = new Threads(4, semFour);

    //creating scheduler
    TimerTask scheduler = new Scheduler(threadOne, threadTwo, threadThree, threadFour);
    Timer timer = new Timer("MyTimer");
    timer.scheduleAtFixedRate(scheduler, 0, 10);
    try{
      Thread.sleep(5000);
    }
    catch(Exception e){}
    System.exit(0);
  }
}
