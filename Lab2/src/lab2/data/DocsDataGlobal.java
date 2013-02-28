/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.data;

/**
 *
 * @author Cyril
 */
public class DocsDataGlobal implements DocsData {

  DocsData docsData;
  HeaderData headerData;

  public DocsDataGlobal(DocsData docsData, HeaderData headerData) {
    this.docsData = docsData;
    this.headerData = headerData;
  }

  @Override
  public void startDoc(String className) {
    this.docsData.startDoc(className);
    this.headerData.addClassName(className);
  }

  @Override
  public void endDoc() {
    this.docsData.endDoc();
  }

  @Override
  public void addTerm(String term) {
    this.docsData.addTerm(term);
    this.headerData.tryRegisterTerm(term);
  }
}
