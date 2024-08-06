// author @ Low Wei Bin Lab 16D
class JoiningQueue extends Event {

  private Customer customer;
  private Bank bank;
  private Queue queue; 

  public JoiningQueue(double time, Customer customer, Bank bank, Queue queue) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " joined queue " + this.queue;
  }

  @Override
  public Event[] simulate() {
    boolean b = queue.enq(this.customer);
    return new Event[] {};
  }

     
}
