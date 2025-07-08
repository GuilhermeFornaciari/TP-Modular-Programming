package Programa.Visao.Screens.Cliente;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.Form.BaseForm;
import Programa.Visao.List.TableActionButton.TableAction;

public class ClienteForm extends BaseForm<Cliente> {

  public ClienteForm(IRepositorioGeral<Cliente> repo, TableAction action) {
    super(repo, action);
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

  @Override
  public void onUpdate(Map<String, ?> data) throws BuilderValidationException {
    super.onUpdate(data);
    System.out.println("OnUpdate");
    Map<String, String> tempData = (Map<String, String>) data;
    ClienteBuilder builder = new ClienteBuilder()
        .withId(Integer.parseInt(tempData.get("id")))
        .withCEP(tempData.get("CEP"))
        .withCPF(tempData.get("CPF"))
        .withEmail(tempData.get("Email"))
        .withNome(tempData.get("Nome"));
        
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate localDate = LocalDate.parse(tempData.get("DataNascimento"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataNascimento(date);
    } catch (Exception e) {
      builder.withDataNascimento(null);
    }

    Cliente updatingData = builder.build();
    try {
      this.repo.atualizar(updatingData);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void onCreate(Map<String, ?> data) throws BuilderValidationException {
    super.onCreate(data);
    Map<String, String> tempData = (Map<String, String>) data;
    ClienteBuilder builder = new ClienteBuilder();
    builder.withNome(tempData.get("Nome"));
    builder.withEmail(tempData.get("Email"));
    builder.withCPF(tempData.get("CPF"));
    builder.withCEP(tempData.get("CEP"));

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate localDate = LocalDate.parse(tempData.get("DataNascimento"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataNascimento(date);
    } catch (Exception e) {
      builder.withDataNascimento(null);
    }

    Cliente creatingData = builder.build();
    this.repo.criar(creatingData);

  }

}
