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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Programa.Modelo.Entidade;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Observable.ObservableAction;
import Programa.Visao.Observable.Publisher;
import Programa.Visao.Observable.Subscriber;
import Programa.Visao.Shared.ComboBoxItem;
import Programa.Visao.Shared.CustomComboBoxRenderer;
import Programa.Visao.Shared.NumericTextField.NumericTextField;

import java.awt.event.ActionEvent;

public abstract class BaseForm<T extends Entidade> extends JFrame implements ActionListener, Subscriber<T>, Publisher<T> {

  private final Map<String, JComponent> fields = new HashMap<>();
  protected JButton submitButton;
  public IRepositorioGeral<T> repo;
  protected JPanel fieldsPanel;
  protected TableAction action;
  protected Integer rows, cols, currentRow = 0;
  // private final FormDataHandler<T> dataHandler;

  public BaseForm(IRepositorioGeral<T> repo, TableAction action) {
    super();
    this.repo = repo;
    this.action = action;

    //Content panel is the JFRAME panel
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    JPanel wrapperPanel = new JPanel(new BorderLayout());
    wrapperPanel.setBackground(Color.WHITE);
    wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.add(wrapperPanel, BorderLayout.CENTER);
    
    fieldsPanel = new JPanel(new GridBagLayout());
    fieldsPanel.setBackground(Color.WHITE);
    JScrollPane scrollPane = new JScrollPane(fieldsPanel);
    scrollPane.setBorder(null);
    
    //Button Panel is just the last row in the form, with the Submit button ("Salvar")
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.setBackground(Color.WHITE);
    submitButton = new JButton("Salvar");
    submitButton.addActionListener(e -> onSubmit(e));
    buttonPanel.add(submitButton);

    //Adding components to contentPanel
    wrapperPanel.add(scrollPane, BorderLayout.CENTER);
    wrapperPanel.add(buttonPanel, BorderLayout.SOUTH);
    setMinimumSize(new Dimension(600, getMinimumSize().height));
    updateFrameSize();
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
    if (options.size() <= 0)
      return;
    JComboBox<Object> field = new JComboBox<Object>();
    field.setRenderer(new CustomComboBoxRenderer());
    for (Integer i = 0; i < options.size(); i++) {
      field.addItem(options.get(i));
    }
    addFormField(fieldName, label, field);
  }

  public void addNumericTextField(String fieldName, String label) {
    JTextField field = new NumericTextField();
    addFormField(fieldName, label, field);
  }

  public void addDropdownField(String fieldName, String label, Object[] options) {
    if (options.length <= 0)
      return;
    JComboBox<Object> field = new JComboBox<Object>();
    field.setRenderer(new CustomComboBoxRenderer());
    for (Integer i = 0; i < options.length; i++) {
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
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.gridwidth = 1;
    gbc.gridx = 0;
    gbc.gridy = fields.size() - 1;
    if (fieldName != "id")
      fieldsPanel.add(new JLabel(label + ":"), gbc);

    gbc.gridx = 1;
    if (fieldName != "id") fieldsPanel.add(field, gbc);
    cols = gbc.gridx + 1;
    rows = gbc.gridy + 1;

    currentRow++;

    updateFrameSize();
  }

  public void addCustomPanel(JPanel panel, int gridwidth) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = currentRow;
    gbc.gridwidth = gridwidth;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(10, 5, 10, 5);

    fieldsPanel.add(panel, gbc);
    currentRow++;
  }

  public void updateFrameSize() {
    // fieldsPanel.revalidate();

    // Dimension preferred = fieldsPanel.getPreferredSize();

    // Insets insets = getInsets();
    // int totalHeight = preferred.height + insets.top + insets.bottom;

    // setSize(500 + insets.left + insets.right, totalHeight + insets.top + insets.bottom + 60);

    // setLocationRelativeTo(null);
    pack();
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
    for (Map.Entry<String, JComponent> field : fields.entrySet()) {
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
        notifySubscribers(action == TableAction.CREATE ? ObservableAction.CREATE : ObservableAction.UPDATE);
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
