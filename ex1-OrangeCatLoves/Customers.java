// author @ Low Wei Bin Lab 16D

public class Customers {

  private int customerID;

  private double serviceTime;

  public Customers(int customerID, double serviceTime) {
    this.customerID = customerID;
    this.serviceTime = serviceTime;
  }

  public double getEndTime(double startTime) {
     double endTime = startTime + this.serviceTime;
     return endTime;
  }

  public int getCustomerID() {
    return this.customerID;
  }


}

