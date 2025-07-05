package Programa.Visao;

import java.lang.reflect.Field;

public class BaseBuilder {

  public Object getProperty(String propertyName) {
    try {
      Field field = this.getClass().getDeclaredField(propertyName);
      field.setAccessible(true); // allows access to private fields
      return field.get(this);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
