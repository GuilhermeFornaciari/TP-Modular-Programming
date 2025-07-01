package Programa.Visao.Cliente;

import Programa.Modelo.Cliente;
import Programa.Persistencia.BancoDeDados;
import Programa.Visao.CustomList;
import Programa.Visao.ObservableAction;
import Programa.Visao.Subscriber;
import Programa.Visao.TableColumnConfig;
import Programa.Visao.TableConfig;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class ClienteScreen extends JPanel implements Subscriber<Cliente> {

  CustomList clienteList;
  ClienteForm clienteForm;
  ClienteFormCm ccForm;

  BancoDeDados db;

  public ClienteScreen(BancoDeDados db) {
    super();
    
    this.db = db;
  
    this.setLayout(new BorderLayout());
    TableConfig tableConfig = new TableConfig();
    tableConfig.addColumnConfig(new TableColumnConfig("Nome", 0.2));
    tableConfig.addColumnConfig(new TableColumnConfig("CPF", 0.2));
    tableConfig.addColumnConfig(new TableColumnConfig("Email", 0.2));
    tableConfig.addColumnConfig(new TableColumnConfig("CEP", 0.2));
    tableConfig.addColumnConfig(new TableColumnConfig("Nascimento", 0.2));
    
    clienteList = new CustomList(tableConfig);
    clienteForm = new ClienteForm();
    ccForm = new ClienteFormCm(db.cliente);
    
    add(ccForm, BorderLayout.NORTH);
    add(clienteList, BorderLayout.CENTER);
    
  }

  private void setupObservers() {
    this.clienteForm.registerObserver(this);
  }

  @Override
  public void onNotify(Cliente registry, ObservableAction action) {
    Subscriber.super.onNotify(registry, action);
    System.out.println("BATATAAA");
    clienteList.populateList();
  }

}
