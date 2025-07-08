package Programa.Visao.Shared.NumericTextField;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class NumericTextField extends JTextField {
  public NumericTextField() {
    ((AbstractDocument)getDocument()).setDocumentFilter(new DecimalDocumentFilter());
  }
}