// author @ Low Wei Bin Lab 16D
class ServiceEnd extends Event {

  private Customer customer;
  private Bank bank;
  private Counter counter;

  public ServiceEnd(double time, Customer customer, Bank bank, Counter counter) {
    super(time);

    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer + " " + this.customer.getTask() + 
      " done " + "(by " + this.counter + " $" + String.valueOf(this.counter.getAmount()) + " " + 
      this.counter.getQueue() + ") " + this.customer.getTask().statusTransaction(); 
  }


  public Event[] simulate() {

    this.counter.setAvailable();
    Customer customerfromCounterQ = (Customer) counter.leaveQueue();
    Customer customerfromEntranceQ = (Customer) bank.leaveQueue();
    double transitionTime = this.customer.getTransitionTime(this.getTime());

    if (customerfromCounterQ != null) {
      if (customerfromEntranceQ != null) {
        return new Event[] { new Departure(this.getTime(), this.customer, this.bank),
          new ServiceBegin(this.getTime(), customerfromCounterQ, this.bank, this.counter),
          new JoiningCounterQueue(transitionTime, customerfromEntranceQ, this.bank, this.counter)
        };
      } else {
        return new Event[] { new Departure(this.getTime(), this.customer, this.bank),
          new ServiceBegin(this.getTime(), customerfromCounterQ, this.bank, this.counter)
        };
      }
    } else {
      if (customerfromEntranceQ != null) {
        return new Event[] { new Departure(this.getTime(), this.customer, this.bank),
          new ServiceBegin(this.getTime(), customerfromEntranceQ, this.bank, this.counter)
        };
      } else {
        return new Event[] { new Departure(this.getTime(), this.customer, this.bank)
        };
      }
    }
  }

}
