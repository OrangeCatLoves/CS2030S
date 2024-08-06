/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)

 */

class LongerThan implements BooleanCondition<String> {

  private int y;

  public LongerThan(int y) {
    this.y = y;
  }

  public boolean test(String str) {
    if (str.length() > this.y) {
      return true;
    } else {
      return false;
    }
  }

}
