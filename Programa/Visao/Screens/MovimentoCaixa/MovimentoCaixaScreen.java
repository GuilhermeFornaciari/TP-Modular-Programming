package Programa.Visao.Screens.MovimentoCaixa;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Programa.Persistencia.BancoDeDados;

public class MovimentoCaixaScreen extends JPanel {
  BancoDeDados db;

  public MovimentoCaixaScreen(BancoDeDados db) {
    this.db = db;
    this.setLayout(new BorderLayout());
    JPanel movimentoCaixaList = new MovimentoCaixaList(db.movimentoCaixa, db.cliente);
    add(movimentoCaixaList, BorderLayout.CENTER);
  }
}
