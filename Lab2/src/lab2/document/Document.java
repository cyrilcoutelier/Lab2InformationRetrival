/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.document;

import java.util.Map;

/**
 *
 * @author Cyril
 */
public class Document {

  private String path;
  private Map<String, Integer> terms;

  public Document(String path, Map<String, Integer> terms) {
    this.path = path;
    this.terms = terms;
  }

  public String getPath() {
    return path;
  }

  public Map<String, Integer> getTerms() {
    return terms;
  }
}
