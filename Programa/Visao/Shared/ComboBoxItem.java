package Programa.Visao.Shared;

public class ComboBoxItem {
  
  String value;
  String label;

  public ComboBoxItem(String label, String value) {
    this.label = label;
    this.value = value;
  }

  public String getLabel() {
    return label;
  }
  public String getValue() {
    return value;
  }

}
