package Persistencia;

import java.util.ArrayList;

import Modelo.Entidade;
import Visao.NotFoundException;

public interface IRepositorioGeral<T extends Entidade> {
  Integer criar(T entidade);
  void atualizar(T entidade) throws NotFoundException;
  void deletar(T entidade) throws NotFoundException;
  T pegar_um(int id) throws NotFoundException;
  ArrayList<T> pegar_todos();
}
