package Programa.Visao.Screens.ItemMovimento;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Screens.MovimentoCaixa.MovimentoCaixaList;

public class ItemMovimentoScreen extends JPanel {
  BancoDeDados db;

  public ItemMovimentoScreen(BancoDeDados db) {
    this.db = db;
    this.setLayout(new BorderLayout());
    JPanel itemMovimentoList = new ItemMovimentoList(db.movimentoCaixa, db.cliente);
    add(itemMovimentoList, BorderLayout.CENTER);
  }
}
