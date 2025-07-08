package Programa.Visao.List;

import Programa.Modelo.Cliente;
import Programa.Modelo.Entidade;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Observable.ObservableAction;
import Programa.Visao.Observable.Subscriber;
import Programa.Visao.Shared.RoundedButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public abstract class BaseList<T extends Entidade> extends JPanel implements ActionListener, Subscriber<T> {

  DefaultTableModel tableModel = new NonEditableTableModel();
  protected JTable table = new JTable();
  protected JScrollPane scrollPane = new JScrollPane();
  Map<Integer, JPanel> actionPanelsMap = new HashMap<>();

  public TableConfig tableConfig;

  protected ArrayList<T> tableData;
  Map<Integer, T> dataMap = new HashMap<>();

  // Table Header
  protected JPanel headerPanel;
  protected String headerTitle;

  //Search
  protected JTextField searchTextField = new JTextField();

  // Repo
  public IRepositorioGeral<T> repo;

  public BaseList(IRepositorioGeral<T> repo, String headerTitle) {
    super();
    this.repo = repo;
    this.headerTitle = headerTitle;

    this.setLayout(new BorderLayout());

    setupTableHeader();
    setupTableBody();

    this.add(headerPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  public BaseList(String headerTitle) {
    super();
    this.headerTitle = headerTitle;

    this.setLayout(new BorderLayout());

    setupTableHeader();
  }

  public void setupTableHeader() {
    headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(0, 0, 5, 0),
        BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2,2,2,2, Color.GRAY),
            BorderFactory.createEmptyBorder(3, 3, 3, 3))));

    JLabel headerLabel = new JLabel(headerTitle);
    headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    headerPanel.add(headerLabel, BorderLayout.WEST);

    searchTextField.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createEmptyBorder(0, 10, 0, 10),
      BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY)
    ));
    // searchTextField.getDocument().addDocumentListener(new DocumentListener() {
    //   public void changedUpdate(DocumentEvent e) {
    //     warn();
    //   }

    // });
    // headerPanel.add(searchTextField, BorderLayout.CENTER);

    RoundedButton createButton = new RoundedButton("+", 10, 30, 30, new Color(0x193CB8), new Color(0x193CB8).darker(),
        Color.WHITE, Color.WHITE);
    createButton.addActionListener((_) -> {
      onCreateClick();
    });
    headerPanel.add(createButton, BorderLayout.EAST);
  }

  public void setupTableBody() {
    this.setupTableConfig();
    this.setupTableModel();
    this.getTableData();
    this.populateTableModel();
    this.setupTable();
    this.setupScrollPane();
  }

  public void setupTableModel() {
    tableConfig.getColumnConfigs().forEach((config) -> {
      tableModel.addColumn(config.getColumnLabel());
    });
    tableModel.addColumn("Ações");
  }

  public void setupTable() {
    table.setModel(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setFillsViewportHeight(true);

    int column = tableConfig.getColumnConfigs().size();
    table.getColumnModel().getColumn(column).setCellRenderer(new ActionCellRenderer());
    table.getColumnModel().getColumn(column).setCellEditor(new ActionCellEditor());

    if (!actionPanelsMap.isEmpty()) {
      Integer randomKey = actionPanelsMap.keySet().toArray(new Integer[0])[0];
      int height = actionPanelsMap.get(randomKey).getPreferredSize().height;
      table.setRowHeight(height);
    }

    table.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        resizeTableColumns(table);
      }
    });
  }

  public void updateTable() {
    table.setModel(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setFillsViewportHeight(true);

    int column = tableConfig.getColumnConfigs().size();
    table.getColumnModel().getColumn(column).setCellRenderer(new ActionCellRenderer());
    table.getColumnModel().getColumn(column).setCellEditor(new ActionCellEditor());
  }

  public void reloadTableData() {
    tableModel.fireTableDataChanged();
    if (table.getAutoResizeMode() != JTable.AUTO_RESIZE_OFF) {
      table.doLayout();
    }
  }

  private void setupScrollPane() {
    scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setViewportBorder(null);
    scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
    scrollPane.setBackground(Color.CYAN);
  }

  public void setupTableConfig() {

  }

  public void populateTableModel() {
    actionPanelsMap.forEach((_, panel) -> {
      Component[] components = panel.getComponents();
      for (Component c : components) {
        if (c instanceof TableActionButton) {
          ((TableActionButton) c).removeActionListener(this);
        }
      }
      panel.removeAll();
    });
    actionPanelsMap.clear();
    tableModel = new NonEditableTableModel();
    setupTableModel();
    tableData.forEach((item) -> {
      Vector<Object> rowData = new Vector<Object>(tableConfig.getColumnConfigs().size() + 1);

      tableConfig.getColumnConfigs().forEach((config) -> {
        String propertyKey = config.getColumnName();
        Object propertyValue = item.getProperty(propertyKey);
        if (propertyValue instanceof Cliente cliente) {
          rowData.add(cliente.getNome());
        } else if (propertyValue instanceof TipoTransacao tipoTransacao) {
          rowData.add(tipoTransacao.getDescricao());
        } else if (propertyValue instanceof Float floatVal) {
          DecimalFormat decimalFormat = new DecimalFormat("0.00");
          rowData.add(decimalFormat.format(floatVal));
        } else {
          rowData.add(propertyValue.toString());
        }
      });
      try {
        JPanel panel = createActionPanel(item.getId());
        rowData.add(panel);
        actionPanelsMap.put(item.getId(), panel);
        tableModel.addRow(rowData);
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
  }

  private JPanel createActionPanel(int itemId) {

    ImageIcon pencilIcon = null;
    ImageIcon trashIcon = null;

    try {
      pencilIcon = new ImageIcon(getClass().getResource("/resources/assets/pencil-icon.png"));
      trashIcon = new ImageIcon(getClass().getResource("/resources/assets/recycle-bin-icon.png"));
      Image scaledPencil = pencilIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
      Image scaledTrash = trashIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
      pencilIcon = new ImageIcon(scaledPencil);
      trashIcon = new ImageIcon(scaledTrash);
    } catch (Exception e) {
      e.printStackTrace();
    }

    JPanel panel = new TableActionPanel();
    panel.setOpaque(true);

    TableActionButton editButton = null;
    TableActionButton deleteButton = null;
    
    if (pencilIcon != null)editButton = new TableActionButton(pencilIcon, itemId, TableAction.UPDATE);
    else editButton = new TableActionButton("U", itemId, TableAction.UPDATE);
    editButton.addActionListener(this);
    panel.add(editButton);
    
    if(trashIcon != null) deleteButton = new TableActionButton(trashIcon, itemId, TableAction.DELETE);
    else deleteButton = new TableActionButton("D", itemId, TableAction.DELETE);
    deleteButton.addActionListener(this);
    panel.add(deleteButton);
    
    return panel;
  }

  public void populateTableModel(ArrayList<T> data) {
    data.forEach((item) -> {
      Vector<String> rowData = new Vector<String>(tableConfig.getColumnConfigs().size());
      tableConfig.getColumnConfigs().forEach((config) -> {
        rowData.add(item.getProperty(config.getColumnName()).toString());
      });
      tableModel.addRow(rowData);
    });
  }

  private void resizeTableColumns(JTable table) {

    int width = table.getParent().getWidth();
    int columnCount = table.getColumnCount();

    if (columnCount <= 0)
      return;
    int columnWidth = width / columnCount;
    for (int i = 0; i < columnCount; i++) {
      table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
    }
  }

  public void getTableData() {
    tableData = repo.pegar_todos();
  }

  public void search(String query, String searchField) {
    ArrayList<T> filtered = new ArrayList<>();
    for (T item: tableData) {
      Object value = null;
      try {
        value = item.getProperty(searchField);
      } catch (Exception e) { 
        e.printStackTrace();
      }

      if (value != null && value.toString().toLowerCase().contains(query.toLowerCase())) {
        filtered.add(item);
      } 
    }
    tableData = filtered;
  }  

  public class NonEditableTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
      if (column == getColumnCount() - 1)
        return true;
      return false;
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    TableActionButton button = (TableActionButton) e.getSource();
    if (button.getButtonAction() == TableAction.UPDATE) {
      this.onUpdateClick(button);
    }
    if (button.getButtonAction() == TableAction.DELETE) {
      this.onDeleteClick(button);
    }
  }

  public void onUpdateClick(TableActionButton button) {

  }

  public void onDeleteClick(TableActionButton button) {
    try {
      repo.deletar(repo.pegar_um(button.getItemId()));
      getTableData();
      populateTableModel();
      updateTable();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onCreateClick() {
  }

  @Override
  public void onNotify(ObservableAction action) {
    System.out.println("ON NOTIFY");
    if (action == ObservableAction.DELETE)
      return;
    getTableData();
    try {
      populateTableModel();
      updateTable();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
