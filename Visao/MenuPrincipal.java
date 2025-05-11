package Visao;

import java.util.Scanner;

import Modelo.Cliente;
import Modelo.Despesa;
import Modelo.Receita;
import Persistencia.RepositorioGeral;

public class MenuPrincipal {
    public static void exibirMenuPrincipal(
        RepositorioGeral<Cliente> clienteRepo,
        RepositorioGeral<Receita> receitaRepo,
        RepositorioGeral<Despesa> despesaRepo
    ) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Receitas");
            System.out.println("3. Gerenciar Despesas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1 -> MenuCliente.exibirMenuCliente(scanner, clienteRepo);
                case 2 -> MenuReceita.exibirMenuReceita(scanner, receitaRepo);
                case 3 -> MenuDespesa.exibirMenuDespesa(scanner, despesaRepo);
                case 0 -> System.out.println("Saindo do programa...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                System.out.print("Escolha uma opção: ");
            }
        }
    }
}