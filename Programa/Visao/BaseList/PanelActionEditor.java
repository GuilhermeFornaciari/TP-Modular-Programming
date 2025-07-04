package Programa.Visao.BaseList;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class PanelActionEditor extends AbstractCellEditor implements TableCellEditor {

  private JPanel panel;

  // public PanelActionEditor() {
  // panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
  // panel.setOpaque(true);
  // button = new JButton();
  // button.setMargin(new Insets(2, 5, 2, 5));
  // button.addActionListener(e -> fireEditingStopped());
  // panel.add(button);
  // }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    this.panel = (JPanel) value;
    return panel;
  }

  @Override
  public Object getCellEditorValue() {
    return panel;
  }

}
