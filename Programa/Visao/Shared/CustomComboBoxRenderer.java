package Programa.Visao.Shared;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class CustomComboBoxRenderer extends BasicComboBoxRenderer {

  @Override
  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
      boolean cellHasFocus) {
    Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    if (value != null) {
      if (value instanceof ComboBoxItem cbItem) {
        setText(cbItem.label);
        return this;
      }
    }
    return comp;
  }

}
