import java.sql.Date;

import Interfaces.Entidade;

public class Despesa extends Entidade{
  Integer Id;
  Float Valor;
  Date DataPagamento;
  Date DataCriacao;
  public Despesa(Integer Id, Float Valor, Date DataPagamento, Date DataCriacao){
    this.Id = Id;
    this.Valor = Valor;
    this.DataPagamento = DataPagamento;
    this.DataCriacao = DataCriacao;
  }

}
