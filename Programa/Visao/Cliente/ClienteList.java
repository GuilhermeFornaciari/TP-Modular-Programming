package Programa.Visao.Cliente;

import java.awt.GridBagConstraints;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
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
  public void setupTableHeader() {
    // TODO Auto-generated method stub
    super.setupTableHeader();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 2;
    gbc.gridy = 0;
    JButton createButton2 = new JButton("OMG");
    createButton2.addActionListener((e) -> {
      Cliente c = new Cliente("Mozart", "090329032", "M", "9038120321", new Date(2004, 2, 3));
      repo.criar(c);
    });
    headerPanel.add(createButton2, gbc);
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
    ClienteFormCm form = new ClienteFormCm(repo, TableAction.UPDATE);
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
    ClienteFormCm form = new ClienteFormCm(repo, TableAction.CREATE);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }


}
