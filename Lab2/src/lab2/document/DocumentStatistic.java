/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.data.HeaderDataMap;
import lab2.prefixtree.Node;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Gang
 */
public class DocumentStatistic {
    private ArrayList<ArrayList<WordCount>> documentStatistics;
    private ArrayList<WordCount> collectionStatistics;
    
    
    IndexReader indexReader;
    public DocumentStatistic(String indexPath) {
        documentStatistics = new ArrayList<>();
        collectionStatistics = new ArrayList<>();
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

    public void classifyFiles() {
        try {
            int maxDocs = indexReader.maxDoc();
            for (int i = 0; i < maxDocs; ++i) {
                Terms termVector = indexReader.getTermVector(i, "contents");
                TermsEnum termsEnum = termVector.iterator(TermsEnum.EMPTY);

                ArrayList<WordCount> tmp = new ArrayList<>();
                        
                while (termsEnum.next() != null) {
                    //System.out.println(termsEnum.term().utf8ToString());
                    String term = termsEnum.term().utf8ToString();
                    add(collectionStatistics,term);
                    add(tmp,term);
                }
                // wordCounts.get(0).showInfo();
                //  wordCounts.get(0).showInfo();
                documentStatistics.add(tmp);
                //  System.out.println("another doc");
            }

        } catch (IOException ex) {
            Logger.getLogger(DocumentStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void add(ArrayList<WordCount> array, String word) {
        Iterator<WordCount> it = array.iterator();
        while (it.hasNext()) {
            if (it.next().word.equals(word))  {
                it.next().count++;
                return;
            }
        }
        WordCount wc = new WordCount(word,1);
        array.add(wc);
        
    }
}
