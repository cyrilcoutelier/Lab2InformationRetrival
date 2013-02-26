/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package document;

import java.util.ArrayList;

/**
 *
 * @author Gang
 */
public class WordCount {
    String word;
    int count;
    
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }
    
    public void showInfo() {
        System.out.println("the word is: " + word + " the count is : " + count) ;
        
    }
}
