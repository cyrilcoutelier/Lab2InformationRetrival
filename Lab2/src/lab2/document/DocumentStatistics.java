package lab2.document;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;


/**
 *
 * @author Gang
 */
public class DocumentStatistics {
    private ArrayList<WordCount> document;
    private ArrayList<ArrayList<WordCount>> documentStatistics;
    
    public ArrayList<ArrayList<WordCount>> getDocumentStatistics() {
        return documentStatistics;
    }
}
