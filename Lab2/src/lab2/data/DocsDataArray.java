/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lab2.document.Document;
import lab2.factory.DocFactory;
import lab2.transition.Converter;

/**
 *
 * @author Cyril
 */
public class DocsDataArray implements DocsData {

  Document currentDoc = null;
  List<Document> docs;
  DocFactory docFactory;

  public DocsDataArray(List<Map<String, Integer>> docs, DocFactory docFactory) {
    this.docs = Converter.mapsToDocs(docs);
    this.docFactory = docFactory;
  }

  @Override
  public void startDoc() {
    this.currentDoc = new Document(null, new TreeMap<String, Integer>());
    this.docs.add(currentDoc);
  }

  @Override
  public void endDoc() {
  }

  @Override
  public void addTerm(String term) {
    if (!this.existDoc(term)) {
      this.currentDoc.getTerms().put(term, 1);
    } else {
      incTermCount(term);
    }
  }

  private void incTermCount(String term) {
    int termCount = this.currentDoc.getTerms().get(term);
    termCount++;
    this.currentDoc.getTerms().put(term, termCount);
  }

  private boolean existDoc(String term) {
    return this.currentDoc.getTerms().containsKey(term);
  }

  public List<Map<String, Integer>> getDocs() {
    return Converter.docsToMaps(docs);
  }
}
