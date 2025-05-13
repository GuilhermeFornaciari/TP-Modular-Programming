package Programa.Persistencia;

import Programa.Modelo.Cliente;
import Programa.Modelo.Despesa;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.Receita;

public class BancoDeDados {
  public IRepositorioGeral<Cliente> cliente;
  public IRepositorioGeral<Despesa> despesa;
  public IRepositorioGeral<Receita> receita;
  public IRepositorioGeral<MovimentoCaixa> movimentoCaixa;
  public BancoDeDados(){
    cliente = new RepositorioGeral<Cliente>();
    despesa = new RepositorioGeral<Despesa>();
    receita = new RepositorioGeral<Receita>();
    movimentoCaixa = new RepositorioGeral<MovimentoCaixa>();
  }
}
