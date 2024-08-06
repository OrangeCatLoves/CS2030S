/**
 * A generic box storing an item.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Low Wei Bin A0272304N (16D)

 */
class Box<T> {

  private final T content;
  private static final Box<?> EMPTY_BOX = new Box(null);

  private Box(T content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object t) {
    if (this == t) {
      return true;
    } else if (t == null) {
      return false;
    } else if (t instanceof Box<?>) {
      Box box = (Box) t;
      if (this.content == null) {
        if (t == null) {
          return true;
        } else {
          return false;
        }
      } else if (this.content.equals(box.content)) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    if (this.content == null) {
      return "[]";
    }
    String s = "[";
    s = s + this.content;
    s = s + "]";
    return s;
  }

  public static <T> Box<T> of(T item) {
    if (item == null) {
      return null;
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    Box box = (Box<T>) new Box(item);
    return box;
  }

  public static <T> Box<T> empty() {
    return (Box<T>) EMPTY_BOX;
  }

  public boolean isPresent() {
    if (this.content == null) {
      return false;
    } else {
      return true;
    }
  }

  public static <T> Box<T> ofNullable(T item) {
    if (item == null) {
      return empty();
    } else {
      Box<T> box = Box.of(item);
      return box;
    }
  }

  public Box<T> filter(BooleanCondition<? super T> bc) {
    if (this.content == null) {
      return Box.empty();
    } else if (bc.test(this.content)) {
      return this;
    } else {
      return Box.empty();
    }
  }

  public <R> Box<R> map(Transformer<? super T, ? extends R> transformer) {
    if (this.content == null) {
      return empty();
    } else {
      Object obj = transformer.transform(this.content);
      if (obj == null) {
        return empty();
      }
      Box box = Box.of(obj);
      return box;
    }
  }

}

