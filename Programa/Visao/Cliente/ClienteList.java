package Programa.Visao.Cliente;

import java.util.List;

import Programa.Modelo.Cliente;
import Programa.Persistencia.IRepositorioGeral;
import Programa.Visao.TableColumnConfig;
import Programa.Visao.TableConfig;
import Programa.Visao.BaseList.BaseList;

public class ClienteList extends BaseList<Cliente> {

  public ClienteList(IRepositorioGeral<Cliente> repo) {
    super(repo);
  }

  @Override
  public void setupTableConfig() {
    tableConfig = new TableConfig();
    List<String> names = List.of("Nome", "CPF", "Email", "CEP", "DataNascimento");
    List<String> labels = List.of("Nome", "CPF", "Email", "CEP", "Nascimento"); 
    List<Double> widths = List.of(0.2, 0.2, 0.2, 0.2, 0.2); 

    for (int i=0; i<labels.size(); i++) {
      tableConfig.addColumnConfig(new TableColumnConfig(names.get(i), labels.get(i), widths.get(i)));
    }

  }

}
