package Programa.Visao.Screens.MovimentoCaixa;

import java.sql.Date;
import java.util.ArrayList;

import Programa.Modelo.Cliente;
import Programa.Modelo.ItemMovimento;
import Programa.Modelo.MovimentoCaixa;
import Programa.Visao.Builder.Builder;
import Programa.Visao.Builder.BuilderValidationException;

public class MovimentoCaixaBuilder extends Builder<MovimentoCaixa> {

  private Integer id;
  private Date DataCriacao;
  private Cliente cliente;
  private ArrayList<ItemMovimento> itemMovimentos;
 
  public MovimentoCaixaBuilder() {
    this.itemMovimentos = new ArrayList<ItemMovimento>();
  }

  @Override
  public MovimentoCaixa build() throws BuilderValidationException {
    validate();
    if (!exceptionMap.isEmpty()) {
      throw new BuilderValidationException(exceptionMap);
    }
    if (id == null) {
      MovimentoCaixa movimento = new MovimentoCaixa(DataCriacao, cliente);
      itemMovimentos.forEach(movimento::adicionarTransacao);
      return movimento;
    }
    MovimentoCaixa movimento = new MovimentoCaixa(id, DataCriacao, cliente);
    itemMovimentos.forEach(movimento::adicionarTransacao);
    return movimento;
  }

  public MovimentoCaixaBuilder withId(Integer id) {
    this.id = id;
    return this;
  }

  public MovimentoCaixaBuilder withDataCriacao(Date DataCriacao) {
    this.DataCriacao = DataCriacao;
    return this;
  }

  public MovimentoCaixaBuilder withCliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  public MovimentoCaixaBuilder withItemMovimento(ItemMovimento itemMovimento) {
    this.itemMovimentos.add(itemMovimento);
    return this;
  }

  public MovimentoCaixaBuilder withItemMovimentos(ArrayList<ItemMovimento> itemMovimentos) {
    this.itemMovimentos.addAll(itemMovimentos);
    return this;
  }

  @Override
  protected void validate() {
    exceptionMap.clear();

    if (DataCriacao == null) {
      addException("DataCriacao", "Data de criação não pode ser nula");
    }

    if (cliente == null) {
      addException("cliente", "Cliente não pode ser nulo");
    }
  }
}