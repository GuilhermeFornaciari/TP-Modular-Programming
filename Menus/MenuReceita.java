package Menus;

import Entidades.Receita;
import Repositorio.RepositorioGeral;
import java.util.Scanner;

public class MenuReceita {
    public static void exibirMenuReceita(Scanner scanner, RepositorioGeral<Receita> receitaRepo) {
        int opcao;
        do {
            System.out.println("\n=== Menu Receita ===");
            System.out.println("1. Inserir Receita");
            System.out.println("2. Alterar Receita");
            System.out.println("3. Apagar Receita");
            System.out.println("4. Visualizar Receita por ID");
            System.out.println("5. Visualizar Todas as Receitas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = MenuPrincipal.lerInteiro(scanner);

            switch (opcao) {
                case 1 -> inserirReceita(scanner, receitaRepo);
                case 2 -> alterarReceita(scanner, receitaRepo);
                case 3 -> apagarReceita(scanner, receitaRepo);
                case 4 -> visualizarReceitaPorId(scanner, receitaRepo);
                case 5 -> visualizarTodasReceitas(receitaRepo);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void inserirReceita(Scanner scanner, RepositorioGeral<Receita> receitaRepo) {
        try {
            System.out.print("Valor: ");
            Float valor = scanner.nextFloat();
            scanner.nextLine(); // Limpa o buffer
            System.out.print("Data de Criação (aaaa-mm-dd): ");
            String dataCriacao = scanner.nextLine();
            System.out.print("Data de Pagamento (aaaa-mm-dd): ");
            String dataPagamento = scanner.nextLine();
            System.out.print("ID do Cliente: ");
            Integer idCliente = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            Receita receita = new Receita(null, valor, java.sql.Date.valueOf(dataPagamento), java.sql.Date.valueOf(dataCriacao), idCliente);
            receita.setIdCliente(idCliente);
            Integer id = receitaRepo.criar(receita);
            System.out.println("Receita inserida com sucesso! ID da Receita: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao inserir receita");
        }
    }

    private static void alterarReceita(Scanner scanner, RepositorioGeral<Receita> receitaRepo) {
        try {
            System.out.print("ID da Receita para alterar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Receita receita = receitaRepo.pegar_um(id);
            System.out.print("Novo Valor: ");
            receita.setValor(scanner.nextFloat());
            scanner.nextLine(); // Limpa o buffer
            System.out.print("Nova Data de Criação (aaaa-mm-dd): ");
            receita.setDataCriacao(java.sql.Date.valueOf(scanner.nextLine()));
            System.out.print("Nova Data de Pagamento (aaaa-mm-dd): ");
            receita.setDataPagamento(java.sql.Date.valueOf(scanner.nextLine()));
            System.out.print("Novo ID do Cliente: ");
            receita.setIdCliente(scanner.nextInt());
            scanner.nextLine(); // Limpa o buffer
            receitaRepo.atualizar(receita);
            System.out.println("Receita alterada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar receita");
        }
    }

    private static void apagarReceita(Scanner scanner, RepositorioGeral<Receita> receitaRepo) {
        try {
            System.out.print("ID da Receita para apagar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Receita receita = receitaRepo.pegar_um(id);
            receitaRepo.deletar(receita);
            System.out.println("Receita apagada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao apagar receita");
        }
    }

    private static void visualizarReceitaPorId(Scanner scanner, RepositorioGeral<Receita> receitaRepo) {
        try {
            System.out.print("ID da Receita: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Receita receita = receitaRepo.pegar_um(id);

            System.out.println("ID: " + receita.getId());
            System.out.println("Valor: " + receita.getValor());
            System.out.println("Data de Criação: " + receita.getDataCriacao());
            System.out.println("Data de Pagamento: " + receita.getDataPagamento());
            System.out.println("ID do Cliente: " + receita.getIdCliente());
        } catch (Exception e) {
            System.out.println("Erro ao visualizar receita");
        }
    }

    private static void visualizarTodasReceitas(RepositorioGeral<Receita> receitaRepo) {
        System.out.println("\nTodas as Receitas:");
        System.out.println(receitaRepo);
    }
}