package Programa.Visao.List;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {

  private JPanel panel;

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
