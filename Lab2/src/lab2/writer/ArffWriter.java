/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.writer;

import document.DocumentStatistics;
import document.WordCount;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import lab2.HeaderData;
import lab2.arff.AttributeType;
import lab2.arff.Section;

/**
 *
 * @author Cyril
 */
public class ArffWriter {

  private PrintStream ps;
  private HeaderData headerData;
  private DocumentStatistics documentStatistics;
  private String relationName;

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

    ArrayList<ArrayList<WordCount>> documents = documentStatistics.getDocumentStatistics();
    for (ArrayList<WordCount> words : documents) {
      writeDocument(words);
    }
  }

  private void writeDocument(ArrayList<WordCount> words) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0; i < words.size(); i++) {      
      if (this.isNotFirst(i)) {
        sb.append(", ");
      }

      WordCount word = words.get(i);
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
