package Programa.Visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Programa.Modelo.Entidade;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Persistencia.RepositorioGeral;
import java.awt.event.ActionEvent;

public abstract class BaseForm<T extends Entidade> extends JPanel implements ActionListener, Subscriber<T> {

  private final Map<String, JComponent> fields = new LinkedHashMap<>();
  private JButton submitButton;
  private IRepositorioGeral<T> repo;
  // private final FormDataHandler<T> dataHandler;

  public BaseForm(IRepositorioGeral<T> repo) {
    super();
    this.repo = repo;
    setLayout(new GridBagLayout());

    submitButton = new JButton("Salvar");
    submitButton.addActionListener(this);
  }

  public void addTextField(String fieldName, String label) {
    addFormField(fieldName, label, new JTextField());
  }

  public void addFormattedTextField(String fieldName, String label, String mask) {
    try {
      MaskFormatter maskFormatter = new MaskFormatter(mask);
      maskFormatter.setPlaceholder("_");
      addFormField(fieldName, label, new JFormattedTextField(maskFormatter));
    } catch (Exception e) {
      // Handle this error later
    }
  }

  public void addFormField(String fieldName, String label, JComponent field, GridPosition gridPosition) {
    fields.put(fieldName, field);
  }

  public void addFormField(String fieldName, String label, JComponent field) {
    fields.put(fieldName, field);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.gridwidth = 1;

    gbc.gridx = 0;
    gbc.gridy = fields.size() - 1;
    add(new JLabel(label + ":"), gbc);

    gbc.gridx = 1;
    add(field, gbc);

    // GAMBI PRO BOTÃO DE SUBMIT (ARRUMAR DPS)
    remove(submitButton);
    gbc.gridx = 1;
    gbc.gridy = fields.size();
    gbc.anchor = GridBagConstraints.EAST;
    add(submitButton, gbc);
  }

  public Map<String, String> getFieldsValue() {
    Map<String, String> values = new HashMap<>();
    for (Map.Entry<String, JComponent> field : fields.entrySet()) {
      String key = field.getKey();
      JComponent component = field.getValue();
      if (component instanceof JTextField)
        values.put(key, ((JTextField) component).getText());
      else if (component instanceof JFormattedTextField) {
        Object value = ((JFormattedTextField) field).getValue();
        values.put(key, value != null ? value.toString() : "");
      }
    }
    return values;
  }

  public void clearFieldsValue() {
    for (Map.Entry<String, JComponent> field : fields.entrySet()) {
      String key = field.getKey();
      JComponent component = field.getValue();
      if (component instanceof JTextField jTextField)
        jTextField.setText("");
      if (component instanceof JFormattedTextField jFormattedTextField)
        jFormattedTextField.setValue(null);
    }
  }

  public void populateForm(T data) {
    for (Map.Entry<String, JComponent> field : fields.entrySet()) {
      String key = field.getKey();
      JComponent component = field.getValue();
      if (component instanceof JTextField jTextField) {
        try {
          jTextField.setText(data.getProperty(key).toString());
        } catch (Exception e) {
          // It lacks error handling here
        }
      }
    }
  }

  public class GridPosition {
    Integer x, y;

    public void gridPosition(Integer x, Integer y) {
      this.x = x;
      this.y = y;
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
    }
  }

}
