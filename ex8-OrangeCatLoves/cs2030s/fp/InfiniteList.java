package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  private static final InfiniteList<?> SENTINEL = new Sentinel();

  private InfiniteList() { // Constructor
    this.head = null; 
    this.tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<? extends T> producer) { 
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer)));
    //return new InfiniteList<>(Lazy.of(Maybe.of(producer.produce())), 
    //Lazy.of(() -> InfiniteList.generate(producer)));
  }

  public static <T> InfiniteList<T> iterate(T seed, 
      Transformer<? super T, ? extends T> next) {
    // TODO
    return new InfiniteList<>(seed, 
        () -> InfiniteList.iterate(next.transform(seed), next));
    //return new InfiniteList<>(Lazy.of(Maybe.of(seed)), 
    //Lazy.of(InfiniteList.iterate(next.transform(seed), next)));
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) { // Constructor
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    //this.head = Lazy.of(Maybe.of(head));
    this.tail = Lazy.of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) { // Constructor
    // TODO
    this.head = head;
    this.tail = tail;
  }

  public T head() { // Returns value inside Lazy<Maybe<T>>
    // TODO
    T result = this.head.get().orElseGet(() -> this.tail.get().head());
    return result;
  }

  public InfiniteList<T> tail() {
    // TODO
    Maybe<T> h = this.head.get();
    Maybe<InfiniteList<T>> t = h.map(x -> this.tail.get());
    return t.orElseGet(() -> this.tail.get().tail());
    /*InfiniteList<T> infiniteList = this;
    while (infiniteList.head.get() == Maybe.none()) {
      infiniteList = infiniteList.tail.get();
    }
    return infiniteList.tail.get();*/
    //return this.tail.get();
    //return new InfiniteList<>();
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>(this.head.map(x -> x.map(mapper)),
        this.tail.map(x -> x.map(mapper)));
    //return new InfiniteList<>(Lazy.of(() -> this.head.get().map(mapper)),
        //Lazy.of(() -> this.tail.get().map(mapper)));
    /*return new InfiniteList<>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail().map(mapper)));*/
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>(this.head.map(x -> x.filter(predicate)),
        this.tail.map(x -> x.filter(predicate)));
    //return new InfiniteList<>(Lazy.of(() -> this.head.get().filter(predicate)),
          //Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  private static class Sentinel extends InfiniteList<Object> { 

    public Sentinel() { // Calling the null constructor
      super();
    }

    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.<R>sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.<Object>sentinel();
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.<Object>sentinel();
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.<Object>sentinel();
    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    @Override
    public String toString() {
      return "-";
    }
  }

  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    InfiniteList<T> infiniteList = (InfiniteList<T>) InfiniteList.SENTINEL;
    return infiniteList;
    //return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    if (n <= 0) {
      return InfiniteList.<T>sentinel();
    } else {
      // Either you map your tail to tail.limit(n) or tail.limit(n-1)
      // Depending on whether there's something existing inside your head
      // or not, which is done by orElseGet(Producer)
      // list var here is referring to the InfiniteList
      return new InfiniteList<T>(this.head, 
          this.tail.map(list -> this.head.get().map(h -> list.limit(n - 1))
          .orElseGet(() -> list.limit(n))));  
    }
    //return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    // Lazy<Maybe<T>> checkHead = this.head.filter(h -> predicate.test(h));
    Lazy<Maybe<T>> checkHead = this.head.map(x -> x.filter(predicate));
    return new InfiniteList<>(checkHead, 
        checkHead.map(h -> h.map(x -> this.tail.get().takeWhile(predicate))
          .orElseGet(() -> this.head.get().map(x -> InfiniteList.<T>sentinel())
            .orElseGet(() -> this.tail.get().takeWhile(predicate)))));
    // Second line of return statement does the null checking for us
    // If the first null check failed since it's not Maybe.none then
    // We continue to take the elements
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    // Done through recursion, using wishful thinking that the accumulated value for
    // the tail is already computed
    U accumulatedValue = this.tail.get().reduce(identity, accumulator);
    if (this.head.get().equals(Maybe.none())) { // If there's nothing in the head,
      // Skip it and go for the next head value
      return accumulatedValue;
    }
    return accumulator.combine(accumulatedValue, this.head());
    //return accumulator.combine(this.head, accumulatedValue); 
    /*U currentValue = identity;
    U nextAccumulatedValue = this.tail.get().
      reduce(accumulator.combine(currentValue, this.head()), accumulator);

    return nextAccumulatedValue;*/ 
    //return null;
  }

  public long count() {
    // TODO
    return this.reduce(0, (x, y) -> x + 1); 
    //return 0;
  }

  public List<T> toList() {
    // TODO
    List<T> toList = new ArrayList<>();
    InfiniteList<T> fromList = this;
    while (!fromList.isSentinel()) {
      fromList.head.get().ifPresent(head -> toList.add(head));
      fromList = fromList.tail.get();
    }
    return toList; 
    //return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
