package Programa;
import Programa.Modelo.Cliente;
import Programa.Modelo.ItemMovimento;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.TipoDespesaReceita;
import Programa.Modelo.TipoTransacao;
import Programa.Persistencia.BancoDeDados;
import Programa.Visao.MainLayout;

public class Main {
    public static void main(String[] args) {
        BancoDeDados banco = new BancoDeDados();
        Cliente cliente = new Cliente(
            1, "Bruno",
            "11122233344",
            "bruno@email.com",
            "123456789",
            java.sql.Date.valueOf("2004-07-13")
        );
        TipoTransacao tipoTransacao = new TipoTransacao(1, TipoDespesaReceita.RECEITA,"Pagamento de cliente");
        TipoTransacao tipoTransacao2 = new TipoTransacao(2, TipoDespesaReceita.DESPESA,"Pagamento de contas");
        banco.tipoTransacao.criar(tipoTransacao);
        banco.tipoTransacao.criar(tipoTransacao2);
        banco.cliente.criar(cliente);
        MovimentoCaixa movimentoCaixa = new MovimentoCaixa(1,java.sql.Date.valueOf("2025-05-13"),cliente);
        ItemMovimento item1 = new ItemMovimento(1, java.sql.Date.valueOf("2025-05-13"),"sim de contas",tipoTransacao, 221);
        ItemMovimento item2 = new ItemMovimento(2, java.sql.Date.valueOf("2025-05-13"),"não de contas",tipoTransacao2,3213);
        movimentoCaixa.adicionarTransacao(item1);
        movimentoCaixa.adicionarTransacao(item2);
        banco.movimentoCaixa.criar(movimentoCaixa);
        cliente.setNome("Marcelo");
        try{
            banco.cliente.atualizar(cliente);  
            System.out.println(banco.cliente);
            System.out.println(banco.movimentoCaixa);
        }
        catch (Exception e){
        }
        new MainLayout(banco).setVisible(true);

    }
}
