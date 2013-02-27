/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.test;

import java.util.ArrayList;
import lab2.document.DocumentStatistics;
import lab2.document.WordCount;

/**
 *
 * @author Cyril
 */
public class TestDocumentStatistics {

  public static void main(String[] args) {
    String usage = "java org.apache.lucene.demo.IndexFiles"
            + " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n"
            + "This indexes the documents in DOCS_PATH, creating a Lucene index"
            + "in INDEX_PATH that can be searched with SearchFiles";
    String indexPath = "index";
    String docsPath = null;
    boolean create = true;
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-index":
          indexPath = args[i + 1];
          i++;
          break;
        case "-docs":
          docsPath = args[i + 1];
          i++;
          break;
        case "-update":
          create = false;
          break;
      }
    }
    /*
     if (docsPath == null) {
     System.err.println("Usage: " + usage);
     System.exit(1);
     }

     */
    //
    //
    //

    DocumentStatistics statistics = new DocumentStatistics(indexPath);
    statistics.classifyFiles();
    /*    ArrayList<ArrayList<WordCount>> documentStatistics = statistics.getDocumentStatistics();
     Iterator<ArrayList<WordCount>> docsIt = documentStatistics.iterator();
     while (docsIt.hasNext()) {
     Iterator<WordCount> wordIt = docsIt.next().iterator();
     while (wordIt.hasNext()) {
     wordIt.next().showInfo();
     }
     }*/
    ArrayList<WordCount> collection = statistics.getCollectionStatistics();
    /*   Iterator<WordCount> it = collection.iterator();
     while (it.hasNext()) {
     it.next().showInfo();
     }*/
    System.out.println(collection.size());
  }
}
