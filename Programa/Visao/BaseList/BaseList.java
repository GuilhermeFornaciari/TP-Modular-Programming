package Programa.Visao.BaseList;

import Programa.Modelo.Entidade;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.TableConfig;
import Programa.Visao.BaseList.TableActionButton.TableAction;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

public abstract class BaseList<T extends Entidade> extends JPanel implements ActionListener {

  DefaultTableModel tableModel = new NonEditableTableModel();
  JTable table = new JTable();
  JScrollPane scrollPane = new JScrollPane();

  public TableConfig tableConfig;

  ArrayList<T> tableData;

  public IRepositorioGeral<T> repo;

  public BaseList(IRepositorioGeral<T> repo) {
    super();
    this.repo = repo;

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    this.setLayout(new GridBagLayout());
    this.setupTableConfig();
    this.setupTableModel();
    this.getTableData();
    this.populateTableModel();
    this.setupTable();
    this.setupScrollPane();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;

    this.add(scrollPane, gbc);
  }

  public void setupTableModel() {
    tableConfig.getColumnConfigs().forEach((config) -> {
      tableModel.addColumn(config.getColumnLabel());
    });
    tableModel.addColumn("Ações");
  }

  public void setupTable() {
    table = new JTable(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setFillsViewportHeight(true);

    int column = tableConfig.getColumnConfigs().size();
    table.getColumnModel().getColumn(column).setCellRenderer(new PanelActionRenderer());
    table.getColumnModel().getColumn(column).setCellEditor(new PanelActionEditor());

    table.setRowHeight(table.getRowHeight() + 20);

    // Resize event setup
    table.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        resizeTableColumns(table);
      }
    });
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
    tableData.forEach((item) -> {
      Vector<Object> rowData = new Vector(tableConfig.getColumnConfigs().size()+1);
      tableConfig.getColumnConfigs().forEach((config) -> {
        rowData.add(item.getProperty(config.getColumnName()).toString());
      });

      //Buttons
      JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
      panel.setOpaque(true);

      TableActionButton editButton = new TableActionButton("U", tableModel.getColumnCount()-1, item.getId(), TableAction.UPDATE);
      editButton.addActionListener(this);
      panel.add(editButton);

      TableActionButton deleteButton = new TableActionButton("D", tableModel.getColumnCount()-1, item.getId(), TableAction.DELETE);
      deleteButton.addActionListener(this);
      panel.add(deleteButton);
      
      
      panel.setVisible(true);
      rowData.addLast(panel);
      tableModel.addRow(rowData);
    });
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
      if (column == getColumnCount()-1) return true;
      return false;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    TableActionButton button = (TableActionButton) e.getSource();
    if (button.getButtonAction() == TableAction.UPDATE) {
      System.out.println("Edit");
      System.out.println(button.getItemId());
    }
    if (button.getButtonAction() == TableAction.DELETE) {
      System.out.println("Delete");
      System.out.println(button.getItemId());
    }
  }

}
