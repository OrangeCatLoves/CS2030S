// author @ Low Wei Bin 16D
class Withdraw extends Task {

  private int status;

  public Withdraw(Customer customer) {
    super(customer);
    this.status = 0;
  }

  @Override
  public void performTask(Customer customer, Counter counter) {
    int amountLeft = counter.getAmount();
    int amountToWithdraw = customer.getAmount();
    int newCalculatedAmount = amountLeft - amountToWithdraw;
    if (newCalculatedAmount < 0) {
      this.status = 0;
      return;
    } else {
      this.status = 1;
      counter.setNewAmount(newCalculatedAmount);
    }
  }

  @Override
  public String toString() {
    return "withdrawal $" + String.valueOf(this.getCustomer().getAmount());
  }

  @Override
  public String statusTransaction() {
    if (this.status == 1) {
      return "success";
    } else {
      return "fail";
    }
  }

}

