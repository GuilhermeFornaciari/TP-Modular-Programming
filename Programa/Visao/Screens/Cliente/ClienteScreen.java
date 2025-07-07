package Programa.Visao.Screens.Cliente;

import Programa.Persistencia.BancoDeDados;

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
