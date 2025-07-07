package Programa.Visao.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Programa.Modelo.Entidade;

public abstract class Builder<T extends Entidade> {
  protected Map<String, ArrayList<String>> exceptionMap = new HashMap<>();
  public abstract T build() throws BuilderValidationException;
  protected abstract void validate();
  protected void addException(String key, String value) {
    if (exceptionMap.get(key) == null) {
      ArrayList<String> exceptions = new ArrayList<>();
      exceptions.add(value);
      exceptionMap.put(key, exceptions);
    } else {
      exceptionMap.get(key).add(value);
    }
  }
}
