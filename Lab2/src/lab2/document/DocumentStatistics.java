package lab2.document;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.indexing.MyAnalyser;
import lab2.test.Node;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Gang
 */
public class DocumentStatistics {

    private ArrayList<ArrayList<WordCount>> documentStatistics;
    private ArrayList<WordCount> collectionStatistics;
    Analyzer analyzer;
    IndexReader indexReader;
    Node documentPrefixTree;
    Node collectionPrefixTree;

    public DocumentStatistics(String indexPath) {
        this.analyzer = new MyAnalyser(Version.LUCENE_40);
        documentStatistics = new ArrayList<>();
        collectionStatistics = new ArrayList<>();
        collectionPrefixTree = new Node();
        try {
            indexReader = DirectoryReader.open(FSDirectory.open(new File(indexPath)));
        } catch (IOException ex) {
            Logger.getLogger(DocumentStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ArrayList<WordCount>> getDocumentStatistics() {
        return documentStatistics;
    }

    
    
    public ArrayList<WordCount> getCollectionStatistics() {
        return collectionStatistics;
    }

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

    public void classifyFiles() {
        try {
            int maxDocs = indexReader.maxDoc();
            for (int i = 0; i < maxDocs; ++i) {
                Terms termVector = indexReader.getTermVector(i, "contents");
                TermsEnum termsEnum = termVector.iterator(TermsEnum.EMPTY);

                documentPrefixTree = new Node();

                while (termsEnum.next() != null) {
                    //System.out.println(termsEnum.term().utf8ToString());
                    documentPrefixTree.addWord(termsEnum.term().utf8ToString());
                    collectionPrefixTree.addWord(termsEnum.term().utf8ToString());
                }
                ArrayList<WordCount> documentWordInfo = documentPrefixTree.getWordCounts();
                // wordCounts.get(0).showInfo();
                //  wordCounts.get(0).showInfo();
                documentStatistics.add(documentWordInfo);
                //  System.out.println("another doc");
            }
            collectionStatistics = collectionPrefixTree.getWordCounts();

        } catch (IOException ex) {
            Logger.getLogger(DocumentStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
