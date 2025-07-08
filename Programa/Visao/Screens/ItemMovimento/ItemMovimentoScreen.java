package Programa.Visao.Screens.ItemMovimento;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;

public class ItemMovimentoScreen extends JPanel {

  public ItemMovimentoScreen(IRepositorioGeral<MovimentoCaixa> repo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo, Integer idMovimentoCaixa) {
    this.setLayout(new BorderLayout());
    JPanel itemMovimentoList = new ItemMovimentoList(repo, tipoTransacaoRepo, idMovimentoCaixa);
    add(itemMovimentoList, BorderLayout.CENTER);
  }

}
