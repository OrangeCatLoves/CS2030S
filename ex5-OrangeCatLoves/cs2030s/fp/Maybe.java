package cs2030s.fp;

import java.util.NoSuchElementException;

// Author @ Low Wei Bin A0272304N

public abstract class Maybe<T> {

  private static Maybe<?> NONE = new None();

  protected abstract T get();

  public abstract String toString();

  public abstract Maybe<T> filter(BooleanCondition<? super T> bc);

  public abstract <R> Maybe<R> map(Transformer<? super T, ? extends R> transformer);

  public abstract <R> Maybe<R> flatMap(Transformer<? super T, 
      ? extends Maybe<? extends R>> transformer);
  
  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void ifPresent(Consumer<? super T> consumer);

  static class None extends Maybe<Object> {

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> bc) {
      return (Maybe<Object>) none();
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super Object, ? extends R> transformer) {
      return none();
    }

    @Override
    public <R> Maybe<R> flatMap(Transformer<? super Object, 
        ? extends Maybe<? extends R>> transformer) {
      return none();
    }

    @Override
    public Object orElse(Object t) {
      return t;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return (Object) producer.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> consumer) {
      return;
    }

    @Override
    public boolean equals(Object t) {
      return this == t;
    }

    @Override
    public String toString() {
      return "[]";
    }
  }

  static class Some<T> extends Maybe<T> {

    private T content;

    @Override
    protected T get() {
      return this.content;
    }

    private Some(T t) {
      this.content = t;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> bc) {
      if (this.content == null) {
        return Maybe.some(null);
      } else if (bc.test(this.get())) {
        return (Maybe<T>) this;
      } else {
        return none();
      }
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super T, ? extends R> transformer) {
      Maybe<R> obj = Maybe.some(transformer.transform(this.get()));
      return obj;
    }

    @Override
    public <R> Maybe<R> flatMap(Transformer<? super T, ? extends Maybe<? extends R>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<R> obj = (Maybe<R>) transformer.transform(this.get());
      return obj;
      
    }

    @Override
    public T orElse(T t) {
      return (T) this.content;
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return (T) this.content;
    }

    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(this.content);
      return;
    }

    @Override
    public boolean equals(Object t) {
      if (this == t) {
        return true;
      } else if (t == null) {
        return false;
      } else if (t instanceof Some<?>) {
        Some<?> some = (Some<?>) t;
        if (this.content == null) {
          if (some.content == null) {
            return true;
          } else {
            return false;
          }
        } else if (this.content.equals(some.content)) {
          return true;
        } else {
          return false;
        }
      }
      return false;
    }

    @Override 
    public String toString() {
      return "[" + this.content + "]";
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> maybe = (Maybe<T>) NONE;
    return maybe;
  }

  public static <T> Maybe<T> some(T t) {
    Maybe<T> some = new Some<>(t);
    return some;
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none();
    } else {
      return new Some<T>(t);
    }
  }

}
