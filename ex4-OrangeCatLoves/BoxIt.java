/**
 * Takes an item and return the item in a box.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)
 */

class BoxIt<T> implements Transformer<T, Box<T>> {

  private Box<?> boxed;
  
  /*public BoxIt() {
    this.boxed = box;
  }*/

  // Change the map method in Box to check for if argument is instanceof BoxIt ? Then box it ??

  public Box<T> transform(T obj) {
    Box<T> box = Box.of(obj);
    return box;
  }
}
