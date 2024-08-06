/**
 * The Seq<T> class for CS2030S 
 *
 * @author Low Wei Bin 16D
 * @version CS2030S AY23/24 Semester 2
 */
class Seq<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] seq;

  Seq(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.seq = temp;
  }

  public void set(int index, T item) {
    this.seq[index] = item;
  }

  public T get(int index) {
    return this.seq[index];
  }

  public T min() {
    T smallest = null;
    int length = this.seq.length;
    T[] tmp = this.seq;
    for (int i = 0; i < length; i++) {
      if (smallest == null) {
        smallest = tmp[i];
      }

      if (smallest.compareTo(tmp[i]) > -1) {
        smallest = tmp[i];
      }
    }
    /*for (T t : seq) {
      if (t.compareTo(smallest) > 0) {
        smallest = t;
      }
    }*/
    return smallest;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.seq.length; i++) {
      s.append(i + ":" + this.seq[i]);
      if (i != this.seq.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
