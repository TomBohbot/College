package edu.yu.da;

/** 
 * Implement a dynamic programming solution for minimizing
 * a "word wrap penalty" function for a sequence of text.  
 * 
 * @author Avraham Leff- skeleton code
 * @author Tom Bohbot - implementation
 */

import java.util.*;

public class DPWordWrapping {

    private String [] words;
    private int lineLength;
    private HashMap <Integer, List<String>> map;
    private int [] indicesForMap;

    /** 
     * No-argument constructor.
     *
     * @param words a non-null, non-empty, sequence of words
     * @param lineLength a positive value that defines the line length for all
     * lines in the computed display
     * @see requirements doc for details
     */
    public DPWordWrapping(final String[] words, final int lineLength) {
        if (words == null || lineLength < 0) {
            throw new IllegalArgumentException("Illegal.");
        }
        this.words = new String [words.length+1];
        for (int i = 1; i < this.words.length; i ++) {
            this.words[i] = words[i-1];
        }
        this.lineLength = lineLength;
        this.map = new HashMap <>();
        this.indicesForMap = new int [this.words.length]; 
    }


    /** 
     * Using the rules and constraints defined in the requirements document,
     * return the value of the optimal total penalty for the specified sequence
     * of words and constant line length parameter supplied to the constructor.
     *
     * @return the optimal total penalty to layout the words
     */
    public int minimumPenalty() {
        int [] layout = new int [this.words.length];
        int [] potCost = new int [this.words.length];
        indicesForMap  = new int [this.words.length];
        for (int i = 1; i < this.words.length; i ++ ) {
            layout[i] = this.words[i].length();
            if (layout[i] > this.lineLength) {
                return Integer.MAX_VALUE;
            }
        }
        for (int i = layout.length - 1; i > 0; i --) {
            int charsOnLine = 0;
            int penalty = 0;
            potCost[i] = Integer.MAX_VALUE;
            for (int j = i; j < this.words.length && charsOnLine <= this.lineLength; j ++) {
                charsOnLine += i == j ? layout[j] : layout[j] + 1;
                if (charsOnLine <= this.lineLength) {
                    int remChars = this.lineLength - charsOnLine;
                    penalty = j == this.words.length-1 ? remChars*remChars : remChars*remChars + potCost[j+1];
                    indicesForMap[i] = penalty < potCost[i] ? j : indicesForMap[i];
                    potCost[i] = penalty < potCost[i] ? penalty : potCost[i];
                }
            }
        }
        return potCost[1];
    }

    /** 
     * Returns the optimal layout for the specified sequence of words and
     * constant line parameter supplied to the constructor.  If no optimal total
     * penalty value can be computed per the requirements doc, the layout is undefined.
     *
     * @return layout A Map which associates with each line in the layout
     * (0..n-1), a sequence of words that conforms to the rules specified in the
     * requirements doc.
     */
    public Map<Integer, List<String>> getLayout() {
        int lineNumber = 0;
        for (int i = 1; i < indicesForMap.length; i ++) {
            int chars = 0;
            ArrayList <String> list = new ArrayList<>();
            for (int j = indicesForMap[i-1]+1; j <= indicesForMap[i] && chars <= this.lineLength; j ++) {
                list.add(this.words[j]);
                chars += this.words[j].length();
            }
            if (list.size() != 0) {
                this.map.put(lineNumber, list);
                lineNumber += 1;
            }
        }
        return this.map;
    }
}
