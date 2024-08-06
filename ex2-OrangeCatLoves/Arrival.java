// author @ Low Wei Bin Lab 16D
  
class Arrival extends Event {

  private Customer customer;
  private Bank bank;
  private Queue queue;

  public Arrival(double time, Customer customer, Bank bank, Queue queue) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " arrived " + this.queue;
  }

  @Override
  public Event[] simulate() {
     
    if (bank.isFull()) {

      if (queue.isFull()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer, this.bank)
        }; 
      } else {
        return new Event[] {
          new JoiningQueue(this.getTime(), this.customer, this.bank, this.queue)
        };
      }
    } else {
      int counterID = bank.findAvailableCounter();
      return new Event[] { new ServiceBegin(this.getTime(), 
          this.customer, this.bank, this.queue, counterID)
      };

    }
  }

}   
