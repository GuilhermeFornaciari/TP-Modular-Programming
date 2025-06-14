package Programa.Modelo;
public abstract class Entidade {
  private Integer id; 
  public Entidade(Integer id) { this.id = id; } 
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  
  @Override
  public String toString() { return "Id: " + id + "\n"; }
}