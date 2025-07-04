package Programa.Visao.BaseList;

import javax.swing.Action;
import javax.swing.JButton;

public class TableActionButton extends JButton {
  
  int row;
  int itemId;
  TableAction buttonAction;

  public TableActionButton(String text, int row, int itemId, TableAction action) {
    super(text);
    this.row = row;
    this.itemId = itemId;
    this.buttonAction = action;
  }

  public int getRow() {
    return row;
  }
  public int getItemId() {
    return itemId;
  }
  public TableAction getButtonAction() {
    return buttonAction;
  }

  public enum TableAction {CREATE, UPDATE, DELETE};


}
