import java.util.Objects;

public class Tuple<K,V> {
    K value1;
    V value2;

    public Tuple (K value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public K getValue1 () {
        return value1;
    }

    public void setValue1 (K value1) {
        this.value1 = value1;
    }

    public V getValue2 () {
        return value2;
    }

    public void setValue2 (V value2) {
        this.value2 = value2;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(value1, tuple.value1) && Objects.equals(value2, tuple.value2);
    }

    @Override
    public int hashCode () {
        return Objects.hash(value1, value2);
    }
}
