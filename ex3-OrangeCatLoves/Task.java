// author @ Low Wei Bin Lab 16D
abstract class Task {

  private Customer owner;

  public Task(Customer customer) {
    this.owner = customer;
  }

  public Customer getCustomer() {
    return this.owner;
  }

  public void performTask(Customer customer, Counter counter) {
    return;
  }

  public String statusTransaction() {
    return "Task Execution";
  }

}

