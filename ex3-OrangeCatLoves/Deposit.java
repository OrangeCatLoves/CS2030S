// author @ Low Wei Bin Lab 16D
class Deposit extends Task {

  private int status; // To denote if process has failed or passed

  public Deposit(Customer customer) {
    super(customer);
    this.status = 1; // 1 denotes success, 0 denotes unsuccessful
  }

  @Override
  public void performTask(Customer customer, Counter counter) {
    int amountLeft = counter.getAmount();
    int amountToDeposit = customer.getAmount();
    int newCalculatedAmount = amountLeft + amountToDeposit;
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
    return "deposit $" + String.valueOf(this.getCustomer().getAmount());
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

