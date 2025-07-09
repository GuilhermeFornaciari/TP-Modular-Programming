package Programa.Visao.Screens.ItemMovimento;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import Programa.Modelo.ItemMovimento;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.NotFoundException;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.List.BaseList;
import Programa.Visao.List.TableActionButton;
import Programa.Visao.List.TableActionButton.TableAction;
import Programa.Visao.List.TableColumnConfig;
import Programa.Visao.List.TableConfig;

public class ItemMovimentoList extends BaseList<ItemMovimento> {

  ItemMovimentoForm form;

  IRepositorioGeral<TipoTransacao> tipoTransacaoRepo;
  IRepositorioGeral<MovimentoCaixa> movimentoCaixaRepo;

  Integer idMovimentoCaixa;

  public ItemMovimentoList(IRepositorioGeral<MovimentoCaixa> repo, IRepositorioGeral<TipoTransacao> tipoTransacaoRepo,
      Integer idMovimentoCaixa) {
    super("Item Movimento");
    this.tipoTransacaoRepo = tipoTransacaoRepo;
    this.movimentoCaixaRepo = repo;
    this.idMovimentoCaixa = idMovimentoCaixa;
    setupTableBody();

    this.add(headerPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("tipo", "dataCriacao", "dataPagamento", "descricao", "valor");
    List<String> labels = List.of("Tipo", "Data de Criação", "Data de Pagamento", "Descrição", "Valor");
    List<Double> widths = List.of(0.2, 0.1, 0.1, 0.4, 0.2);

    for (int i = 0; i < labels.size(); i++) {
      tableConfig.addColumnConfig(new TableColumnConfig(names.get(i), labels.get(i), widths.get(i)));
    }

    searchableFields.add("descricao");
  }

  @Override
  public void getTableData() {
    try {
      MovimentoCaixa movimentoCaixa = movimentoCaixaRepo.pegar_um(idMovimentoCaixa);
      tableData = movimentoCaixa.getItemMovimentos();
    } catch (Exception e) {
      tableData = new ArrayList<>();
    }
  }

  @Override
  public void onUpdateClick(TableActionButton button) {
    super.onUpdateClick(button);
    try {
      if (form != null) {
        form.setVisible(false);
        form.dispose();
      }

      ItemMovimento itemMovimento = null;

      MovimentoCaixa movimentoCaixa = movimentoCaixaRepo.pegar_um(idMovimentoCaixa);
      for (ItemMovimento item : movimentoCaixa.getItemMovimentos()) {
        if (item.getId().equals(button.getItemId())) {
          itemMovimento = item;
        }
      }

      System.out.println(button.getItemId());
      if (itemMovimento == null)
        return;

      form = new ItemMovimentoForm(repo, tipoTransacaoRepo, movimentoCaixaRepo, idMovimentoCaixa, TableAction.UPDATE);
      form.registerObserver(this);

      form.setVisible(true);
      form.populateForm(itemMovimento);
    } catch (Exception e) {
      e.printStackTrace();
      form.setVisible(false);
      form.dispose();
    }
  }

  @Override
  public void onDeleteClick(TableActionButton button) {
    try {
      MovimentoCaixa movimentoCaixa = movimentoCaixaRepo.pegar_um(idMovimentoCaixa);
      movimentoCaixa.removerTransacao(button.getItemId());
      movimentoCaixaRepo.atualizar(movimentoCaixa);
      getTableData();
      populateTableModel();
      updateTable();
    } catch (NotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onCreateClick() {
    if (form != null) {
      form.setVisible(false);
      form.dispose();
    }
    form = new ItemMovimentoForm(repo, tipoTransacaoRepo, movimentoCaixaRepo, idMovimentoCaixa, TableAction.CREATE);
    form.registerObserver(this);
    form.setVisible(true);
    try {
    } catch (Exception e) {
      form.setVisible(false);
      form.dispose();
    }
  }

}
