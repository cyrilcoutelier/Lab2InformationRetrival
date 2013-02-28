/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.prefixtree;

import java.util.ArrayList;
import java.util.Stack;
import lab2.document.WordCount;

/**
 *
 * @author Gang
 */
public class Node {

  private Node[] children; //a pointer to the next node
  private int value;
  private static String infoString = "";
  private static ArrayList<WordCount> wordCounts;

  Stack stack;
  
  public Node() {
    children = new Node[26];
    value = 0;
    wordCounts = new ArrayList<>();
  }

  public void addWord(String word) {
    char[] chars = word.toCharArray();
    Node pointer = this;
    for (int i = 0; i < chars.length; ++i) {
      if (i == chars.length - 1) {
        pointer.value++;
      }
      if (pointer.children[chars[i] - 97] == null) {
        pointer.children[chars[i] - 97] = new Node();
      }
      pointer = pointer.children[chars[i] - 97];
    }
  }

  public ArrayList<WordCount> getWordCounts() {
     
      
      
      
      return null;
  }
  
  public boolean hasValue() {
      for (int i=0;i<26;++i) {
          if (children != null) {
              return true;
          }
      } 
      return false;
  }

  public void caculateWordCount() {
      if (this.hasValue()) {
          if (this.value!=0) {
              wordCounts.add(new WordCount(infoString,value));
          }
      }
  }

  /*  
   *  recursive printInfo for the tree 
   *  very hard to understand and comment
   */
  public void printInfo() {
    boolean end = true;
    for (int i = 0; i < 26; ++i) {
      if (children[i] != null) {
        end = false;
        infoString += (char) (i + 'a');
        if (this.value != 0) {
          System.out.println("the string is : " + infoString + " value : " + value);
        }
        children[i].printInfo();
      }
    }
    if (end == true) {
      infoString = "";
    }
  }
}
