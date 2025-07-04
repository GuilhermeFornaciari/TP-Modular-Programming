package Programa.Visao.BaseList;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PanelActionRenderer implements TableCellRenderer {
  public PanelActionRenderer() {
    super();
  }

  public Component getTableCellRendererComponent(
      JTable table, Object value,
      boolean isSelected, boolean hasFocus,
      int row, int column) {
      return (JPanel) value;
  }
}
