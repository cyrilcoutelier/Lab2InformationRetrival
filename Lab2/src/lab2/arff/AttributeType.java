/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.arff;

/**
 *
 * @author Cyril
 */
public enum AttributeType {

  NUMERIC(
  "NUMERIC"),
  NOMINAL("NOMINAL");
  private String text;

  private AttributeType(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }
}
