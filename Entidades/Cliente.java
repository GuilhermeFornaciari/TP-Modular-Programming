import java.sql.Date;

import Interfaces.Entidade;

public class Cliente extends Entidade{
  Integer Id;
  String Nome;
  String CPF;
  String Email;
  String CEP;
  Date DataNascimento;

  public Cliente(String nome, String cpf, String email, String cep, Date data_nascimento) {
    Nome = nome;
    CPF = cpf;
    Email = email;
    CEP = cep;
    DataNascimento = data_nascimento;
  } 
}
