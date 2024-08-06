/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)

 */

class DivisibleBy implements BooleanCondition<Integer> {

  private int x;

  public DivisibleBy(int x) {
    this.x = x;
  }

  public boolean test(Integer i) {
    if (i % this.x == 0) {
      return true;
    } else {
      return false;
    }
  }

}
