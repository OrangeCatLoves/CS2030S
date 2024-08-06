// author @ Low Wei Bin Lab 16D

public class Customer {

  private int customerID;
  private double serviceTime;
  private Task task;

  public Customer(int customerID, int whichTask, double serviceTime) {
    this.customerID = customerID;
    this.serviceTime = serviceTime;
    initialiseTask(whichTask);
  }

  private void initialiseTask(int whichTask) {
    if (whichTask == 0) {
      this.task = new Deposit(this);
    } else if (whichTask == 1) {
      this.task = new Withdraw(this);
    }
  }

  public double getEndTime(double startTime) {
    double endTime = startTime + this.serviceTime;
    return endTime;
  }

  public Task getTask() {
    return this.task;
  }

  public int getCustomerID() {
    return this.customerID;
  }

  @Override
  public String toString() {
    return "C" + Integer.toString(this.customerID);
  }

  public void leaveQueue() {
    return;
  }

  public void joinQueue() {
    return;
  }

}

