// author @ Low Wei Bin Lab 16D
class Bank {

  private Seq<Counter> counters; // Array of BankCounters

  private Queue<Customer> queue; // Entrance queue

  private int numOfCounter; 

  public Bank(int numOfCounter, Seq<Counter> seq, Queue<Customer> queue) {
    this.numOfCounter = numOfCounter;
    this.counters = seq;
    this.queue = queue;
  }

  public void initialiseCounter(int maxCounterQueueSize) {

    for (int i = 0; i < numOfCounter; i++) {
      Queue<Customer> queue = new Queue<Customer>(maxCounterQueueSize);
      Counter counter = new Counter(i, queue);
      this.counters.set(i, counter);
    }
  }

  public boolean isAllCounterQueuesFull() {

    for (int i = 0; i < numOfCounter; i++) {
      if (!counters.get(i).getQueue().isFull()) {
        return false;
      }
    }
    return true;
  }

  public int entranceQueueLen() {
    return this.queue.length();
  }

  public boolean isFull() {
    for (int i = 0; i < numOfCounter; i++) {
      if (counters.get(i).getAvailability()) {
        return false;
      }
    }
    return true;
  }

  public Counter findAvailableCounter() {
    Counter counter = counters.min();
    if (counter.getAvailability()) {
      return counter;
    } else if (!counter.getQueue().isFull()) {
      return counter;
    } else {
      return null;
    } 
  }

  public Queue<Customer> getEntranceQueue() {
    return this.queue;
  }

  public boolean joinQueue(Customer customer) { // Only for Entrance queue
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() { // Only for Entrance queue
    Customer customer = (Customer) this.queue.deq();
    return customer;
  }

}

