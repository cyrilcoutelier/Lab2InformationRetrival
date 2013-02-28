/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

/**
 *
 * @author Cyril
 */
public interface DocsData {
  void startDoc(String className);
  void endDoc();
  void addTerm(String term);
}
