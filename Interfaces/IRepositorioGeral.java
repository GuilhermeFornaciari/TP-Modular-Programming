package Interfaces;

import java.util.ArrayList;

import excecoes.NotFoundException;

public interface IRepositorioGeral {
  void criar(Entidade entidade);
  void atualizar(Entidade entidade) throws NotFoundException;
  void deletar(Entidade entidade) throws NotFoundException;
  Entidade pegar_um(int id) throws NotFoundException;
  ArrayList<Entidade> pegar_todos();
}
