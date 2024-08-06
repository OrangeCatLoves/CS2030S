// author @ Low Wei Bin Lab 16D
class Bank {

  private Counter[] counter;

  private Queue queue;

  private int numOfCounter; // Remove this field since it's unecessary

  public Bank(int numOfCounter, Counter[] counter, Queue queue) {
    this.numOfCounter = numOfCounter;
    this.counter = counter;
    this.queue = queue;
  }

  public void initialiseCounter() {
       
    for (int i = 0; i < numOfCounter; i++) {
      counter[i] = new Counter(i);
    }
  }

  public Counter[] getAllCounters() {
    return this.counter;
  }

  public boolean isFull() {
    for (int i = 0; i < numOfCounter; i++) {
      if (counter[i].getAvailability()) { 
        return false;
      }
    }
    return true;
  }

  public int findAvailableCounter() {
    int index = -1;
    for (int i = 0; i < numOfCounter; i++) {
      if (counter[i].getAvailability()) {
        index = i;
        break;
      }
    }
    return index;
  }
        
}

