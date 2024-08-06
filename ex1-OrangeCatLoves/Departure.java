// author @ Low Wei Bin Lab 16D
   class Departure extends Event{

     private static final int tag = 0;
     private Customers customer;
     private Bank bank;

     public int getTag() {
       return tag;
     }

     public Departure(double time, Customers customer, Bank bank) {
       super(time);
       this.customer = customer;
       this.bank = bank;

     }

     @Override
     public String toString() {
       return super.toString() + String.format(": Customer %d departed", this.customer.getCustomerID());
     }

     public Event[] simulate() {
       // do nothing
       return new Event[] {};
     }

   }
