/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.prefixtree;

/**
 *
 * @author Gang
 */
public class PrefixTree {
    private Node node;
    
    public PrefixTree() {
        node = new Node();
    }
    
    public void add(String word) {
        for (char c : word.toCharArray()) {
            //node.addChar(c);
        }
    }
}
