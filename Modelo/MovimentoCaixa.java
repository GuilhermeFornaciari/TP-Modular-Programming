package Modelo;

import java.sql.Date;
import java.util.ArrayList;

public class MovimentoCaixa extends Entidade {
    private Date DataCriacao;
    private ArrayList<Despesa> despesas;
    private ArrayList<Receita> receitas;
    private Integer IdCliente;
    

    public MovimentoCaixa(Integer Id, Date DataCriacao, Integer IdCliente) {
        this.Id = Id;
        this.DataCriacao = DataCriacao;
        this.IdCliente = IdCliente;
        this.despesas = new ArrayList<Despesa>();
        this.receitas = new ArrayList<Receita>();
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
    

    public void adicionarReceita(Receita receita) {
        receitas.add(receita);
    }

    public void removerReceita(Receita receita) {
        receitas.removeIf(r -> r.getId().equals(receita.getId()));
    }

    public void adicionarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public void removerDespesa(Despesa Despesa) {
        despesas.removeIf(r -> r.getId().equals(Despesa.getId()));
    }

    @Override
    public String toString() {
        String despesas = this.despesas.toString();
        String receitas = this.receitas.toString();
        return "ID: " + this.getId() + "\n" +
        "Data de Criação: " + this.getDataCriacao() + "\n" +
        "Id do cliente: " + IdCliente +
        "\nReceitas: " + receitas + "\nDespesas: " + despesas +
        "\n####################################################";
    }
}
