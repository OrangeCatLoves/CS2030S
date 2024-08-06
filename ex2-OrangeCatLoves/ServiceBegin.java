// author @ Low Wei Bin Lab 16D
class ServiceBegin extends Event {
     
  private Customer customer;
  private Bank bank;
  private Queue queue;
  private int counterID;

  public ServiceBegin(double time, Customer customer, Bank bank, Queue queue, int counterID) {
    super(time);
    this.customer = customer;
    this.queue = queue;
    this.bank = bank;
    this.counterID = counterID;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " " + this.customer.getTask() +
      " begin " + this.bank.getAllCounters()[this.counterID];
  }

  @Override
  public Event[] simulate() {
    this.bank.getAllCounters()[this.counterID].setBusy(); 
    double endTime = this.customer.getEndTime(this.getTime());
    return new Event[] {
      new ServiceEnd(endTime, this.customer, this.bank, this.queue, this.counterID)
    };
  }

}



