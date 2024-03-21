//package cn.fatcarter.common.reactive;
//
//import java.util.Objects;
//import java.util.Set;
//
//public class Ref<T> {
//    private T rawValue;
//    private Set<EffectObject> deps;
//
//    public static <T> Ref<T> v(T value) {
//        return new Ref<>(value);
//    }
//
//    public Ref(T rawValue) {
//        this.rawValue = rawValue;
//    }
//
//    public T getValue() {
//        trackEffect();
//        return this.rawValue;
//    }
//
//    public void setValue(T value) {
//        if (isChange(value)) {
//            this.rawValue = value;
//            triggerEffect();
//        }
//    }
//
//    private boolean isChange(T value) {
//        return Objects.equals(value, this.rawValue);
//    }
//
//    private void trackEffect() {
//
//    }
//
//    private void triggerEffect() {
//
//    }
//
//}
