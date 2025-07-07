package Programa.Visao.List;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class TableActionPanel extends JPanel {

  TableActionPanel() {
    super(new FlowLayout(FlowLayout.CENTER, 0, 0));
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension size = super.getPreferredSize();

    System.out.printf("Custom panel %d\n", getComponentCount());
    if (getComponentCount() > 0) {
      Integer buttonHeight = getComponent(0).getPreferredSize().height;
      int preferredHeight = buttonHeight;
      return new Dimension(size.width, preferredHeight);
    }

    return size;
  }

}
