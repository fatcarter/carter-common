package cn.fatcarter.common.lang;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tuple<T1, T2> {
    private T1 t1;
    private T2 t2;

    public static <T1, T2> Tuple<T1, T2> empty() {
        return new Tuple<>(null, null);
    }

    public static <T1, T2> Tuple<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple<>(t1, t2);
    }

    public T1 getFirst() {
        return this.t1;
    }

    public void setFirst(T1 t1) {
        this.t1 = t1;
    }

    public T2 getSecond() {
        return this.t2;
    }

    public void setSecond(T2 t2) {
        this.t2 = t2;
    }
}
