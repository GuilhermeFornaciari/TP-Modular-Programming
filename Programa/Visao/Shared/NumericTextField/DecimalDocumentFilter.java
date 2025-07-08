package Programa.Visao.Shared.NumericTextField;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class DecimalDocumentFilter extends DocumentFilter {

  protected boolean isValidInput(Document doc, int offset, String text) throws BadLocationException {
    if (text.isEmpty()) return true;
    
    String textBefore = doc.getText(0, doc.getLength());
    String textAfter = textBefore.substring(0, offset) + text + textBefore.substring(offset);

    return textAfter.matches("\\d*(\\.\\d?\\d?)?");
  }

  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
    if (isValidInput(fb.getDocument(), offset, string)) {
      super.insertString(fb, offset, string, attr);
      return;
    }
    Toolkit.getDefaultToolkit().beep();
  }

  @Override
  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
      throws BadLocationException {
    if (isValidInput(fb.getDocument(), offset, text)) {
      super.replace(fb, offset, length, text, attrs);
      return;
    }
    Toolkit.getDefaultToolkit().beep();
  }
  
}
