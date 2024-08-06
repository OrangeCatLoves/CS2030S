// author @ Low Wei Bin Lab 16D
  
class Arrival extends Event {

  private Customer customer;
  private Bank bank;

  public Arrival(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " arrived " + this.bank.getEntranceQueue();
  }

  @Override
  public Event[] simulate() {

    Counter counter = this.bank.findAvailableCounter();
    if (counter != null) {
      if (counter.getAvailability() == true) {
        return new Event[] { new ServiceBegin(this.getTime(),
          this.customer, this.bank, counter)
        };
      } else {
        return new Event[] {
            new JoiningCounterQueue(this.getTime(), this.customer, this.bank, counter)
        };
      }
    } else {
      if (bank.getEntranceQueue().isFull()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer, this.bank)
        };
      } else {
        return new Event[] {
            new JoiningQueue(this.getTime(), this.customer, this.bank) 
        };
      }
    }
  }

}   
