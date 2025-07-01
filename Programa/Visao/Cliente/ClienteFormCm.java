package Programa.Visao.Cliente;

import java.awt.event.ActionEvent;
import java.util.Map;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.BaseForm;

public class ClienteFormCm extends BaseForm<Cliente> {

  public ClienteFormCm(IRepositorioGeral<Cliente> repo) {
    super(repo);
    this.setupFields();
  }

  public void setupFields() {
    addTextField("id", "Id");
    addTextField("Nome", "Nome");
    addFormattedTextField("CPF", "CPF", "###.###.###-##");
    addTextField("Email", "Email");
    addFormattedTextField("CEP", "CEP", "#####-###");
    addFormattedTextField("DataNascimento", "Data de Nascimento", "##/##/####");
  }

  public void actionPerformed(ActionEvent e) {
    Map<String, String> value = getFieldsValue();
    value.forEach((key, val) -> {
      System.out.println(key + " = " + val);
  });
  }
}
