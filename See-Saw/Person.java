public class Person{
  private double velocity;
  private double height;
  private boolean up;

  public Person(double velocity, double height, boolean up){
    this.velocity = velocity;
    this.height = height;
    this.up = up;
  }

  public double getVelocity(){
    return velocity;
  }

  public double getHeight(){
    return height;
  }

  public boolean getUp(){
    return up;
  }

  public void setHeight(double height, String direction){
    if (direction.equals("up")){
      this.height += height;
    }
    else{
      this.height -= height;
    }
  }

  public void setUp(boolean up){
    this.up = up;
  }
}
