/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lab2.document.Document;

/**
 *
 * @author Cyril
 */
public class Converter {

  static public List<Map<String, Integer>> docsToMaps(List<Document> docs) {
    List<Map<String, Integer>> maps = new ArrayList<>();

    for (Document document : docs) {
      Map<String, Integer> map = docToMap(document);
      maps.add(map);
    }
    return maps;
  }

  public static Map<String, Integer> docToMap(Document document) {
    return document.getTerms();
  }

  static public List<Document> mapsToDocs(List<Map<String, Integer>> maps) {
    List<Document> docs = new ArrayList<>();

    for (Map<String, Integer> map : maps) {
      Document doc = mapToDoc(map);
      docs.add(doc);
    }
    return docs;
  }

  private static Document mapToDoc(Map<String, Integer> map) {
    return new Document(null, map);
  }
}
