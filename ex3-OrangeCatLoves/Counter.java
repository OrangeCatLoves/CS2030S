// author @ Low Wei Bin Lab 16D
class Counter implements Comparable<Counter> {

  private boolean availability;
  private int counterID;
  private int amount;
  private Queue<Customer> queue;

  public Counter(int counterID, Queue<Customer> queue) {
    this.availability = true;
    this.counterID = counterID;
    this.amount = 100;
    this.queue = queue;
  }

  public String toString() {
    return "S" + Integer.toString(this.counterID);
  }

  @Override
  public int compareTo(Counter counter) { 
    int currQsize = this.getQueue().length();
    int nextQsize = counter.getQueue().length();
    int currCounterID = this.getCounterID();
    int nextCounterID = counter.getCounterID();
    if (this.getAvailability() == true && counter.getAvailability() == true) {
      return -1;
    } else  if (this.getAvailability() == true && counter.getAvailability() == false) {
      return -1;
    } else if (this.getAvailability() == false && counter.getAvailability() == true) {
      return 1;
    } else if (this.getAvailability() == false && counter.getAvailability() == false) {
      if (currQsize > nextQsize) {
        return 1;
      } else if (currQsize == nextQsize) {
        if (currCounterID < nextCounterID) {
          return -1;
        } else {
          return 1;
        }
      } else {
        return -1;
      }
    } else {
      return 1; // Should not reach here at all
    }
  }

  public void setNewAmount(int newAmount) {
    this.amount = newAmount;
  }

  public int getAmount() {
    return this.amount;
  }

  public Queue<Customer> getQueue() {
    return this.queue;
  }
  
  public boolean getAvailability() {
    return this.availability;
  }

  public int getCounterID() {
    return this.counterID;
  }

  public boolean isAvailable() {

    if (this.availability) {
      return true;
    } else {
      return false;
    }
  }

  public void setBusy() {
    this.availability = false;
  }

  public void setAvailable() {
    this.availability = true;
  }

  public boolean joinQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    Customer customer = (Customer) this.queue.deq();
    return customer;
  }


}

