package Programa.Visao.Screens.ItemMovimento;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import Programa.Modelo.Cliente;
import Programa.Modelo.ItemMovimento;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.Form.BaseForm;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Shared.ComboBoxItem;

public class ItemMovimentoForm extends BaseForm<ItemMovimento> {

  IRepositorioGeral<TipoTransacao> tipoTransacaoRepo;
  public ItemMovimentoForm(IRepositorioGeral<ItemMovimento> repo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo,
      TableAction action) {
    super(repo, action);
    this.tipoTransacaoRepo = tipoTransacaoRepo;
    this.setupFields();
  }

  public void setupFields() {

    ArrayList<ComboBoxItem> tipoTransacaoOptions = new ArrayList<>();

    ArrayList<TipoTransacao> tiposTransacao = tipoTransacaoRepo.pegar_todos();
    tiposTransacao.forEach((tipoTransacao) -> {
      tipoTransacaoOptions.add(new ComboBoxItem(tipoTransacao.getDescricao(), tipoTransacao.getId().toString()));
    });

    addTextField("id", "Id");
    addDropdownField("tipo", "Tipo", tipoTransacaoOptions);
    addFormattedTextField("dataCriacao", "Data de Criacao", "##/##/####");
    addFormattedTextField("dataPagamento", "Data de Pagamento", "##/##/####");
    addTextField("descricao", "Descrição");
    addTextField("valor", "Valor");
  }

  public void actionPerformed(ActionEvent e) {
  }

  public void onUpdate(Map<String, ?> data) throws BuilderValidationException {
    super.onUpdate(data);
    Map<String, String> tempData = (Map<String, String>) data;
    ItemMovimentoBuilder builder = new ItemMovimentoBuilder()
        .withId(Integer.parseInt(tempData.get("id")));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    try {
      LocalDate localDate = LocalDate.parse(tempData.get("DataCriacao"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataCriacao(date);
    } catch (Exception e) {
      builder.withDataCriacao(null);
    }
    try {
      LocalDate localDate = LocalDate.parse(tempData.get("DataPagamento"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataPagamento(date);
    } catch (Exception e) {
      builder.withDataPagamento(null);
    }

    builder.withDescricao(tempData.get("descricao"));

    
    try {
      TipoTransacao tipoTransacao = tipoTransacaoRepo.pegar_um(Integer.parseInt(tempData.get("tipo")));
      builder.withTipo(tipoTransacao);
    } catch (Exception e) { 
      builder.withTipo(null);
    }

    builder.withValor(Float.parseFloat(tempData.get("valor")));

    ItemMovimento updatingData = builder.build();
    try {
      this.repo.atualizar(updatingData);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
