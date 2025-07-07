package Programa.Visao.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuilderValidationException extends Exception {
  
  Map<String, ArrayList<String>> exceptionMap = new HashMap<>();

  public BuilderValidationException(Map<String, ArrayList<String>> exceptionMap) {
    super();
    this.exceptionMap = exceptionMap;
  }

  public Map<String, ArrayList<String>> getExceptionMap() {
    return exceptionMap;
  }

}
