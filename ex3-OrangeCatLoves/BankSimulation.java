import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Low Wei Bin Lab 16D
 * @version CS2030S AY23/24 Semester 2
 */ 
class BankSimulation extends Simulation {
  /** 
   * The availability of counters in the bank. 
   */
  private boolean[] available; // Should not be public

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents; // Should not be public. Same as other class.

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounter = sc.nextInt();
    int maxCounterQueueSize = sc.nextInt(); // Reads the max counter queue size
    int m = sc.nextInt(); // Reads the max queue capacity

    Queue<Customer> queue = new Queue<Customer>(m); // Entrance Queue
    Seq<Counter> seq = new Seq<Counter>(numOfCounter);
    Bank bank = new Bank(numOfCounter, seq, queue); // Initialises the entire bank
    bank.initialiseCounter(maxCounterQueueSize);

    int id = 0;

    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int whichTask = sc.nextInt(); // 1 indicates withdrawal, 0 indicates deposit
      int amount = sc.nextInt(); // Amount of money customer wants to deposit/withdraw
      Customer customer = new Customer(id, whichTask, serviceTime, amount);
      initEvents[id] = new Arrival(arrivalTime, customer, bank);
      id += 1;
    }
  }
  

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
