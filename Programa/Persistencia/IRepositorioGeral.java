package Programa.Persistencia;

import Programa.Modelo.Entidade;
import Programa.Modelo.NotFoundException;
import Programa.Visao.Observable.Publisher;

import java.util.ArrayList;

public interface IRepositorioGeral<T extends Entidade> extends Publisher<T> {
  Integer criar(T entidade);
  void atualizar(T entidade) throws NotFoundException;
  void deletar(T entidade) throws NotFoundException;
  T pegar_um(int id) throws NotFoundException;
  ArrayList<T> pegar_todos();
}
