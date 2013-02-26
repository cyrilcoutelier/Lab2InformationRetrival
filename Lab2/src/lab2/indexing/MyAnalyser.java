/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.indexing;

import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

/**
 *
 * @author Cyril
 */
public class MyAnalyser extends Analyzer {

  public static final String[] ENGLISH_STOP_WORDS = {
    "a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your"
  };
  Version matchVersion;
  CharArraySet stopWords;

  public MyAnalyser(Version version) {
    this.matchVersion = version;
    this.stopWords = StopFilter.makeStopSet(this.matchVersion, MyAnalyser.ENGLISH_STOP_WORDS);
  }

  @Override
  protected Analyzer.TokenStreamComponents createComponents(String fieldName, Reader reader) {
    Tokenizer src = new LowerCaseTokenizer(this.matchVersion, reader);

    TokenStream result = new StopFilter(this.matchVersion, src, this.stopWords);
    result = new PorterStemFilter(result);

    Analyzer.TokenStreamComponents tsc = new Analyzer.TokenStreamComponents(src, result);

    return tsc;
  }
}
