package Entidades;

<<<<<<< HEAD
import Interfaces.Entidade;
import java.sql.Date;

public class Despesa extends Entidade {
    private Float Valor;
    private Date DataPagamento;
    private Date DataCriacao;

    public Despesa(Integer Id, Float Valor, Date DataPagamento, Date DataCriacao) {
        this.Id = Id;
        this.Valor = Valor;
        this.DataPagamento = DataPagamento;
        this.DataCriacao = DataCriacao;
    }

    public Float getValor() {
        return Valor;
    }

    public void setValor(Float valor) {
        Valor = valor;
    }

    public Date getDataPagamento() {
        return DataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        DataPagamento = dataPagamento;
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n" +
        "Valor: " + this.getValor() + "\n" +
        "Data de Pagamento: " + this.getDataPagamento() + "\n" +
        "Data de Criação: " + this.getDataCriacao() + "\n" +
        "--------------------------------------------------";

    }
}
=======

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

  
  @Override
  public String toString() {
    return "R$ " + this.Valor;
  }
}
>>>>>>> main/main
