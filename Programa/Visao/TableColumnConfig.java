package Programa.Visao;

public class TableColumnConfig {

  private final String columnName;
  private final double width;

  public TableColumnConfig(String columnName, double width) {
    this.width = width;
    this.columnName = columnName;
  }

  public String getColumnName() {
    return columnName;
  }

  public double getWidth() {
    return width;
  }

}
