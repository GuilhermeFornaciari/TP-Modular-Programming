package Programa.Visao.BaseList;

import javax.swing.Action;
import javax.swing.JButton;

public class TableActionButton extends JButton {
  
  int itemId;
  TableAction buttonAction;

  public TableActionButton(String text, int itemId, TableAction action) {
    super(text);
    this.itemId = itemId;
    this.buttonAction = action;
  }

  public int getItemId() {
    return itemId;
  }
  public TableAction getButtonAction() {
    return buttonAction;
  }

  public enum TableAction {CREATE, UPDATE, DELETE};


}
