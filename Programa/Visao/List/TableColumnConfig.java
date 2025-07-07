package Programa.Visao.List;

public class TableColumnConfig {

  private final String columnName;
  private final String columnLabel;
  private final double width;

  public TableColumnConfig(String columnName, String columnLabel, double width) {
    this.width = width;
    this.columnName = columnName;
    this.columnLabel = columnLabel;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getColumnLabel() {
    return columnLabel;
  }

  public double getWidth() {
    return width;
  }

}
