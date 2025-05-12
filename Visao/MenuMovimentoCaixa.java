package Visao;

import java.util.Scanner;

import Modelo.MovimentoCaixa;
import Modelo.Receita;
import Modelo.Despesa;
import Persistencia.IRepositorioGeral;

public class MenuMovimentoCaixa {
    public static void exibirMenuMovimentoCaixa(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo, IRepositorioGeral<Receita> receitaRepo, IRepositorioGeral<Despesa> despesaRepo) {
        int opcao;

        do {
            System.out.println("\n=== Menu Movimento Caixa ===");
            System.out.println("1. Criar Movimentação de Caixa");
            System.out.println("2. Adicionar Receita à Movimentação");
            System.out.println("3. Adicionar Despesa à Movimentação");
            System.out.println("4. Visualizar Movimentação por ID");
            System.out.println("5. Visualizar Todas as Movimentações");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = MenuPrincipal.lerInteiro(scanner);

            switch (opcao) {
                case 1 -> criarMovimentacao(scanner, movimentoRepo);
                case 2 -> adicionarReceita(scanner, movimentoRepo, receitaRepo);
                case 3 -> adicionarDespesa(scanner, movimentoRepo, despesaRepo);
                case 4 -> visualizarMovimentacaoPorId(scanner, movimentoRepo);
                case 5 -> visualizarTodasMovimentacoes(movimentoRepo);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarMovimentacao(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo) {
        try {
            System.out.print("Data de Criação (aaaa-mm-dd): ");
            String dataCriacao = scanner.nextLine();
            MovimentoCaixa movimento = new MovimentoCaixa(null, java.sql.Date.valueOf(dataCriacao));
            Integer id = movimentoRepo.criar(movimento);
            System.out.println("Movimentação criada com sucesso! ID: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao criar movimentação.");
        }
    }

    private static void adicionarReceita(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo, IRepositorioGeral<Receita> receitaRepo) {
        try {
            System.out.print("ID da Movimentação: ");
            Integer idMovimento = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            MovimentoCaixa movimento = movimentoRepo.pegar_um(idMovimento);

            System.out.print("ID da Receita: ");
            Integer idReceita = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Receita receita = receitaRepo.pegar_um(idReceita);

            movimento.adicionarReceita(receita);
            System.out.println("Receita adicionada à movimentação com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar receita à movimentação.");
        }
    }

    private static void adicionarDespesa(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo, IRepositorioGeral<Despesa> despesaRepo) {
        try {
            System.out.print("ID da Movimentação: ");
            Integer idMovimento = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            MovimentoCaixa movimento = movimentoRepo.pegar_um(idMovimento);

            System.out.print("ID da Despesa: ");
            Integer idDespesa = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Despesa despesa = despesaRepo.pegar_um(idDespesa);

            movimento.adicionarDespesa(despesa);
            System.out.println("Despesa adicionada à movimentação com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar despesa à movimentação.");
        }
    }

    private static void visualizarMovimentacaoPorId(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo) {
        try {
            System.out.print("ID da Movimentação: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            MovimentoCaixa movimento = movimentoRepo.pegar_um(id);
            System.out.println(movimento);
        } catch (Exception e) {
            System.out.println("Erro ao visualizar movimentação.");
        }
    }

    private static void visualizarTodasMovimentacoes(IRepositorioGeral<MovimentoCaixa> movimentoRepo) {
        System.out.println("\nTodas as Movimentações:");
        System.out.println(movimentoRepo);
    }
}