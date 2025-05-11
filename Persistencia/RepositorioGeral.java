package Persistencia;
 
 import java.util.ArrayList;

import Modelo.Entidade;
import Visao.NotFoundException;
 
 public class RepositorioGeral<T extends Entidade> implements Persistencia.IRepositorioGeral<T> {
   private ArrayList<T> lista = new ArrayList<>();
   private Integer IdCounter = 0;  
   @Override
   public Integer criar(T entidade) {
     entidade.Id = this.IdCounter;
     lista.add(entidade);
     this.IdCounter++;
     return entidade.Id;
   }
 
   @Override
   public void atualizar(T entidade) throws NotFoundException {
     for(Integer i = 0; i < lista.size(); i++){
       if (lista.get(i).getId().equals(entidade.Id)){
        lista.set(i, entidade);
        return;
       }
     }
   }
 
   @Override
   public void deletar(T entidade) throws NotFoundException {
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId().equals(entidade.getId())) { // Usando getId para comparar
               lista.remove(i);
               return;
           }
       }
       throw new NotFoundException();
   }
 
   @Override
   public T pegar_um(int id) throws NotFoundException {
     for(Integer i = 0; i < lista.size(); i++){
       if (lista.get(i).Id == id){
         return lista.get(i);
       }
     }
     throw new NotFoundException();
   }
 
   @Override
   public ArrayList<T> pegar_todos() {
       return lista;
   }
 
   @Override
   public String toString() {   
     String mensagem = "";
     for(Integer i = 0; i < lista.size(); i++){
       mensagem += lista.get(i).toString() + "\n";
     }
     return mensagem;
   }
}
