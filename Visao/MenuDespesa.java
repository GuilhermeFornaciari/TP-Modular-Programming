package Visao;

import java.util.Scanner;

import Modelo.Despesa;
import Persistencia.IRepositorioGeral;
import Persistencia.RepositorioGeral;

public class MenuDespesa {
    public static void exibirMenuDespesa(Scanner scanner, IRepositorioGeral<Despesa> despesaRepo) {
        int opcao;

        do {
            System.out.println("\n=== Menu Despesa ===");
            System.out.println("1. Inserir Despesa");
            System.out.println("2. Alterar Despesa");
            System.out.println("3. Apagar Despesa");
            System.out.println("4. Visualizar Despesa por ID");
            System.out.println("5. Visualizar Todas as Despesas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = MenuPrincipal.lerInteiro(scanner);

            switch (opcao) {
                case 1 -> inserirDespesa(scanner, despesaRepo);
                case 2 -> alterarDespesa(scanner, despesaRepo);
                case 3 -> apagarDespesa(scanner, despesaRepo);
                case 4 -> visualizarDespesaPorId(scanner, despesaRepo);
                case 5 -> visualizarTodasDespesas(despesaRepo);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void inserirDespesa(Scanner scanner, IRepositorioGeral<Despesa> despesaRepo) {
        try {
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Limpa o buffer
            System.out.print("Data de Pagamento (aaaa-mm-dd): ");
            String dataPagamento = scanner.nextLine();
            System.out.print("Data de Criação (aaaa-mm-dd): ");
            String dataCriacao = scanner.nextLine();
            System.out.print("ID do movimento de caixa: ");
            Integer idMovimentoCaixa = scanner.nextInt();

            Despesa despesa = new Despesa(null, (float) valor, java.sql.Date.valueOf(dataPagamento), java.sql.Date.valueOf(dataCriacao),idMovimentoCaixa);
            Integer id = despesaRepo.criar(despesa);
            System.out.println("Despesa inserida com sucesso! ID da Despesa: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao inserir despesa");
        }
    }

    private static void alterarDespesa(Scanner scanner, IRepositorioGeral<Despesa> despesaRepo) {
        try {
            System.out.print("ID da Despesa para alterar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Despesa despesa = despesaRepo.pegar_um(id);
            System.out.print("Novo Valor: ");
            despesa.setValor((float) scanner.nextDouble());
            scanner.nextLine(); // Limpa o buffer
            System.out.print("Nova Data de Pagamento (aaaa-mm-dd): ");
            despesa.setDataPagamento(java.sql.Date.valueOf(scanner.nextLine()));
            System.out.print("Nova Data de Criação (aaaa-mm-dd): ");
            despesa.setDataCriacao(java.sql.Date.valueOf(scanner.nextLine()));
            System.out.print("Novo ID do movimento de caixa: ");
            Integer idMovimentoCaixa = scanner.nextInt();
            despesa.setIdMovimentoCaixa(idMovimentoCaixa);
            despesaRepo.atualizar(despesa);
            System.out.println("Despesa alterada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar despesa");
        }
    }

    private static void apagarDespesa(Scanner scanner, IRepositorioGeral<Despesa> despesaRepo) {
        try {
            System.out.print("ID da Despesa para apagar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Despesa despesa = despesaRepo.pegar_um(id);
            despesaRepo.deletar(despesa);
            System.out.println("Despesa apagada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao apagar despesa");
        }
    }

    private static void visualizarDespesaPorId(Scanner scanner, IRepositorioGeral<Despesa> despesaRepo) {
        try {
            System.out.print("ID da Despesa para visualizar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Despesa despesa = despesaRepo.pegar_um(id);

            System.out.println("ID: " + despesa.getId());
            System.out.println("Valor: " + despesa.getValor());
            System.out.println("Data de Pagamento: " + despesa.getDataPagamento());
            System.out.println("Data de Criação: " + despesa.getDataCriacao());
        } catch (Exception e) {
            System.out.println("Erro ao visualizar despesa");
        }
    }

    private static void visualizarTodasDespesas(IRepositorioGeral<Despesa> despesaRepo) {
        System.out.println("\nTodas as Despesas:");
        System.out.println(despesaRepo);
    }
}