// author @ Low Wei Bin Lab 16D

public class Customer {

  private int customerID;
  private double serviceTime;
  private Task task;
  private int amount;

  public Customer(int customerID, int whichTask, double serviceTime, int amount) {
    this.customerID = customerID;
    this.serviceTime = serviceTime;
    this.amount = amount;
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

  public double getTransitionTime(double startTime) {
    double endTime = startTime + 0.01;
    return endTime;
  }

  public Task getTask() {
    return this.task;
  }

  public int getAmount() {
    return this.amount;
  }

  public int getCustomerID() {
    return this.customerID;
  }

  @Override
  public String toString() {
    return "C" + Integer.toString(this.customerID);
  }



}

