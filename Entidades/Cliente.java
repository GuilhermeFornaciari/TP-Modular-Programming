package Entidades;

import Interfaces.Entidade;
import java.sql.Date;

public class Cliente extends Entidade {
    private String Nome;
    private String CPF;
    private String Email;
    private String CEP;
    private Date DataNascimento;

    public Cliente(String nome, String cpf, String email, String cep, Date data_nascimento) {
        this.Nome = nome;
        this.CPF = cpf;
        this.Email = email;
        this.CEP = cep;
        this.DataNascimento = data_nascimento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cpf) {
        CPF = cpf;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String cep) {
        CEP = cep;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n" +
        "Nome: " + this.getNome() + "\n" +
        "CPF: " + this.getCPF() + "\n" +
        "Email: " + this.getEmail() + "\n" +
        "CEP: " + this.getCEP() + "\n" +
        "Data de Nascimento: " + this.getDataNascimento() + "\n" +
        "--------------------------------------------------";

    }
}
