package Repositorio;

import java.util.ArrayList;

import Interfaces.Entidade;
import excecoes.NotFoundException;

public class RepositorioGeral implements Interfaces.IRepositorioGeral {
  private ArrayList<Entidade> lista = new ArrayList<>();

  @Override
  public void criar(Entidade entidade) {  
    lista.add(entidade);
  }

  @Override
  public void atualizar(Entidade entidade) throws NotFoundException {
    for(Integer i = 0; i < lista.size(); i++){
      if (lista.get(i).Id == entidade.Id){
        lista.remove(i);
        lista.add(i,entidade);
        return;
      }
    }
  }

  @Override
  public void deletar(Entidade entidade) throws NotFoundException{
    for(Integer i = 0; i < lista.size(); i++){
      if (lista.get(i).Id == entidade.Id){
        lista.remove(i);
        return;
      }
    }
    throw new NotFoundException();

  }

  @Override
  public Entidade pegar_um(int id) throws NotFoundException {
    for(Integer i = 0; i < lista.size(); i++){
      if (lista.get(i).Id == id){
        return lista.get(i);
      }
    }
    throw new NotFoundException();
  }

  @Override
  public ArrayList<Entidade> pegar_todos() {
      return lista;
  }
  
}
