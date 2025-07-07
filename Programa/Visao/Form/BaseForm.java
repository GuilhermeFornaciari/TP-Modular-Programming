package Programa.Visao.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Programa.Modelo.Entidade;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Observable.Subscriber;
import Programa.Visao.Shared.ComboBoxItem;
import Programa.Visao.Shared.CustomComboBoxRenderer;

import java.awt.event.ActionEvent;

public abstract class BaseForm<T extends Entidade> extends JFrame implements ActionListener, Subscriber<T> {

  private final Map<String, JComponent> fields = new HashMap<>();
  protected JButton submitButton;
  public IRepositorioGeral<T> repo;
  protected JPanel mainPanel;
  protected TableAction action;
  protected Integer rows, cols;
  // private final FormDataHandler<T> dataHandler;

  public BaseForm(IRepositorioGeral<T> repo, TableAction action) {
    super();
    this.repo = repo;
    this.action = action;

    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    mainPanel = new JPanel(new GridBagLayout()) {
        @Override
        public Dimension getPreferredSize() {
            Dimension preferred = super.getPreferredSize();
            return new Dimension(500, preferred.height);
        }
    };

    contentPane.add(mainPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    submitButton = new JButton("Salvar");
    submitButton.addActionListener(e -> onSubmit(e));

    buttonPanel.add(submitButton);
    
    contentPane.add(buttonPanel, BorderLayout.SOUTH);
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

  public void addDropdownField(String fieldName, String label, ArrayList<ComboBoxItem> options) {
    if (options.size() <= 0) return;
    JComboBox<Object> field = new JComboBox<Object>();
    field.setRenderer(new CustomComboBoxRenderer());
    for (Integer i=0; i<options.size(); i++) {
      field.addItem(options.get(i));
    }
    addFormField(fieldName, label, field);
  }

  public void addDropdownField(String fieldName, String label, Object[] options) {
    if (options.length <= 0) return;
    JComboBox<Object> field = new JComboBox<Object>();
    field.setRenderer(new CustomComboBoxRenderer());
    for (Integer i=0; i<options.length; i++) {
      field.addItem(options[i]);
    }
    addFormField(fieldName, label, field);
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
    if (fieldName != "id") mainPanel.add(new JLabel(label + ":"), gbc);

    gbc.gridx = 1;
    if (fieldName != "id") mainPanel.add(field, gbc);
    cols = gbc.gridx+1;
    rows = gbc.gridy+1;

    updateFrameSize();
  }

  public void updateFrameSize() {
    mainPanel.revalidate();

    Dimension preferred = mainPanel.getPreferredSize();

    Insets insets = getInsets();
    int totalHeight = preferred.height + insets.top + insets.bottom;

    setSize(500 + insets.left + insets.right, totalHeight + insets.top + insets.bottom + 60);

    setLocationRelativeTo(null);
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
      } else if (component instanceof JComboBox comboBox) {
        Object selectedItem = comboBox.getSelectedItem();
        if (selectedItem instanceof ComboBoxItem cbItem) {
          String value = cbItem.getValue();
          values.put(key, value);
        } else {
          values.put(key, selectedItem.toString());
        }
      }
    }
    return values;
  }

  public void clearFieldsValue() {
    for (Map.Entry<String, JComponent> field : fields.entrySet()) {      JComponent component = field.getValue();
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
      if (component instanceof JFormattedTextField jFormattedTextField) {
        Object temp = data.getProperty(key);
        if (temp instanceof Date tempDate) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          String formatted = dateFormat.format(tempDate);
          jFormattedTextField.setValue(formatted);
        } else
          jFormattedTextField.setText(data.getProperty(key).toString());
      } else if (component instanceof JTextField jTextField) {
        try {
          if (!key.equals("id"))
            jTextField.setText(data.getProperty(key).toString());
          else
            jTextField.setText(data.getId().toString());
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

  public void onSubmit(ActionEvent event) {
    if (event.getSource() == submitButton) {
      try {
        if (action == TableAction.CREATE)
          onCreate(getFieldsValue());
        else if (action == TableAction.UPDATE)
          onUpdate(getFieldsValue());
        setVisible(false);
        dispose();
      } catch (BuilderValidationException e) {
        handleInvalidForm(e.getExceptionMap());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void onCreate(Map<String, ?> data) throws BuilderValidationException {

  }

  public void onUpdate(Map<String, ? extends Object> data) throws BuilderValidationException {

  }

  public void handleInvalidForm(Map<String, ArrayList<String>> exceptionMap) {
    exceptionMap.forEach((key, value) -> {
      SwingUtilities.invokeLater(() -> {
        String tooltipText = String.join(" ", value);
        fields.get(key).setToolTipText(tooltipText);
        fields.get(key).setBorder(new LineBorder(Color.RED, 2));
      });
    });
  }

}
