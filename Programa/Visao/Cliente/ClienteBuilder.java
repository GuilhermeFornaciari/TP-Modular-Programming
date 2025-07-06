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
  
  
  public Cliente build() {
    validate();
    if (id == null) {
      return new Cliente(Nome, CPF, Email, CEP, DataNascimento);
    } else {
      return new Cliente(id, Nome, CPF, Email, CEP, DataNascimento);
    }
  }
  
  public ClienteBuilder withId(Integer id) {
    this.id = id;
    return this;
  }
  public ClienteBuilder withNome(String nome) {
    Nome = nome;
    return this;
  }

  public ClienteBuilder withCPF(String cPF) {
    CPF = cPF;
    return this;
  }

  public ClienteBuilder withCEP(String cEP) {
    CEP = cEP;
    return this;
  }

  public ClienteBuilder withDataNascimento(Date dataNascimento) {
    DataNascimento = dataNascimento;
    return this;
  }

  public ClienteBuilder withEmail(String email) {
    Email = email;
    return this;
  }

  public void validate() {
    if (Nome == null || Nome.trim().isEmpty()) {
      throw new IllegalStateException("Nome cannot be null or empty");
    }
    if (CPF == null || CPF.trim().isEmpty()) {
      throw new IllegalStateException("Nome cannot be null or empty");
    }
    throw new validationsErrors(this.validationsErrors)
  }

}
