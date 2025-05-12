package Persistencia;

import Modelo.Cliente;
import Modelo.Despesa;
import Modelo.MovimentoCaixa;
import Modelo.Receita;

public class BancoDeDados {
  public IRepositorioGeral<Cliente> cliente;
  public IRepositorioGeral<Despesa> despesa;
  public IRepositorioGeral<Receita> receita;
  public IRepositorioGeral<MovimentoCaixa> movimentoCaixa;
  public BancoDeDados(){
    cliente = new RepositorioGeral<Cliente>();
    despesa = new RepositorioGeral<Despesa>();
    receita = new RepositorioGeral<Receita>();
    movimentoCaixa = new RepositorioGeral<>();
  }
}
