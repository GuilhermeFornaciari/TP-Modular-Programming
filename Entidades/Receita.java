package Entidades;

import java.sql.Date;

import Interfaces.Entidade;

public class Receita extends Entidade{
  Integer Id;
  Float Valor;
  Date DataPagamento;
  Date DataCriacao;
  Integer IdCliente;
  public Receita(Integer Id, Float Valor, Date DataPagamento, Date DataCriacao){
    this.Id = Id;
    this.Valor = Valor;
    this.DataPagamento = DataPagamento;
    this.DataCriacao = DataCriacao;
  }
  
  @Override
  public String toString() {
    return "R$ " + this.Valor;
  }
}
