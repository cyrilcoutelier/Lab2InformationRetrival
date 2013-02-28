/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Cyril
 */
public interface HeaderData {
  void tryRegisterTerm(String term);
  
  void addClassName(String className);
  
  boolean isIdxValid();

  void computeIdx();

  int getTermIndex(String term);
  
  List<String> getTerms();
  
  Set<String> getClassNames();
  
}
