/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cyril
 */
public class HeaderDataMap implements HeaderData {

  private Map<String, Integer> termsMap = new HashMap<>();
  int currentIdx = 0;

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
    this.termsMap.put(term, this.currentIdx);
    this.currentIdx++;
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
  }

  @Override
  public List<String> getTerms() {
    List<String> termList = new ArrayList<>();
    
    for (String term : this.termsMap.keySet()) {
      termList.add(term);
    }

    return termList;
  }
}
