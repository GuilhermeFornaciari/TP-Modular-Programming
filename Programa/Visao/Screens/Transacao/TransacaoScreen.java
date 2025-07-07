package Programa.Visao.Screens.Transacao;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Programa.Persistencia.BancoDeDados;

public class TransacaoScreen extends JPanel {
  BancoDeDados db;

  public TransacaoScreen(BancoDeDados db) {
    this.db = db;

    this.setLayout(new BorderLayout());
    JPanel transacaoList = new TransacaoList(db.tipoTransacao);
    add(transacaoList, BorderLayout.CENTER);
  }
 
}