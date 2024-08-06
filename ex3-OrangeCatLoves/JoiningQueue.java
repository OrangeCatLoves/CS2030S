// author @ Low Wei Bin Lab 16D
class JoiningQueue extends Event {

  private Customer customer;
  private Bank bank;

  public JoiningQueue(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " joined bank queue " +
      this.bank.getEntranceQueue();
  }

  @Override
  public Event[] simulate() {
    boolean b = bank.joinQueue(this.customer);
    return new Event[] {};
  }

     
}
