package Programa.Visao.Cliente;

import Programa.Modelo.Cliente;

public interface ClienteFormListener {
  default void onClienteChanged(Cliente cliente) {};
  default void onClienteChanged(Cliente cliente, boolean isNew) {}; 
}