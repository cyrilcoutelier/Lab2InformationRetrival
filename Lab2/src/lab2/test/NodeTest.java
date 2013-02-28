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
        node.addWord("a");
        node.addWord("al");
        node.addWord("aall");
        //node.caculateWordCount();
        //ArrayList<WordCount> wordsInfo = node.getWordCounts(node);

        //Iterator<WordCount> it = wordsInfo.iterator();

        //  while (it.hasNext()) {
        ///       it.next().showInfo();
        //   }
        // node.printInfo();

    }
    // node.printInfo();
}

