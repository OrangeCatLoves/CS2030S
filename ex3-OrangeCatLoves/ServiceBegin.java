// author @ Low Wei Bin Lab 16D
class ServiceBegin extends Event {
     
  private Customer customer;
  private Bank bank;
  private Counter counter;

  public ServiceBegin(double time, Customer customer, Bank bank, Counter counter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " " + this.customer.getTask() +
      " begin " + "(by " + this.counter + " $" + String.valueOf(this.counter.getAmount()) +
      " " + this.counter.getQueue() + ")";
  }

  @Override
  public Event[] simulate() {
    this.counter.setBusy();
    this.customer.getTask().performTask(this.customer, this.counter);
    double endTime = this.customer.getEndTime(this.getTime());
    return new Event[] {
      new ServiceEnd(endTime, this.customer, this.bank, this.counter)
    };
  }

}



