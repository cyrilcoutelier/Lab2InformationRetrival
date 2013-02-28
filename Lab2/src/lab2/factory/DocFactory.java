/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.factory;

import java.util.TreeMap;
import lab2.document.Document;

/**
 *
 * @author Cyril
 */
public class DocFactory {
  public Document createDoc(String classLabel)
  {
    return new Document(classLabel, new TreeMap<String, Integer>());
  }
}
