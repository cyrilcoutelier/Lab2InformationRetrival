/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

import java.util.List;
import java.util.Map;
import lab2.factory.DocFactory;

/**
 *
 * @author Cyril
 */
public class DocsDataArray implements DocsData {

  Map<String, Integer> currentDoc;
  List<Map<String, Integer>> docs;
  DocFactory docFactory;

  public DocsDataArray(Map<String, Integer> currentDoc, List<Map<String, Integer>> docs, DocFactory docFactory) {
    this.currentDoc = currentDoc;
    this.docs = docs;
    this.docFactory = docFactory;
  }

  @Override
  public void startDoc() {
    this.currentDoc = this.docFactory.createDoc();
    this.docs.add(currentDoc);
  }

  @Override
  public void endDoc() {
  }

  @Override
  public void addTerm(String term) {
    if (!this.existDoc(term)) {
      this.currentDoc.put(term, 1);
    } else {
      incTermCount(term);
    }
  }

  private void incTermCount(String term) {
    int termCount = this.currentDoc.get(term);
    termCount++;
    this.currentDoc.put(term, termCount);
  }

  private boolean existDoc(String term) {
    return this.currentDoc.containsKey(term);
  }

  private List<Map<String, Integer>> getDocs() {
    return this.docs;
  }
}
