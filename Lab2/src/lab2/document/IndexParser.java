/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.document;

import java.io.IOException;
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

  public IndexParser(IndexReader indexReader, DocsData docsData, String fieldName) {
    this.indexReader = indexReader;
    this.docsData = docsData;
    this.fieldName = fieldName;
  }

  public void parse() throws IOException {
    int maxDocs = this.indexReader.maxDoc();
    for (int i = 0; i < maxDocs; ++i) {
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
