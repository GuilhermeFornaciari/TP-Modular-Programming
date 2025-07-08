package Programa.Modelo;
import java.sql.Date;

public class ItemMovimento extends Entidade{
    private TipoTransacao tipo;
    private Date dataCriacao;
    private Date dataPagamento;
    private String descricao;
    private float valor;
    public ItemMovimento(Integer Id, Date dataPagamento, String descricao, TipoTransacao tipo, float valor) {
      super(Id);
      this.tipo = tipo;
      this.dataPagamento = dataPagamento;
      this.descricao = descricao;
      this.valor = valor;
    }

    public ItemMovimento(Integer Id, Date dataCriacao, Date dataPagamento, String descricao, TipoTransacao tipo, float valor) {
      super(Id);
      this.tipo = tipo;
      this.dataCriacao = dataCriacao;
      this.dataPagamento = dataPagamento;
      this.descricao = descricao;
      this.valor = valor;
    }

  public Float getValor() {
      return valor;
  }

  public void setValor(Float valor) {
      this.valor = valor;
  }

  public void setTransacao(TipoTransacao tipo){
    this.tipo = tipo;
  }
  public TipoTransacao getTransacao() { return tipo; }

  public void setDataPagamento(Date dataPagamento){
    this.dataPagamento = dataPagamento;
  }
  public Date getDataPagamento() { return dataPagamento; }
  
  public void setDataCriacao(Date dataCriacao){
    this.dataCriacao = dataCriacao;
  }
  public Date getDataCriacao() { return dataCriacao; }
  
  public String getDescricao() {
      return descricao;
  }

  public void setDescricao(String descricao) {
      this.descricao = descricao;
  }


    @Override
    public String toString() {
        return "\n{" + super.toString() + 
        "Data da criação: " + this.getDataCriacao() + "\n" +
        "Data do pagamento: " + this.getDataPagamento() + "\n" +
        "Descrição: " + this.getDescricao() + "\n" +
        "Tipo da transação: " + tipo +
        "Valor: " + this.getValor() + "\n" +
        "}";
    }
    

}
