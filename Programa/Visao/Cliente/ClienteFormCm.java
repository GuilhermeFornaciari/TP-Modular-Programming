package Programa.Visao.Cliente;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.BaseForm;
import Programa.Visao.BaseList.TableActionButton.TableAction;

public class ClienteFormCm extends BaseForm<Cliente> {

  public ClienteFormCm(IRepositorioGeral<Cliente> repo, TableAction action) {
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
  public void onUpdate(Map<String, String> data) {
    super.onUpdate(data);
    System.out.println("OnUpdate");
    ClienteBuilder builder = new ClienteBuilder()
    .withId(Integer.parseInt(data.get("id")))
    .withCEP(data.get("CEP"))
    .withCPF(data.get("CPF"))
    .withCEP(data.get("Email"))
    .withCEP(data.get("Nome"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    LocalDate localDate = LocalDate.parse(data.get("DataNascimento"), formatter);
    Date date = Date.valueOf(localDate);
    builder.withDataNascimento(date);
    try {
      this.repo.atualizar(builder.build());
    } catch (Exception e) {
    }

  }

  @Override
  public void onCreate(Map<String, String> data) {
    super.onCreate(data);
    System.out.println("OnUpdate");
    ClienteBuilder builder = new ClienteBuilder();
    builder.withId(Integer.parseInt(data.get("id")));
    
    builder.withNome(data.get("Nome"));
    builder.withEmail(data.get("Email"));
    builder.withCPF(data.get("CPF"));
    builder.withCEP(data.get("CEP"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    LocalDate localDate = LocalDate.parse(data.get("DataNascimento"), formatter);
    Date date = Date.valueOf(localDate);
    builder.withDataNascimento(date);
    try {
      this.repo.criar(builder.build());
    } catch (Exception e) {
      
    }
  }

}
