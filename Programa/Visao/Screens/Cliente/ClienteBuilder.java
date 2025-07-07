package Programa.Visao.Screens.Cliente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

import Programa.Modelo.Cliente;
import Programa.Visao.Builder.Builder;
import Programa.Visao.Builder.BuilderValidationException;

public class ClienteBuilder extends Builder<Cliente> {

  private Integer id;
  private String Nome;
  private String CPF;
  private String Email;
  private String CEP;
  private Date DataNascimento;

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
  private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{5}-?\\d{3}$");
  private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$");

  public ClienteBuilder() {
  }

  @Override
  public Cliente build() throws BuilderValidationException {
    validate();
    if (!exceptionMap.isEmpty()) throw new BuilderValidationException(exceptionMap);
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

  public ClienteBuilder withNome(String Nome) {
    this.Nome = Nome;
    return this;
  }

  public ClienteBuilder withCPF(String CPF) {
    this.CPF = CPF;
    return this;
  }

  public ClienteBuilder withCEP(String CEP) {
    this.CEP = CEP;
    return this;
  }

  public ClienteBuilder withDataNascimento(Date DataNascimento) {
    this.DataNascimento = DataNascimento;
    return this;
  }

  public ClienteBuilder withEmail(String Email) {
    this.Email = Email;
    return this;
  }

  @Override
  public void validate() {
    exceptionMap.clear();
    
    if (Nome == null || Nome.trim().isEmpty()) {
      addException("Nome", "Nome não pode ser vazio");
    }

    if (CPF == null || CPF.replace(".", "").replace("-", "").replace("_", "").trim().isEmpty()) {
      addException("CPF", "CPF não pode ser vazio");
    } else if (!CPF_PATTERN.matcher(CPF).matches()) {
      addException("CPF", "CPF inválido");
    }

    if (Email == null || Email.trim().isEmpty()) {
      addException("Email", "Email não pode ser vazio");
    } else if (!EMAIL_PATTERN.matcher(Email).matches()) {
      addException("Email", "Email inválido");
    }

    if (CEP == null || CEP.replace("-", "").replace("_", "").trim().isEmpty()) {
      addException("CEP", "CEP não pode ser vazio");
    } else if (!CEP_PATTERN.matcher(CEP).matches()) {
      addException("CEP", "CEP inválido");
    }

    if (DataNascimento == null) {
      addException("DataNascimento", "Data de nascimento não pode ser vazia");
    } else if (DataNascimento.toLocalDate().isAfter(LocalDate.now())) {
      addException("DataNascimento", "Data de nascimento deve ser menor que hoje");
    }

  }
}