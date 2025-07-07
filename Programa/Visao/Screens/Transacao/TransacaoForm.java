package Programa.Visao.Screens.Transacao;

import java.awt.event.ActionEvent;
import java.util.Map;

import Programa.Modelo.TipoDespesaReceita;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.Form.BaseForm;
import Programa.Visao.List.TableActionButton.TableAction;

public class TransacaoForm extends BaseForm<TipoTransacao> {

  public TransacaoForm(IRepositorioGeral<TipoTransacao> repo, TableAction action) {
    super(repo, action);
    this.setupFields();
  }

  public void setupFields() {
    String[] tipoOptions = {"Receita", "Despesa"};
    addTextField("id", "Id");
    addDropdownField("tipo", "Tipo", tipoOptions);
    addTextField("descricao", "Descrição");
  }

  @Override
  public void onUpdate(Map<String, ?> data) throws BuilderValidationException {
    super.onUpdate(data);
    System.out.println("OnUpdate");
    TransacaoBuilder builder = new TransacaoBuilder()
        .withId(Integer.parseInt((String) data.get("id")))
        .withTipo(data.get("tipo") == "Receita" ? TipoDespesaReceita.RECEITA : TipoDespesaReceita.DESPESA)
        .withDescricao((String) data.get("descricao"));
    TipoTransacao updatingData = builder.build();
    try {
      this.repo.atualizar(updatingData);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void onCreate(Map<String, ?> data) throws BuilderValidationException {
    super.onUpdate(data);
    System.out.println("OnUpdate");
    TransacaoBuilder builder = new TransacaoBuilder()
        .withTipo(data.get("tipo") == "Receita" ? TipoDespesaReceita.RECEITA : TipoDespesaReceita.DESPESA)
        .withDescricao((String) data.get("descricao"));
    TipoTransacao creatingData = builder.build();
    try {
      this.repo.criar(creatingData);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  public void actionPerformed(ActionEvent e) {}
}
