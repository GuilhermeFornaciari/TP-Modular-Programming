package Modelo;

public class NotFoundException extends Exception {
  String message = "Não foi encontrado o elemento desejado";
  public NotFoundException(){
    super();
  }
}
