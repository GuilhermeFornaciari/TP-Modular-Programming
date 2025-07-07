package Programa.Visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Screens.Cliente.ClienteScreen;
import Programa.Visao.Screens.MovimentoCaixa.MovimentoCaixaScreen;
import Programa.Visao.Screens.Transacao.TransacaoScreen;

public class Sidebar extends JPanel {

  LinkedHashMap<String, JPanel> panels = new LinkedHashMap<>();
  public Consumer<JPanel> onMenuItemClick;

  public Sidebar(BancoDeDados db) {
    super();
    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;

    panels.put("Clientes", new ClienteScreen(db));
    panels.put("Transações", new TransacaoScreen(db));
    panels.put("Movimentos de Caixa", new MovimentoCaixaScreen(db));

    panels.forEach((key, value) -> {
      JButton btn = new JButton(key);
      btn.addActionListener((_) -> {
        onMenuItemClick.accept(value);
      });
      add(btn, gbc);
      gbc.gridy++;
    });

  }

  public void setOnMenuItemClick(Consumer<JPanel> callback) {
    this.onMenuItemClick = callback;
  }

}
