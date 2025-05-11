package Programa;
import Modelo.Cliente;
import Modelo.Despesa;
import Modelo.Receita;
import Persistencia.BancoDeDados;
import Persistencia.RepositorioGeral;
import Visao.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        BancoDeDados banco = new BancoDeDados();

        Cliente cliente = new Cliente(
            "Bruno",
            "11122233344",
            "bruno@email.com",
            "123456789",
            java.sql.Date.valueOf("2004-07-13")
        );
        Integer clienteId = banco.cliente.criar(cliente);
        System.out.println("Cliente criado com sucesso! ID: " + clienteId);

        Receita receita = new Receita(
            null,
            1500.00f,
            java.sql.Date.valueOf("2025-05-13"),
            java.sql.Date.valueOf("2025-05-03"),
            clienteId
        );
        Integer receitaId = banco.receita.criar(receita);
        System.out.println("Receita criada com sucesso! ID: " + receitaId);

        Despesa despesa = new Despesa(
            null,
            1000.00f,
            java.sql.Date.valueOf("2025-05-03"),
            java.sql.Date.valueOf("2025-05-13")
        );
        Integer despesaId = banco.despesa.criar(despesa);
        System.out.println("Despesa criada com sucesso! ID: " + despesaId);

        // Exibindo o menu principal
        MenuPrincipal.exibirMenuPrincipal(banco);
    }
}
