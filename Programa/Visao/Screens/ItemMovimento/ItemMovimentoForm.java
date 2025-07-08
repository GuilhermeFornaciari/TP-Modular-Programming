package Programa.Visao.Screens.ItemMovimento;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import Programa.Modelo.ItemMovimento;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.Builder.BuilderValidationException;
import Programa.Visao.Form.BaseForm;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.Shared.ComboBoxItem;

public class ItemMovimentoForm extends BaseForm<ItemMovimento> {

  IRepositorioGeral<TipoTransacao> tipoTransacaoRepo;
  IRepositorioGeral<MovimentoCaixa> movimentoCaixaRepo;
  Integer idMovimentoCaixa;

  public ItemMovimentoForm(IRepositorioGeral<ItemMovimento> repo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo,
      IRepositorioGeral<MovimentoCaixa> movimentoCaixaRepo, Integer idMovimentoCaixa,
      TableAction action) {
    super(repo, action);
    this.tipoTransacaoRepo = tipoTransacaoRepo;
    this.movimentoCaixaRepo = movimentoCaixaRepo;
    this.idMovimentoCaixa = idMovimentoCaixa;
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
    addNumericTextField("valor", "Valor");
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
      LocalDate localDate = LocalDate.parse(tempData.get("dataCriacao"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataCriacao(date);
    } catch (Exception e) {
      e.printStackTrace();

      builder.withDataCriacao(null);
    }
    try {
      LocalDate localDate = LocalDate.parse(tempData.get("dataPagamento"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataPagamento(date);
    } catch (Exception e) {
      e.printStackTrace();
      builder.withDataPagamento(null);
    }

    builder.withDescricao(tempData.get("descricao"));

    try {
      TipoTransacao tipoTransacao = tipoTransacaoRepo.pegar_um(Integer.parseInt(tempData.get("tipo")));
      builder.withTipo(tipoTransacao);
    } catch (Exception e) {
      builder.withTipo(null);
    }

    try {
      String valorStr = tempData.get("valor");
      if (valorStr.endsWith(".")) valorStr.replace(".", "");
      builder.withValor(Float.parseFloat(valorStr));
    } catch (Exception e) {
      builder.withValor(null);
    }

    ItemMovimento updatingData = builder.build();
    try {
      MovimentoCaixa movimentoCaixa = movimentoCaixaRepo.pegar_um(idMovimentoCaixa);
      movimentoCaixa.atualizarTransacao(updatingData);
      movimentoCaixaRepo.atualizar(movimentoCaixa);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onCreate(Map<String, ?> data) throws BuilderValidationException {
    super.onCreate(data);
    Map<String, String> tempData = (Map<String, String>) data;
    ItemMovimentoBuilder builder = new ItemMovimentoBuilder();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    try {
      LocalDate localDate = LocalDate.parse(tempData.get("dataCriacao"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataCriacao(date);
    } catch (Exception e) {
      e.printStackTrace();

      builder.withDataCriacao(null);
    }
    try {
      LocalDate localDate = LocalDate.parse(tempData.get("dataPagamento"), formatter);
      Date date = Date.valueOf(localDate);
      builder.withDataPagamento(date);
    } catch (Exception e) {
      e.printStackTrace();
      builder.withDataPagamento(null);
    }

    builder.withDescricao(tempData.get("descricao"));

    try {
      TipoTransacao tipoTransacao = tipoTransacaoRepo.pegar_um(Integer.parseInt(tempData.get("tipo")));
      builder.withTipo(tipoTransacao);
    } catch (Exception e) {
      builder.withTipo(null);
    }

    try {
      String valorStr = tempData.get("valor");
      if (valorStr.endsWith(".")) valorStr.replace(".", "");
      builder.withValor(Float.parseFloat(valorStr));
    } catch (Exception e) {
      builder.withValor(null);
    }

    ItemMovimento creatingData = builder.build();
    try {
      MovimentoCaixa movimentoCaixa = movimentoCaixaRepo.pegar_um(idMovimentoCaixa);
      movimentoCaixa.adicionarTransacao(creatingData);
      movimentoCaixaRepo.atualizar(movimentoCaixa);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
