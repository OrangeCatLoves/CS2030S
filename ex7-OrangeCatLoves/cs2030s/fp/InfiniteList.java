package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

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
    this.head = Lazy.of(() -> Maybe.some(head));
    //this.head = Lazy.of(Maybe.of(head));
    this.tail = Lazy.of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) { // Constructor
    // TODO
    this.head = head;
    this.tail = tail;
  }

  public T head() {
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

  public static <T> InfiniteList<T> sentinel() {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
