package Programa.Modelo;

public abstract class Entidade {
  public Integer Id;

      public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Porque vc tá printando classe abstrata?";
    }
}
