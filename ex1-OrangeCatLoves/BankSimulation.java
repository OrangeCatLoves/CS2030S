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
    int numOfCounters = sc.nextInt();

    Counters[] available = new Counters[numOfCounters];
    
    Bank bank = new Bank(numOfCounters, available); // Initialises the entire bank
    bank.initialiseCounters();

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customers customer = new Customers(id, serviceTime); // Creates a new customer each time with their own designated serviceTime
      initEvents[id] = new Arrival(arrivalTime, customer, bank); // counterID set to 0 initially
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
