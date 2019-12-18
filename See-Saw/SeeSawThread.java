import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class SeeSawThread extends Thread{
  private Semaphore upSem;
  private Semaphore downSem;
  private Person personOne;
  private Person personTwo;
  private int cycleCount;
  private boolean cycleBool;

  public SeeSawThread(Person personOne, Person personTwo, Semaphore upSem, Semaphore downSem){
    this.personOne = personOne;
    this.personTwo = personTwo;
    this.upSem = upSem;
    this.downSem = downSem;
    cycleCount = 1;
    cycleBool = false;
  }


  public void run(){
    while(cycleCount != 11){
      try{
        Thread.sleep(1000);
      }
      catch(Exception e){}
      if (personOne instanceof Fred){ //if the person is fred
        try{
          if (personOne.getUp()){ //if fred is going up
            upSem.acquire(); //Fred goes up
            //acquire the upSem ... now implement it going up
            personOne.setHeight(personOne.getVelocity(), "up");
            upSem.release();
            if (personOne.getHeight() == 7){ //if fred is at the highest
              personOne.setUp(false);  //then fred should be going down
            }
          }
          else{ //if fred is at the highest point
            downSem.acquire(); //fred goes down
            //acquire the downSem ... now implement it going down
            personOne.setHeight(personTwo.getVelocity(), "down");
            downSem.release();
            if (personOne.getHeight() == 1){ //if fred is at the lowest
              cycleBool = true;
              personOne.setUp(true); //then fred should be going up
            }
          }
        }
        catch(Exception e){}
        System.out.println("Fred's height is " + personOne.getHeight());
      }
      else{ //if the person is wilma
        try{
          if (personOne.getUp()){ //if wilma is going up
            upSem.acquire(); //wilma goes up
            //acquire the upSem ... now implement it going up
            personOne.setHeight(personOne.getVelocity(), "up");
            upSem.release();
            if (personOne.getHeight() == 7){ //if wilma is at the highest
              cycleCount++;
              personOne.setUp(false);  //then wilma should be going down
            }
          }
          else{ //if wilma is at the highest point
            downSem.acquire(); //wilma goes down
            //acquire the downSem ... now implement it going down
            personOne.setHeight(personTwo.getVelocity(), "down");
            downSem.release();
            if (personOne.getHeight() == 1){ //if wilma is at the lowest
              personOne.setUp(true); //then wilma should be going up
            }
          }
        }
        catch(Exception e){}
        System.out.println("Wilma's height is " + personOne.getHeight());
      }
      if (cycleBool){
        System.out.println("Cycle " + cycleCount + " finished");
        cycleCount++;
        cycleBool = false;
      }
    }
  }
}
