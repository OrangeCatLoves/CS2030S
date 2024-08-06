  // author @ Low Wei Bin Lab 16D
  //
   class Arrival extends Event{

     private static final int tag = 0;
     private Customers customer;
     private Bank bank;

     public int getTag() {
       return tag;
     }

     public Arrival(double time, Customers customer, Bank bank) {
       super(time);
       this.customer = customer;
       this.bank = bank;
     }

     @Override
     public String toString() {
       return super.toString() + String.format(": Customer %d arrives", this.customer.getCustomerID());
     }

     @Override
     public Event[] simulate() {
     
     if (bank.isFull()) {
       return new Event[] {
         new Departure(this.getTime(), this.customer, this.bank)
       };
     } else {
       int counterID = bank.findAvailableCounter();
       return new Event[] { new ServiceBegin(this.getTime(), this.customer, this.bank, counterID)
           };

        }
     }

  }   
