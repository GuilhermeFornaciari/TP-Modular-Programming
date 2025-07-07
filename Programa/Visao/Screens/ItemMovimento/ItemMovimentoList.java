package Programa.Visao.Screens.ItemMovimento;

import java.util.List;

import Programa.Modelo.ItemMovimento;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.BaseList;
import Programa.Visao.List.TableActionButton;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.List.TableColumnConfig;
import Programa.Visao.List.TableConfig;
import Programa.Visao.Screens.MovimentoCaixa.MovimentoCaixaForm;

public class ItemMovimentoList extends BaseList<ItemMovimento> {

  ItemMovimentoForm form;
  IRepositorioGeral<TipoTransacao> tipoTransacaoRepo;

  public ItemMovimentoList(IRepositorioGeral<ItemMovimento> repo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo) {
    super(repo, "Item Movimento");
    repo.registerObserver(this);
    this.tipoTransacaoRepo = tipoTransacaoRepo;
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("tipo", "dataCriacao", "dataPagamento", "descricao", "valor");
    List<String> labels = List.of("Tipo", "Data de Criação", "Data de Pagamento", "Descrição", "Valor");
    List<Double> widths = List.of(0.2, 0.1, 0.1, 0.4, 0.2);

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
    form = new ItemMovimentoForm(repo, tipoTransacaoRepo, TableAction.UPDATE);
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
    if (form != null) {
      form.setVisible(false);
      form.dispose();
    }
    form = new ItemMovimentoForm(repo, tipoTransacaoRepo, TableAction.CREATE);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }


}
