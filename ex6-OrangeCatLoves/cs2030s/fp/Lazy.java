package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  private Lazy(Producer<? extends T> producer, Maybe<T> value) {
    this.producer = producer;
    this.value = value;
  }

  public static <T> Lazy<T> of(T v) {
    return new Lazy<>(null, Maybe.some(v));
  }

  public static <T> Lazy<T> of(Producer<? extends T> p) {
    return new Lazy<>(p, Maybe.none());
  }

  public T get() { //Returns the value inside Maybe<T>
    T v = this.value.orElseGet(this.producer);
    this.value = Maybe.some(v); 
    return v; 
  }

  @Override
  public String toString() {
    Transformer<T, String> transformer = x -> String.valueOf(x);
    return this.value.<String>map(transformer).orElse("?");
  }

  public <R> Lazy<R> map(Transformer<? super T, ? extends R> transformer) {
    Producer<R> p = () -> transformer.transform(this.get());
    return Lazy.of(p);
    //return Lazy.of(this.value.<R>map(transformer).orElse(null));
  }

  public <R> Lazy<R> flatMap(Transformer<? super T, 
      ? extends Lazy<? extends R>> transformer) {
    Producer<R> p = () -> transformer.transform(this.get()).get();
    //Transformer<Maybe<T>, Lazy<T>> t = (x) -> Lazy.of(x.orElse(null));
    return Lazy.of(p);
  }

  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) {
    Producer<Boolean> p = () -> bc.test(this.get());
    return Lazy.of(p);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Lazy<?>) {
      Lazy<?> lazy = (Lazy<?>) obj;
      if (this.get().equals(lazy.get())) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public <S, R> Lazy<R> combine(Lazy<? extends S> lazy, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    Producer<R> p = () -> combiner.combine(this.get(), lazy.get());
    return Lazy.of(p);
  }
}
