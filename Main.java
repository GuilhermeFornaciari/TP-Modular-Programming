

import Entidades.Cliente;
import Entidades.Despesa;
import Entidades.Receita;
import Menus.MenuPrincipal;
import Repositorio.RepositorioGeral;

public class Main {
    public static void main(String[] args) {
        // Criação dos repositórios
        RepositorioGeral<Cliente> clienteRepo = new RepositorioGeral<>();
        RepositorioGeral<Receita> receitaRepo = new RepositorioGeral<>();
        RepositorioGeral<Despesa> despesaRepo = new RepositorioGeral<>();

        Cliente cliente = new Cliente(
            "Bruno",
            "11122233344",
            "bruno@email.com",
            "123456789",
            java.sql.Date.valueOf("2004-07-13")
        );
        Integer clienteId = clienteRepo.criar(cliente);
        System.out.println("Cliente criado com sucesso! ID: " + clienteId);

        Receita receita = new Receita(
            null,
            1500.00f,
            java.sql.Date.valueOf("2025-05-13"),
            java.sql.Date.valueOf("2025-05-03"),
            clienteId
        );
        Integer receitaId = receitaRepo.criar(receita);
        System.out.println("Receita criada com sucesso! ID: " + receitaId);

        Despesa despesa = new Despesa(
            null,
            1000.00f,
            java.sql.Date.valueOf("2025-05-03"),
            java.sql.Date.valueOf("2025-05-13")
        );
        Integer despesaId = despesaRepo.criar(despesa);
        System.out.println("Despesa criada com sucesso! ID: " + despesaId);

        // Exibindo o menu principal
        MenuPrincipal.exibirMenuPrincipal(clienteRepo, receitaRepo, despesaRepo);
    }
}