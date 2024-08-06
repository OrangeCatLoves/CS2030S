// author @ Low Wei Bin Lab 16D
   class Bank {

     private Counters[] counter;

     private int numOfCounters;

     public Bank(int numOfCounters, Counters[] counter) {
       this.numOfCounters = numOfCounters;
       this.counter = counter;
     }

     public void initialiseCounters() {
       
       for (int i = 0; i < numOfCounters; i++) {
         counter[i] = new Counters(i);
       }
     }

     public int getNumOfCounters() {
       return this.numOfCounters;
     }

     public Counters[] getAllCounters() {
       return this.counter;
     }

     public boolean isFull() {
       for (int i = 0; i < numOfCounters; i++) {
         if (counter[i].getAvailability()) {
           return false;
         }
       }
       return true;
     }

     public int findAvailableCounter() {
       int index = -1;
       for (int i = 0; i < numOfCounters; i++) {
         if (counter[i].getAvailability()) {
           index = i;
           break;
         }
       }
       return index;
     }
        
   }

