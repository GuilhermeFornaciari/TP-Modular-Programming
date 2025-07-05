package Programa.Visao.Cliente;

import java.sql.Date;

import Programa.Modelo.Cliente;

public class ClienteBuilder {

  private Integer id;
  private String Nome;
  private String CPF;
  private String Email;
  private String CEP;
  private Date DataNascimento;

  public ClienteBuilder() {
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public Cliente build() {
    if (id == null) {
      return new Cliente(Nome, CPF, Email, CEP, DataNascimento);
    } else {
      return new Cliente(id, Nome, CPF, Email, CEP, DataNascimento);
    }
  }

  public void setNome(String nome) {
    Nome = nome;
  }
  public void setCPF(String cPF) {
    CPF = cPF;
  }
  public void setCEP(String cEP) {
    CEP = cEP;
  }
  public void setDataNascimento(Date dataNascimento) {
    DataNascimento = dataNascimento;
  }
  public void setEmail(String email) {
    Email = email;
  }

}
