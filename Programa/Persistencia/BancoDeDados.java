package Programa.Persistencia;

import Programa.Modelo.Cliente;
import Programa.Modelo.MovimentoCaixa;
import Programa.Modelo.TipoTransacao;

public class BancoDeDados {
  public IRepositorioGeral<Cliente> cliente;
  public IRepositorioGeral<MovimentoCaixa> movimentoCaixa;
  public IRepositorioGeral<TipoTransacao> tipoTransacao;
  public BancoDeDados(){
    cliente = new RepositorioGeral<Cliente>();
    movimentoCaixa = new RepositorioGeral<MovimentoCaixa>();
    tipoTransacao = new RepositorioGeral<TipoTransacao>();
  }
}
