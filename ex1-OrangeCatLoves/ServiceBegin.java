// author @ Low Wei Bin Lab 16D
   class ServiceBegin extends Event{
     
     private static final int tag = 1;
     private Customers customer;
     private Bank bank;
     private int counterID;

     public int getTag() {
       return tag;
     }

     public ServiceBegin(double time, Customers customer, Bank bank, int counterID) {
       super(time);
       this.customer = customer;
       this.bank = bank;
       this.counterID = counterID;
     }

     @Override
     public String toString() {
       return super.toString() + String.format(": Customer %d service begin (by Counter %d)",
          this.customer.getCustomerID(), this.counterID);
     }

     public Event[] simulate() {
       bank.getAllCounters()[this.counterID].setBusy();
       double endTime = this.customer.getEndTime(this.getTime());
       // double endTime = this.getTime() + this.serviceTime;
       return new Event[] {
         new ServiceEnd(endTime, this.customer, this.bank, this.counterID)
       };
     }

   }



