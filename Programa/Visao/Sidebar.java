package Programa.Visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Sidebar extends JPanel {

  Sidebar() {
    super();
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    String[] menu_list = {"Cliente", "Despesa"};
    
    // for (Integer i = 0; i< menu_list.length; i++) {
    //   gbc.weightx = 1;
    //   gbc.weighty = 1.0/menu_list.length;
    //   gbc.gridx = 0;
    //   gbc.gridy = i;
    //   JButton btn = new JButton(menu_list[i]);
    //   this.add(btn, gbc);
    // }

  }

}
