/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)

 */


interface Transformer<T, U> {

  public abstract U transform(T t);

}
