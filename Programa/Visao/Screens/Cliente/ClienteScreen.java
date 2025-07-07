package Programa.Visao.Screens.Cliente;

import Programa.Modelo.Cliente;
import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Observable.ObservableAction;
import Programa.Visao.Observable.Subscriber;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class ClienteScreen extends JPanel {

  BancoDeDados db;

  public ClienteScreen(BancoDeDados db) {
    super();
    
    this.db = db;
  
    this.setLayout(new BorderLayout());
    ClienteList clienteListCm = new ClienteList(db.cliente);
    
    add(clienteListCm, BorderLayout.CENTER);
    
  }

}
