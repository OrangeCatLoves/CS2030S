/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)

 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  public Integer transform(Object obj) {
    String result = String.valueOf(obj.hashCode());
    String s = "";
    for (int i = result.length() - 1; i >= result.length() - k; i--) {
      s = result.charAt(i) + s;
    }
    int intValue = Integer.parseInt(s);
    Integer finalResult = Integer.valueOf(intValue);
    return finalResult;
  }
}
