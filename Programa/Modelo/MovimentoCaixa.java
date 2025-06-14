package Programa.Modelo;

import java.sql.Date;
import java.util.ArrayList;

public class MovimentoCaixa extends Entidade {
    private Date DataCriacao;
    private ArrayList<ItemMovimento> itemMovimentos;
    private Cliente cliente;

    public MovimentoCaixa(Integer Id, Date DataCriacao, Cliente cliente) {
        super(Id);
        this.cliente = cliente;
        this.DataCriacao = DataCriacao;
        this.itemMovimentos = new ArrayList<ItemMovimento>();
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public Float getvalorTotal() {
        Float soma = Float.parseFloat("0");
        for(ItemMovimento item: this.itemMovimentos){
            soma += item.getValor();
        }
        return soma;
    }

    public void adicionarTransacao(ItemMovimento itemMovimento) {
        itemMovimentos.add(itemMovimento);
    }
    
    public void removerTransacao(Integer idTransacao) {
        itemMovimentos.removeIf(res -> res.getId().equals(idTransacao));
    }



    @Override
    public String toString() {
        String transacoes = this.itemMovimentos.toString();
        return super.toString() +
        "Data de Criação: " + this.getDataCriacao() + "\n" +
        "Cliente: " + this.cliente +
        "Valor total: " + this.getvalorTotal() +
        "\nTransações: " + transacoes +
        "\n####################################################";
    }
}
