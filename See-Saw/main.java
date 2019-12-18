import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class main{
  public static void main(String[] args) {
    Fred fred = new Fred(1, 1, true); //velocity of 1 height of 1, and fred is going up
    Wilma wilma = new Wilma(1.5, 7, false); //velocity of 1.5, height of 7, and wilma is going down



    Semaphore up = new Semaphore(1);
    Semaphore down = new Semaphore(1);
    SeeSawThread fredThread = new SeeSawThread(fred, wilma, up, down);
    SeeSawThread wilmaThread = new SeeSawThread(wilma, fred, up, down);
    fredThread.start();
    wilmaThread.start();
  }
}
