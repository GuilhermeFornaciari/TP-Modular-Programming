package Programa.Modelo;
public class TipoTransacao extends Entidade{
  TipoDespesaReceita tipo;
  String descricao;
  public TipoTransacao(Integer id,TipoDespesaReceita tipo,String descricao){
    super(id);
    this.tipo = tipo;
    this.descricao = descricao;
  }
  public TipoDespesaReceita getTipo() {
      return tipo;
  }

  public void setTipo(TipoDespesaReceita tipo) {
      this.tipo = tipo;
  }
  
  public String getDescricao() {
      return descricao;
  }

  public void setDescricao(String descricao) {
      this.descricao = descricao;
  }

}


