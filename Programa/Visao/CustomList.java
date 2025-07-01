package Programa.Visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

public class CustomList extends JPanel {

  TableConfig tableConfig;

  DefaultTableModel tableModel = new DefaultTableModel();
  JTable table = new JTable();
  JScrollPane scrollPane = new JScrollPane();

  public CustomList(TableConfig tableConfig) {
    super();
    setLayout(new GridBagLayout());
    this.tableConfig = tableConfig;

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    setupTableModel();
    setupTable();
    setupScrollPane();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    this.add(scrollPane, gbc);
  }

  void setupTableModel() {
    for (TableColumnConfig cc : tableConfig.getColumnConfigs()) {
      tableModel.addColumn(cc.getColumnName());
    }
  }

  void setupTable() {
    table = new JTable(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setFillsViewportHeight(true);
    // Resize event setup
    table.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        resizeTableColumns(table);
      }
    });
  }

  void setupScrollPane() {
    scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setViewportBorder(null);
    scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
    scrollPane.setBackground(Color.CYAN);
  }

  public CustomList() {
    super();
    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    String[] columns = { "Nome", "CPF", "Email", "CEP", "Nascimento" };

    DefaultTableModel table_model = new DefaultTableModel();
    for (String column : columns) {
      table_model.addColumn(column);
    }
    for (Integer i = 0; i < 100; i++) {
      table_model.addRow(new Object[] { "marcos", "12", "dsa", "123" });
    }

    JTable table = new JTable(table_model);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    Integer totalWidth = table.getWidth();
    // if (totalWidth <= 0) {
    // totalWidth = table.getParent().getWidth();
    // }

    Integer ratio = totalWidth / columns.length;
    System.out.println(ratio.toString());

    for (Integer i = 0; i < columns.length; i++) {
      table.getColumnModel().getColumn(i).setPreferredWidth(ratio);
    }

    table.setFillsViewportHeight(true);

    JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setViewportBorder(null);
    scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
    scrollPane.setBackground(Color.CYAN);

    table.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        resizeTableColumns(table);
      }
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    this.add(scrollPane, gbc);
  }

  public void populateList() {
    tableModel.addRow(new Object[] { "marcos", "12", "dsa", "123" });
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

}
