package Programa.Visao.List;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ActionCellRenderer implements TableCellRenderer {
  public ActionCellRenderer() {
    super();
  }

  public Component getTableCellRendererComponent(
      JTable table, Object value,
      boolean isSelected, boolean hasFocus,
      int row, int column) {
    if (value instanceof JPanel) {
      JPanel panel = (JPanel) value;
      panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
      return panel;
    }
    return new JLabel("NO LABEL");
  }

}
