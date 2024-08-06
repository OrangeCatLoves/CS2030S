//author @Low Wei Bin 16D
class JoiningCounterQueue extends Event {
  private Customer customer;
  private Bank bank;
  private Counter counter;


  public JoiningCounterQueue(double time, Customer customer, Bank bank, Counter counter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " joined counter queue " + "(at " + 
      this.counter + " $" + String.valueOf(this.counter.getAmount()) + 
      " " + this.counter.getQueue() + ")";
  }

  @Override
  public Event[] simulate() {
    boolean b = this.counter.joinQueue(this.customer);
    return new Event[] {};
  }
}
