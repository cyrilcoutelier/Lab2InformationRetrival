/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Logger;
import lab2.data.HeaderData;
import lab2.data.HeaderDataMap;
import lab2.document.DocumentStatistics;
import lab2.document.WordCount;
import lab2.writer.ArffWriter;

/**
 *
 * @author Cyril
 */
public class Lab2 {

  static final private Logger log = Logger.getLogger(Lab2.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {
    String usage = "java lab2.test.Lab2"
            + " INDEX_PATH ARFF_PATH RELATION_NAME\n\n"
            + "This create an arff file with the given index";

    if (args.length < 3) {
      log.severe(usage);
      System.exit(1);
    }

    String indexPath = args[0];
    String arffPath = args[1];
    String relationName = args[2];

    try (PrintStream ps = new PrintStream(arffPath)) {
      DocumentStatistics docStats = new DocumentStatistics(indexPath);
      docStats.classifyFiles();
      HeaderData headerData = createHeaderData(docStats.getCollectionStatistics());
      ArffWriter arffWriter = new ArffWriter(ps, headerData, docStats.getDocumentStatistics(), relationName);

      arffWriter.write();
    }
  }

  static private HeaderData createHeaderData(ArrayList<WordCount> collectionStatistics) {
    HeaderData headerData = new HeaderDataMap(new TreeMap<String, Integer>());

    for (WordCount wordCount : collectionStatistics) {
      headerData.tryRegisterTerm(wordCount.word);
    }

    log.info("generating term indexes");
    headerData.computeIdx();
    log.info("DONE");

    return headerData;
  }
}
