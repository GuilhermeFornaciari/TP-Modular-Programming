package Visao;

import java.util.Scanner;

import Modelo.Cliente;
import Persistencia.RepositorioGeral;

public class MenuCliente {
    public static void exibirMenuCliente(Scanner scanner, RepositorioGeral<Cliente> clienteRepo) {
        int opcao;

        do {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1. Inserir Cliente");
            System.out.println("2. Alterar Cliente");
            System.out.println("3. Apagar Cliente");
            System.out.println("4. Visualizar Cliente por ID");
            System.out.println("5. Visualizar Todos os Clientes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = MenuPrincipal.lerInteiro(scanner);

            switch (opcao) {
                case 1 -> inserirCliente(scanner, clienteRepo);
                case 2 -> alterarCliente(scanner, clienteRepo);
                case 3 -> apagarCliente(scanner, clienteRepo);
                case 4 -> visualizarClientePorId(scanner, clienteRepo);
                case 5 -> visualizarTodosClientes(clienteRepo);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void inserirCliente(Scanner scanner, RepositorioGeral<Cliente> clienteRepo) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CEP: ");
            String cep = scanner.nextLine();
            System.out.print("Data de Nascimento (aaaa-mm-dd): ");
            String dataNascimento = scanner.nextLine();

            Cliente cliente = new Cliente(nome, cpf, email, cep, java.sql.Date.valueOf(dataNascimento));
            Integer id = clienteRepo.criar(cliente);
            System.out.println("Cliente inserido com sucesso! ID do Cliente: " + id);
        } catch (Exception e) {
            System.out.println("Erro ao inserir cliente");
        }
    }

    private static void alterarCliente(Scanner scanner, RepositorioGeral<Cliente> clienteRepo) {
        try {
            System.out.print("ID do Cliente para alterar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Cliente cliente = clienteRepo.pegar_um(id);
            System.out.print("Novo Nome: ");
            cliente.setNome(scanner.nextLine());
            System.out.print("Novo CPF: ");
            cliente.setCPF(scanner.nextLine());
            System.out.print("Novo Email: ");
            cliente.setEmail(scanner.nextLine());
            System.out.print("Novo CEP: ");
            cliente.setCEP(scanner.nextLine());
            System.out.print("Nova Data de Nascimento (aaaa-mm-dd): ");
            cliente.setDataNascimento(java.sql.Date.valueOf(scanner.nextLine()));
            clienteRepo.atualizar(cliente);
            System.out.println("Cliente alterado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar cliente");
        }
    }

    private static void apagarCliente(Scanner scanner, RepositorioGeral<Cliente> clienteRepo) {
        try {
            System.out.print("ID do Cliente para apagar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Cliente cliente = clienteRepo.pegar_um(id);
            clienteRepo.deletar(cliente);
            System.out.println("Cliente apagado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao apagar cliente");
        }
    }

    private static void visualizarClientePorId(Scanner scanner, RepositorioGeral<Cliente> clienteRepo) {
        try {
            System.out.print("ID do Cliente para visualizar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            Cliente cliente = clienteRepo.pegar_um(id);

            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCPF());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("CEP: " + cliente.getCEP());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
        } catch (Exception e) {
            System.out.println("Erro ao visualizar cliente");
        }
    }

    private static void visualizarTodosClientes(RepositorioGeral<Cliente> clienteRepo) {
        System.out.println("\nTodos os Clientes:");
        System.out.println(clienteRepo);
    }
}