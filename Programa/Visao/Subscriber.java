package Programa.Visao;

public interface Subscriber<T> {
    default void onNotify(T registry, ObservableAction action) {};
    default void onNotify(ObservableAction action) {};
    default void onNotify() {};
}
