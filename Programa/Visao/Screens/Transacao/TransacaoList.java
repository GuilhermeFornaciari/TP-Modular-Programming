package Programa.Visao.Screens.Transacao;

import java.util.List;

import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.BaseList;
import Programa.Visao.List.TableActionButton;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.List.TableColumnConfig;
import Programa.Visao.List.TableConfig;

public class TransacaoList extends BaseList<TipoTransacao> {

  TransacaoForm form;
  public TransacaoList(IRepositorioGeral<TipoTransacao> repo) {
    super(repo, "Tipos de Transação");
    search("cliente", "descricao");
    populateTableModel();
    updateTable();
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("tipo", "descricao");
    List<String> labels = List.of("Tipo", "Descrição");
    List<Double> widths = List.of(0.3, 0.7);

    for (int i = 0; i < labels.size(); i++) {
      tableConfig.addColumnConfig(new TableColumnConfig(names.get(i), labels.get(i), widths.get(i)));
    }
  }

  @Override
  public void onUpdateClick(TableActionButton button) {
    super.onUpdateClick(button);
    if (form != null) {
      form.setVisible(false);
      form.dispose();
    }
    form = new TransacaoForm(repo, TableAction.UPDATE);
    form.registerObserver(this);
    form.setVisible(true);
    try {
      form.populateForm(repo.pegar_um(button.getItemId()));
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }

  @Override
  public void onCreateClick() {
    if (form != null) {form.setVisible(false); form.dispose();}
    form = new TransacaoForm(repo, TableAction.CREATE);
    form.registerObserver(this);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }

}

