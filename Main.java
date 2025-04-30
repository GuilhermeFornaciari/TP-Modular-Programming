import java.sql.Date;
import Entidades.Cliente;
import Repositorio.RepositorioGeral;

public class Main {
  public static void main(String [] args){
    RepositorioGeral<Cliente> repo = new RepositorioGeral<Cliente>();
    Cliente cliente = new Cliente("Marcelo de alveira", "31548264", "marceliano@gail.com", "76950000", new Date(14537));
    Integer id = repo.criar(cliente);
    
    try {
      Cliente cliente2 = repo.pegar_um(id);
      cliente2.Nome = "Marcela de alexandria";
      repo.atualizar(cliente2);
      Cliente cliente3 = repo.pegar_um(id);
      System.out.println(cliente3);

      
    } catch (Exception e){
      System.out.println("Deu ruim");
    }
  }
}
