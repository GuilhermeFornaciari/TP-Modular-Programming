package Programa.Visao.Screens.ItemMovimento;

import java.sql.Date;

import Programa.Modelo.ItemMovimento;
import Programa.Modelo.TipoTransacao;
import Programa.Visao.Builder.Builder;
import Programa.Visao.Builder.BuilderValidationException;

public class ItemMovimentoBuilder extends Builder<ItemMovimento> {

  private Integer id;
  private TipoTransacao tipo;
  private Date dataCriacao;
  private Date dataPagamento;
  private String descricao;
  private Float valor;

  public ItemMovimentoBuilder() {
  }

  @Override
  public ItemMovimento build() throws BuilderValidationException {
    validate();
    if (!exceptionMap.isEmpty()) throw new BuilderValidationException(exceptionMap);
    
    ItemMovimento item = new ItemMovimento(id, dataPagamento, descricao, tipo, valor);
    item.setDataCriacao(dataCriacao);
    return item;
  }

  public ItemMovimentoBuilder withId(Integer id) {
    this.id = id;
    return this;
  }

  public ItemMovimentoBuilder withTipo(TipoTransacao tipo) {
    this.tipo = tipo;
    return this;
  }

  public ItemMovimentoBuilder withDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
    return this;
  }

  public ItemMovimentoBuilder withDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
    return this;
  }

  public ItemMovimentoBuilder withDescricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  public ItemMovimentoBuilder withValor(Float valor) {
    this.valor = valor;
    return this;
  }

  @Override
  protected void validate() {
    exceptionMap.clear();
    
    if (valor == null) {
      addException("valor", "Valor não pode ser nulo");
    } else if (valor <= 0) {
      addException("valor", "Valor deve ser maior que zero");
    }

    if (tipo == null) {
      addException("tipo", "Tipo de transação não pode ser nulo");
    }

    if (dataCriacao == null) {
      addException("dataCriacao", "Data de criação não pode ser nula");
    }

    if (dataPagamento == null) {
      addException("dataPagamento", "Data de pagamento não pode ser nula");
    } else if (dataCriacao != null && dataPagamento.before(dataCriacao)) {
      addException("dataPagamento", "Data de pagamento não pode ser anterior à data de criação");
    }

    if (descricao == null || descricao.trim().isEmpty()) {
      addException("descricao", "Descrição não pode ser vazia");
    }


  }
}