/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.test;

import lab2.prefixtree.Node;
import java.util.ArrayList;
import java.util.Iterator;
import lab2.document.WordCount;

/**
 *
 * @author Cyril
 */
public class NodeTest {

  public static void main(String[] args) {
    // System.out.println("the value of a is : " + (int)'a');
    // System.out.print((char)(2+'a'));
    Node node = new Node();
    node.addWord("test");
    node.addWord("test");
    node.addWord("test");
    ///   node.addWord("test");
    node.addWord("testb");
    node.addWord("abc");
    node.calculateWordCount();
    ArrayList<WordCount> wordsInfo = node.getWordCounts();

    Iterator<WordCount> it = wordsInfo.iterator();

    while (it.hasNext()) {
      it.next().showInfo();
    }
    // node.printInfo();

  }
}
