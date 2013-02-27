/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.writer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import lab2.arff.AttributeType;
import lab2.arff.Section;
import lab2.data.HeaderData;
import lab2.document.DocumentStatistics;
import lab2.document.WordCount;

/**
 *
 * @author Cyril
 */
public class ArffWriter {

  private PrintStream ps;
  private HeaderData headerData;
  ArrayList<ArrayList<WordCount>> documents;
  private String relationName;

  public ArffWriter(PrintStream ps, HeaderData headerData, ArrayList<ArrayList<WordCount>> documents, String relationName) {
    this.ps = ps;
    this.headerData = headerData;
    this.documents = documents;
    this.relationName = relationName;
  }

  public void write() {
    writeRelation();
    writeHeader();
    writeData();
  }

  private void writeRelation() {
    this.ps.println(Section.RELATION + " " + this.relationName);
  }

  private void writeHeader() {
    List<String> terms = this.headerData.getTerms();

    for (String term : terms) {
      StringBuilder sb = new StringBuilder();
      sb.append(Section.ATTRIBUTE).append(" ").append(term);
      sb.append("\t").append(AttributeType.NUMERIC.getText());
      this.ps.println(sb.toString());
    }
  }

  private void writeData() {
    this.ps.println(Section.DATA);

    for (ArrayList<WordCount> document : this.documents) {
      writeDocument(document);
    }
  }

  private void writeDocument(ArrayList<WordCount> document) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0; i < document.size(); i++) {
      if (this.isNotFirst(i)) {
        sb.append(", ");
      }

      WordCount word = document.get(i);
      writeValue(word, sb);
    }
    sb.append("}");
    this.ps.println(sb.toString());
  }

  private boolean isNotFirst(int idx) {
    return idx > 0;
  }

  private void writeValue(WordCount word, StringBuilder sb) {
    int wordCount = word.count;
    int headerIdx = this.headerData.getTermIndex(word.word);

    sb.append(headerIdx).append(" ").append(wordCount);
  }
}
