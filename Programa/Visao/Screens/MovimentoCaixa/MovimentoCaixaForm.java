package Programa.Visao.Screens.MovimentoCaixa;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import Programa.Modelo.Cliente;
import Programa.Modelo.MovimentoCaixa;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.Form.BaseForm;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Shared.ComboBoxItem;

public class MovimentoCaixaForm extends BaseForm<MovimentoCaixa> {
  
  IRepositorioGeral<Cliente> clienteRepo;
  public MovimentoCaixaForm(IRepositorioGeral<MovimentoCaixa> repo, IRepositorioGeral<Cliente> clienteRepo, TableAction action) {
    super(repo, action);
    this.clienteRepo = clienteRepo;
    this.setupFields();
  }

  public void setupFields() {

    ArrayList<ComboBoxItem> clienteOptions = new ArrayList<>();

    ArrayList<Cliente> clientes = clienteRepo.pegar_todos();
    clientes.forEach((cliente) -> {
      clienteOptions.add(new ComboBoxItem(cliente.getNome(), cliente.getId().toString()));
    });

    addTextField("id", "Id");
    addFormattedTextField("DataCriacao", "Data de Criacao", "##/##/####");
    addDropdownField("cliente", "Cliente", clienteOptions);
  }

  public void actionPerformed(ActionEvent e) {}

    @Override
  public void onUpdate(Map<String, ?> data) throws BuilderValidationException {
    super.onUpdate(data);
    Map<String, String> tempData = (Map<String, String>) data;
    MovimentoCaixaBuilder builder = new MovimentoCaixaBuilder()
        .withId(Integer.parseInt(tempData.get("id")));
    try {
      builder.withCliente(clienteRepo.pegar_um((Integer) Integer.parseInt((String) data.get("cliente"))));
    } catch (Exception e) {
      builder.withCliente(null);
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate localDate = LocalDate.parse(tempData.get("DataCriacao"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataCriacao(date);
    } catch (Exception e) {
      builder.withDataCriacao(null);
    }

    MovimentoCaixa updatingData = builder.build();
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
    MovimentoCaixaBuilder builder = new MovimentoCaixaBuilder();

    try {
      builder.withCliente(clienteRepo.pegar_um((Integer) Integer.parseInt((String) data.get("cliente"))));
    } catch (Exception e) {
      builder.withCliente(null);
    }

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate localDate = LocalDate.parse(tempData.get("DataCriacao"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataCriacao(date);
    } catch (Exception e) {
      builder.withDataCriacao(null);
    }

    MovimentoCaixa creatingData = builder.build();
    this.repo.criar(creatingData);

  }

}
 