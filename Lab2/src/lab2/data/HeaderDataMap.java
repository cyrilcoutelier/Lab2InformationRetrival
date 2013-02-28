/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Cyril
 */
public class HeaderDataMap implements HeaderData {

  private Set<String> classNames;
  private Map<String, Integer> termsMap;
  boolean idxValid = false;

  public HeaderDataMap(Map<String, Integer> termsMap) {
    this.termsMap = termsMap;
  }

  @Override
  public void tryRegisterTerm(String term) {
    if (this.notRegistred(term)) {
      this.registerTerm(term);
    }
  }

  private boolean notRegistred(String term) {
    return !this.termsMap.containsKey(term);
  }

  private void registerTerm(String term) {
    this.termsMap.put(term, null);
    this.idxValid = false;
  }

  @Override
  public int getTermIndex(String term) {
    if (notRegistred(term)) {
      String msg = "the term " + term + " is not registred";
      throw new RuntimeException(msg);
    } else {
      return this.termsMap.get(term);
    }
  }

  @Override
  public void computeIdx() {
    int idx = 0;

    for (Map.Entry<String, Integer> entry : termsMap.entrySet()) {
      entry.setValue(idx);
      idx++;
    }
    this.idxValid = true;
  }

  @Override
  public List<String> getTerms() {
    List<String> termList = new ArrayList<>();

    for (String term : this.termsMap.keySet()) {
      termList.add(term);
    }

    return termList;
  }

  @Override
  public boolean isIdxValid() {
    return this.idxValid;
  }

  @Override
  public void addClassName(String className) {
    this.classNames.add(className);
  }

  @Override
  public Set<String> getClassNames() {
    return this.classNames;
  }
}
