package Programa.Visao.Screens.Cliente;

import java.util.List;


import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.BaseList;
import Programa.Visao.List.TableActionButton;
import Programa.Visao.List.TableColumnConfig;
import Programa.Visao.List.TableConfig;
import Programa.Visao.List.TableActionButton.TableAction;

public class ClienteList extends BaseList<Cliente> {

  ClienteForm form;
  public ClienteList(IRepositorioGeral<Cliente> repo) {
    super(repo, "Clientes");
    repo.registerObserver(this);
  }

  @Override
  public void setupTableHeader() {
    super.setupTableHeader();
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
  public void onUpdateClick(TableActionButton button) {
    super.onUpdateClick(button);
    if (form != null) {form.setVisible(false); form.dispose();}
    form = new ClienteForm(repo, TableAction.UPDATE);
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
    form = new ClienteForm(repo, TableAction.CREATE);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }


}
