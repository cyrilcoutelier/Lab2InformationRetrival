/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import lab2.data.DocsData;
import lab2.data.DocsDataArray;
import lab2.data.DocsDataGlobal;
import lab2.data.HeaderData;
import lab2.data.HeaderDataMap;
import lab2.document.Document;
import lab2.document.IndexParser;
import lab2.factory.DocFactory;
import lab2.writer.ArffWriter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Cyril
 */
public class Lab2 {

  static final private Logger log = Logger.getLogger(Lab2.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException, IOException {
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
    String contentFieldName = "contents";
    String pathFieldName = "path";

    try (PrintStream ps = new PrintStream(arffPath);
            IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexPath)))) {
      DocFactory docFactory = new DocFactory();
      DocsDataArray docsData = new DocsDataArray(new ArrayList<Document>(), docFactory);
      HeaderData headerData = new HeaderDataMap(new TreeMap<String, Integer>());
      DocsData globalData = new DocsDataGlobal(docsData, headerData);
      IndexParser indexParser = new IndexParser(indexReader, globalData, contentFieldName, new TreeSet<String>());

      indexParser.parse();
      headerData.computeIdx();

      List<Document> docs = docsData.getDocs();
      ArffWriter arffWriter = new ArffWriter(ps, headerData, docs, relationName);

      arffWriter.write();
    }
  }
}
