package Programa.Visao;

import java.util.ArrayList;
import java.util.List;

public interface Publisher<T> {
  List<Subscriber> subscribers = new ArrayList<>();
  default public void registerObserver(Subscriber<T> observer) {
    System.out.println("REgistered new observer");
    subscribers.add(observer);
  };
  default  void notifySubscribers(T data, ObservableAction action) {
    for (Subscriber subscriber: subscribers) {
      subscriber.onNotify(data, action);
    }
  };
  default  void notifySubscribers(ObservableAction action) {
    for (Subscriber subscriber: subscribers) {
      subscriber.onNotify(action);
    }
  };
  default  void notifySubscribers() {
    for (Subscriber subscriber: subscribers) {
      subscriber.onNotify();
    }
  };
}
