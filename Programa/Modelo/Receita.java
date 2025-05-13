package Programa.Modelo;

import java.sql.Date;

public class Receita extends Entidade {
    private Float Valor;
    private Date DataPagamento;
    private Date DataCriacao;
    private Integer IdMovimentoCaixa;

    public Receita(Integer Id, Float Valor, Date DataPagamento, Date DataCriacao, Integer IdMovimentoCaixa) {
        this.Id = Id;
        this.Valor = Valor;
        this.DataPagamento = DataPagamento;
        this.DataCriacao = DataCriacao;
        this.IdMovimentoCaixa = IdMovimentoCaixa;
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

    public Integer getIdMovimentoCaixa() {
        return IdMovimentoCaixa;
    }

    public void setIdMovimentoCaixa(Integer idMovimentoCaixa) {
        IdMovimentoCaixa = idMovimentoCaixa;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n" +
        "Valor: " + this.getValor() + "\n" +
        "Data de Pagamento: " + this.getDataPagamento() + "\n" +
        "Data de Criação: " + this.getDataCriacao() + "\n" +     
        "ID do movimento de caixa: " + this.IdMovimentoCaixa + "\n";

    }
}
