/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.factory;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Cyril
 */
public class DocFactory {
  public Map<String, Integer> createDoc()
  {
    return new TreeMap<>();
  }
}
