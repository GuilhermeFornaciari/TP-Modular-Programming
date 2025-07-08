package Programa.Visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Screens.Cliente.ClienteScreen;
import Programa.Visao.Screens.MovimentoCaixa.MovimentoCaixaScreen;
import Programa.Visao.Screens.Transacao.TransacaoScreen;

public class Sidebar extends JPanel {

  protected LinkedHashMap<String, JPanel> panels = new LinkedHashMap<>();
  protected ArrayList<SidebarButton> buttons = new ArrayList<>();
  public Consumer<JPanel> onMenuItemClick;

  public Sidebar(BancoDeDados db) {
    super();
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setBackground(Color.WHITE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0;

    panels.put("Clientes", new ClienteScreen(db));
    panels.put("Tipos de Transação", new TransacaoScreen(db));
    panels.put("Movimentos de Caixa", new MovimentoCaixaScreen(db));

    panels.forEach((key, value) -> {
      SidebarButton btn = new SidebarButton(key);
      btn.addActionListener((_) -> {
        onMenuItemClick.accept(value);
        handleActiveButton(btn);
      });
      add(btn, gbc);
      buttons.add(btn);
      gbc.gridy++;
    });

    buttons.getFirst().setActive(true);

    gbc.weighty = 1.0;
    add(Box.createGlue(), gbc);

  }

  private void handleActiveButton(SidebarButton btn) {
    for (SidebarButton sidebarButton: buttons) {
      sidebarButton.setActive(false);
    };
    btn.setActive(true);
  }

  public void setOnMenuItemClick(Consumer<JPanel> callback) {
    this.onMenuItemClick = callback;
  }

  private class SidebarButton extends JButton {

    private boolean active = false;

    public SidebarButton(String value) {
      super(value);
      // setContentAreaFilled(false);
      setFocusPainted(false);
      addFocusListener(focusListener);

      if (isFocusOwner()) {
        setBackground(new Color(0x193CB8));
      } else {
        setBackground(Color.WHITE);
      }
      setBorder(BorderFactory.createLineBorder(getBackground(), 10));
    }

    private FocusListener focusListener = new FocusListener() {

      public void focusGained(FocusEvent e) {

      }

      public void focusLost(FocusEvent e) {
      }
    };

    public void setActive(boolean active) {
      this.active = active;
      handleAppearance();
    }

    private void handleAppearance() {
      if (active) {
        setBackground(new Color(0x193CB8));
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(getBackground(), 10));
      } else {
        setBackground(Color.WHITE);
        setForeground(Color.DARK_GRAY);
        setBorder(BorderFactory.createLineBorder(getBackground(), 10));
      }
    }

  };

}
