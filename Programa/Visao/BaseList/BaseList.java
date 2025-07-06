package Programa.Visao.BaseList;

import Programa.Modelo.Cliente;
import Programa.Modelo.Entidade;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.ObservableAction;
import Programa.Visao.Subscriber;
import Programa.Visao.TableConfig;
import Programa.Visao.BaseList.TableActionButton.TableAction;
import Programa.Visao.Cliente.ClienteFormCm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public abstract class BaseList<T extends Entidade> extends JPanel implements ActionListener, Subscriber<T> {

  DefaultTableModel tableModel = new NonEditableTableModel();
  JTable table = new JTable();
  JScrollPane scrollPane = new JScrollPane();
  Map<Integer, JPanel> actionPanelsMap = new HashMap<>();

  public TableConfig tableConfig;

  ArrayList<T> tableData;
  Map<Integer, T> dataMap = new HashMap<>();

  // Table Header
  public JPanel headerPanel;
  public String headerTitle;

  // Repo
  public IRepositorioGeral<T> repo;

  public BaseList(IRepositorioGeral<T> repo) {
    super();
    this.repo = repo;
    this.headerTitle = "Tabela";

    this.setLayout(new BorderLayout());

    setupTableHeader();
    setupTableBody();

    this.add(headerPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  public void setupTableHeader() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    headerPanel = new JPanel();

    gbc.weightx = 0;
    gbc.weighty = 0;

    gbc.gridx = 0;
    gbc.gridy = 0;
    headerPanel.add(new JLabel(headerTitle), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    JButton createButton = new JButton("+");
    createButton.addActionListener((e) -> {
      onCreateClick();
    });
    headerPanel.add(createButton, gbc);

    gbc.gridx = 3;
    gbc.gridy = 0;
    JButton deleteLastRow = new JButton("-");
    deleteLastRow.addActionListener((e) -> {
      tableModel.removeRow(tableModel.getRowCount() - 1);
    });
    headerPanel.add(deleteLastRow, gbc);

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
    table.getColumnModel().getColumn(column).setCellRenderer(new PanelActionRenderer());
    table.getColumnModel().getColumn(column).setCellEditor(new PanelActionEditor());

    if (!actionPanelsMap.isEmpty()) {
      Integer randomKey = actionPanelsMap.keySet().toArray(new Integer[0])[0];
      int height = actionPanelsMap.get(randomKey).getPreferredSize().height;
      System.out.printf("Height %d\n", height);
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
    table.getColumnModel().getColumn(column).setCellRenderer(new PanelActionRenderer());
    table.getColumnModel().getColumn(column).setCellEditor(new PanelActionEditor());
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
    System.out.println("PopulateTableModel");
    actionPanelsMap.forEach((i, panel) -> {
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
      Vector<Object> rowData = new Vector(tableConfig.getColumnConfigs().size() + 1);

      tableConfig.getColumnConfigs().forEach((config) -> {
        String propertyKey = config.getColumnName();
        rowData.add(item.getProperty(propertyKey).toString());
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
    JPanel panel = new TableActionPanel();
    panel.setOpaque(true);
    TableActionButton editButton = new TableActionButton("U", itemId,
        TableAction.UPDATE);
    editButton.addActionListener(this);
    panel.add(editButton);
    TableActionButton deleteButton = new TableActionButton("D", itemId,
        TableAction.DELETE);
    deleteButton.addActionListener(this);
    panel.add(deleteButton);
    return panel;
  }

  public void populateTableModel(ArrayList<T> data) {
    data.forEach((item) -> {
      Vector<String> rowData = new Vector(tableConfig.getColumnConfigs().size());
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
