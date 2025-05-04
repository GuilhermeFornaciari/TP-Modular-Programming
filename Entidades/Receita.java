package Entidades;

import Interfaces.Entidade;
import java.sql.Date;

public class Receita extends Entidade {
    private Float Valor;
    private Date DataPagamento;
    private Date DataCriacao;
    private Integer IdCliente;

    public Receita(Integer Id, Float Valor, Date DataPagamento, Date DataCriacao, Integer IdCliente) {
        this.Id = Id;
        this.Valor = Valor;
        this.DataPagamento = DataPagamento;
        this.DataCriacao = DataCriacao;
        this.IdCliente = IdCliente;
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

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n" +
        "Valor: " + this.getValor() + "\n" +
        "Data de Pagamento: " + this.getDataPagamento() + "\n" +
        "Data de Criação: " + this.getDataCriacao() + "\n" +
        "ID do cliente" + this.IdCliente + "\n" + 
        "--------------------------------------------------";
    }
}
