  // author @ Low Wei Bin Lab 16D
   class ServiceEnd extends Event{

     private static final int tag = 0;
     private Customers customer;
     private Bank bank;
     private int counterID;

     public int getTag() {
       return tag;
     }

     public ServiceEnd(double time, Customers customer, Bank bank, int counterID) {
       super(time);

       this.customer = customer;
       this.bank = bank;
       this.counterID = counterID;
     }

     @Override
     public String toString() {
       return super.toString() + String.format(": Customer %d service done (by Counter %d)",
          this.customer.getCustomerID(), this.counterID);
     }

     public Event[] simulate() {
       bank.getAllCounters()[this.counterID].setAvailable();
         return new Event[] { new Departure(this.getTime(), this.customer, this.bank),
       };

     }

   }
