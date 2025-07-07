package Programa.Visao.Screens.Transacao;

import java.util.Objects;

import Programa.Modelo.TipoTransacao;
import Programa.Modelo.TipoDespesaReceita;
import Programa.Visao.Builder.Builder;
import Programa.Visao.Builder.BuilderValidationException;

public class TransacaoBuilder extends Builder<TipoTransacao> {
  
  private Integer id;
  private TipoDespesaReceita tipo;
  private String descricao;

  public TransacaoBuilder() {
    // Initialize with default values if needed
  }

  @Override
  public TipoTransacao build() throws BuilderValidationException {
    validate();
    if (!exceptionMap.isEmpty()) {
      throw new BuilderValidationException(exceptionMap);
    }
    if (id == null) {
      return new TipoTransacao(tipo, descricao);
    }
    return new TipoTransacao(id, tipo, descricao);
  }

  public TransacaoBuilder withId(Integer id) {
    this.id = id;
    return this;
  }
  public TransacaoBuilder withTipo(TipoDespesaReceita tipo) {
    this.tipo = tipo;
    return this;
  }
  public TransacaoBuilder withDescricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  @Override
  protected void validate() {
    exceptionMap.clear();

    if (tipo == null) {
      addException("tipo", "Tipo de transação não pode ser nulo");
    }
    if (descricao == null || descricao.trim().isEmpty()) {
      addException("descricao", "Descrição não pode ser vazia");
    }
  }
}