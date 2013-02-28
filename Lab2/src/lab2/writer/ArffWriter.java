/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.writer;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lab2.arff.AttributeType;
import lab2.arff.Section;
import lab2.data.HeaderData;
import lab2.document.Document;

/**
 *
 * @author Cyril
 */
public class ArffWriter {

  private PrintStream ps;
  private HeaderData headerData;
  List<Document> documents;
  private String relationName;
  private final List<String> terms;

  public ArffWriter(PrintStream ps, HeaderData headerData, List<Document> documents, String relationName) {
    this.ps = ps;
    this.headerData = headerData;
    this.documents = documents;
    this.relationName = relationName;
    this.terms = this.headerData.getTerms();
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
    for (String term : this.terms) {
      StringBuilder sb = new StringBuilder();
      sb.append(Section.ATTRIBUTE).append(" ").append(term);
      sb.append("\t").append(AttributeType.NUMERIC.getText());
      this.ps.println(sb.toString());
    }

    writeNominal();
  }

  private void writeNominal() {
    StringBuilder sb = new StringBuilder();
    sb.append(Section.ATTRIBUTE).append(" CLASS_LABEL");
    sb.append("\t{");
    boolean isFirst = true;

    for (String className : this.headerData.getClassNames()) {
      if (isFirst) {
        isFirst = false;
      } else {
        sb.append(",");
      }
      writeClassName(className, sb);
    }

    sb.append("}");
    this.ps.println(sb.toString());
  }

  private void writeData() {
    this.ps.println(Section.DATA);

    for (Document document : this.documents) {
      writeDocument(document);
    }
  }

  private void writeDocument(Document document) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    boolean isFirst = true;

    for (Map.Entry<String, Integer> entry : document.getTerms().entrySet()) {
      if (isFirst) {
        isFirst = false;
      } else {
        sb.append(", ");
      }
      writeValue(entry, sb);
    }

    sb.append(", ");
    writeDocClassName(sb, document);

    sb.append("}");
    this.ps.println(sb.toString());
  }

  private void writeValue(Entry<String, Integer> entry, StringBuilder sb) {
    int wordCount = entry.getValue();
    int headerIdx = this.headerData.getTermIndex(entry.getKey());

    sb.append(headerIdx).append(" ").append(wordCount);
  }

  private void writeDocClassName(StringBuilder sb, Document document) {
    sb.append(this.terms.size()).append(" ");
    this.writeClassName(document.getPath(), sb);
  }
  
  private void writeClassName(String className, StringBuilder sb) {
    if (className.contains(" ")) {
      sb.append("\"");
    }
    sb.append(className);
    if (className.contains(" ")) {
      sb.append("\"");
    }
  }
}
