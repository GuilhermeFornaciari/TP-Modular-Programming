package Programa.Visao.Screens.MovimentoCaixa;

import java.util.List;

import Programa.Modelo.Cliente;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.BaseList;
import Programa.Visao.List.TableActionButton;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.List.TableColumnConfig;
import Programa.Visao.List.TableConfig;

public class MovimentoCaixaList extends BaseList<MovimentoCaixa> {

  MovimentoCaixaForm form;

  IRepositorioGeral<Cliente> clienteRepo;
  IRepositorioGeral<MovimentoCaixa> movimentoCaixaRepo;
  IRepositorioGeral<TipoTransacao> tipoTransacaoRepo;

  public MovimentoCaixaList(IRepositorioGeral<MovimentoCaixa> repo, IRepositorioGeral<Cliente> clienteRepo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo) {
    super(repo, "Movimentos de Caixa");
    this.clienteRepo = clienteRepo;
    this.tipoTransacaoRepo = tipoTransacaoRepo;
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("DataCriacao", "cliente");
    List<String> labels = List.of("Data de Criação", "Cliente");
    List<Double> widths = List.of(0.3, 0.7);

    for (int i = 0; i < labels.size(); i++) {
      tableConfig.addColumnConfig(new TableColumnConfig(names.get(i), labels.get(i), widths.get(i)));
    }

    searchableFields.add("cliente");

  }

  @Override
  public void onUpdateClick(TableActionButton button) {
    super.onUpdateClick(button);
    if (form != null) {
      form.setVisible(false);
      form.dispose();
    }
    form = new MovimentoCaixaForm(repo, clienteRepo, tipoTransacaoRepo, TableAction.UPDATE);
    form.registerObserver(this);
    form.setVisible(true);
    try {
      form.populateForm(repo.pegar_um(button.getItemId()));
      form.setupItemMovimentoScreen();
    } catch (Exception e) {
      e.printStackTrace();
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
    form = new MovimentoCaixaForm(repo, clienteRepo, tipoTransacaoRepo, TableAction.CREATE);
    form.registerObserver(this);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }

}
