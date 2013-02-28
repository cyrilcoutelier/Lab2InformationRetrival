/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.document;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import lab2.data.DocsData;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;

/**
 *
 * @author Cyril
 */
public class IndexParser {

  IndexReader indexReader;
  DocsData docsData;
  String fieldName;
  String pathFieldName;
  Set<String> pathFieldSet;

  public IndexParser(IndexReader indexReader, DocsData docsData, String fieldName) {
    this.indexReader = indexReader;
    this.docsData = docsData;
    this.fieldName = fieldName;
    pathFieldSet = new TreeSet<>();
    
    this.pathFieldSet.add(pathFieldName);
  }

  public void parse() throws IOException {
    int maxDocs = this.indexReader.maxDoc();
    for (int i = 0; i < maxDocs; ++i) {
      String path = this.indexReader.document(i, pathFieldSet).get(pathFieldName);
      String[] pathElems = path.split("'\\'");
      String className = pathElems[pathElems.length - 1];
      
      this.indexReader.document(i, pathFieldSet);
      this.docsData.startDoc();

      Terms termVector = this.indexReader.getTermVector(i, fieldName);
      TermsEnum termsEnum = termVector.iterator(TermsEnum.EMPTY);
      while (termsEnum.next() != null) {
        this.docsData.addTerm(termsEnum.term().utf8ToString());
      }

      this.docsData.endDoc();
    }
  }
}
