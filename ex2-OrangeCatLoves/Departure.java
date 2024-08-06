// author @ Low Wei Bin Lab 16D
class Departure extends Event {

  private Customer customer;
  private Bank bank;

  public Departure(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " departed";
  }


  public Event[] simulate() {
    return new Event[] {};
  }

}
