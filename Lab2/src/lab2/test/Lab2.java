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
public class Lab2 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String usage = "lab2.test.Lab2"
            + " INDEX_PATH ARFF_PATH\n\n"
            + "This create an arff file with the given index";
    String indexPath = "";
    
    
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
    
    

    // TODO code application logic here
  }
}
