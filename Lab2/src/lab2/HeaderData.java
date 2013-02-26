/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.List;

/**
 *
 * @author Cyril
 */
public interface HeaderData {
  void tryRegisterTerm(String term);

  int getTermIndex(String term);
  
  List<String> getTerms();
}
