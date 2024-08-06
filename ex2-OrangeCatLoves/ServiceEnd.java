// author @ Low Wei Bin Lab 16D
class ServiceEnd extends Event {

  private Customer customer;
  private Bank bank;
  private Queue queue;
  private int counterID;

  public ServiceEnd(double time, Customer customer, Bank bank, Queue queue, int counterID) {
    super(time);

    this.customer = customer;
    this.bank = bank;
    this.queue = queue;
    this.counterID = counterID;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " " + this.customer.getTask() + 
      " done " + this.bank.getAllCounters()[this.counterID]; 
  }


  public Event[] simulate() {
    bank.getAllCounters()[this.counterID].setAvailable();
    if (!this.queue.isEmpty()) {
      Customer customers = (Customer) queue.deq();
      return new Event[] { new Departure(this.getTime(), this.customer, this.bank), 
        new ServiceBegin(this.getTime(), customers, this.bank, this.queue, this.counterID) };
    } else {
      return new Event[] { new Departure(this.getTime(), this.customer, this.bank),
      };
    }

  }

}
