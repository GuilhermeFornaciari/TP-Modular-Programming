package Programa.Visao;

import java.util.Scanner;

import Programa.Modelo.Despesa;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.Receita;
import Programa.Persistencia.BancoDeDados;
import Programa.Persistencia.IRepositorioGeral;

public class MenuMovimentoCaixa {
    public static void exibirMenuMovimentoCaixa(Scanner scanner, BancoDeDados banco) {
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
                case 1 -> criarMovimentacao(scanner, banco.movimentoCaixa);
                case 2 -> adicionarReceita(scanner, banco);
                case 3 -> adicionarDespesa(scanner, banco);
                case 4 -> visualizarMovimentacaoPorId(scanner, banco.movimentoCaixa);
                case 5 -> visualizarTodasMovimentacoes(banco.movimentoCaixa);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarMovimentacao(Scanner scanner, IRepositorioGeral<MovimentoCaixa> movimentoRepo) {
        try {
            System.out.print("Data de Criação (aaaa-mm-dd): ");
            String dataCriacao = scanner.nextLine();
            System.out.print("ID do cliente: ");
            Integer idCliente = scanner.nextInt();
            MovimentoCaixa movimento = new MovimentoCaixa(null, java.sql.Date.valueOf(dataCriacao), idCliente);
            Integer id = movimentoRepo.criar(movimento);
            System.out.println("Movimentação criada com sucesso! ID: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao criar movimentação.");
        }
    }

    private static void adicionarReceita(Scanner scanner, BancoDeDados banco) {
        try {
            System.out.print("ID da Movimentação: ");
            Integer idMovimento = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            MovimentoCaixa movimento = banco.movimentoCaixa.pegar_um(idMovimento);

            System.out.print("ID da Receita: ");
            Integer idReceita = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Receita receita = banco.receita.pegar_um(idReceita);

            if (receita.getIdMovimentoCaixa().equals(movimento.Id)){
                movimento.adicionarReceita(receita);
                System.out.println("Receita adicionada à movimentação com sucesso!");
            } else {
                System.out.println("Id da movimentação na receita não é igual à do registro!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar receita à movimentação.");
        }
    }

    private static void adicionarDespesa(Scanner scanner, BancoDeDados banco) {
        try {
            System.out.print("ID da Movimentação: ");
            Integer idMovimento = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            MovimentoCaixa movimento = banco.movimentoCaixa.pegar_um(idMovimento);

            System.out.print("ID da Despesa: ");
            Integer idDespesa = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Despesa despesa = banco.despesa.pegar_um(idDespesa);
            if (despesa.getIdMovimentoCaixa().equals(movimento.Id)){
                movimento.adicionarDespesa(despesa);
                System.out.println("Despesa adicionada à movimentação com sucesso!");
            } else {
                System.out.println("Id da movimentação na despesa não é igual à do registro!");
            }
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