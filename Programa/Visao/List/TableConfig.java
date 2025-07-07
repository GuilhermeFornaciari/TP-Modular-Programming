package Programa.Visao.List;

import java.util.ArrayList;

public class TableConfig {

  private ArrayList<TableColumnConfig> columnConfigs;

  public TableConfig(ArrayList<TableColumnConfig> columnConfigs) {
    this.columnConfigs = columnConfigs;
  }
  public TableConfig() {
    this.columnConfigs = new ArrayList<TableColumnConfig>();
  }

  public void addColumnConfig(TableColumnConfig columnConfig) {
    this.columnConfigs.add(columnConfig);
  }

  public void setColumnConfigs(ArrayList<TableColumnConfig> columnConfigs) {
    this.columnConfigs = columnConfigs;
  }
  public ArrayList<TableColumnConfig> getColumnConfigs() {
    return this.columnConfigs;
  }

}
