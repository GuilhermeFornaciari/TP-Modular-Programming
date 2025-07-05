package Programa.Visao.Cliente;

import java.util.List;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.BaseForm;
import Programa.Visao.ObservableAction;
import Programa.Visao.Subscriber;
import Programa.Visao.TableColumnConfig;
import Programa.Visao.TableConfig;
import Programa.Visao.BaseList.BaseList;
import Programa.Visao.BaseList.TableActionButton;
import Programa.Visao.BaseList.TableActionButton.TableAction;

public class ClienteList extends BaseList<Cliente> implements Subscriber<Cliente> {

  public ClienteList(IRepositorioGeral<Cliente> repo) {
    super(repo);
    repo.registerObserver(this);
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("Nome", "CPF", "Email", "CEP", "DataNascimento");
    List<String> labels = List.of("Nome", "CPF", "Email", "CEP", "Nascimento"); 
    List<Double> widths = List.of(0.2, 0.2, 0.2, 0.2, 0.2); 

    for (int i=0; i<labels.size(); i++) {
      tableConfig.addColumnConfig(new TableColumnConfig(names.get(i), labels.get(i), widths.get(i)));
    }

  }

  @Override
  public void onNotify(ObservableAction action) {
    System.out.println("OnNotify");
    getTableData();
    System.out.println("PopulateTableModel");
    populateTableModel();
    System.out.println("Before ");
    this.reloadTableData();
  }

  @Override
  public void onUpdateClick(TableActionButton button) {
    super.onUpdateClick(button);
    ClienteFormCm form = new ClienteFormCm(repo, TableAction.UPDATE);
    form.setVisible(true);
    try {
      form.populateForm(repo.pegar_um(button.getItemId()));
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }

}
