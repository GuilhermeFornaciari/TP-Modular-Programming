package Programa.Modelo;

import java.lang.reflect.Field;

public abstract class Entidade {
  private Integer id; 
  public Entidade(Integer id) { this.id = id; } 
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  
  @Override
  public String toString() { return "Id: " + id + "\n"; }

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