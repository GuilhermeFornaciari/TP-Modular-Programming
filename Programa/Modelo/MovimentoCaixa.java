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
    public MovimentoCaixa(Date DataCriacao, Cliente cliente) {
      super(0);
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

    public ArrayList<ItemMovimento> getItemMovimentos(){
        return this.itemMovimentos;
    }

    public Float getvalorTotal() {
        Float soma = Float.parseFloat("0");
        for(ItemMovimento item: this.itemMovimentos){
            soma += item.getValor();
        }
        return soma;
    }

    public void adicionarTransacao(ItemMovimento itemMovimento) {
      if (itemMovimentos.size() > 0) {
        itemMovimento.setId(itemMovimentos.getLast().getId() + 1);
      } else {
        itemMovimento.setId(0);
      }
        itemMovimentos.add(itemMovimento);
    }
    
    public void removerTransacao(Integer idTransacao) {
        itemMovimentos.removeIf(res -> res.getId().equals(idTransacao));
    }

    public void atualizarTransacao(ItemMovimento itemMovimento) {
      for (Integer i=0; i< itemMovimentos.size(); i++) {
        if (!itemMovimentos.get(i).getId().equals(itemMovimento.getId())) continue;
        itemMovimentos.set(i, itemMovimento);
        return;
      }
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
