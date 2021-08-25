package edu.yu.da;

/**
 * Computer Virus Detection
 * @author Dr. Leff   - Skeleton Code
 * @author Tom Bohbot - implementation
 * @version February 18, 2021 
 */

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerVirusDetection {

    public final static int NO_VIRUS_QUALIFIES = -1;

    // Implement this interface any way that you like: I will test
    // your code with my implementation
    public interface VirusChecker {
        /** Returns true iff one Virus is "equal" to another Virus
         */
        boolean areEqual(final Virus virus1, final Virus virus2);
    }

    // You may not change this inner class in any way!
    public static class Virus {
        /** Constructor
         *
         * @param code a String containing the code for the virus
         */
        public Virus(final String code, final VirusChecker virusChecker) {
            Objects.requireNonNull(code, "code can't be null");
            if (code.isEmpty()) {
                throw new IllegalArgumentException("Empty 'code' parameter");
            }
            Objects.requireNonNull(virusChecker, "Virus checker can't be null");

            this.code = code;
            this.virusChecker = virusChecker;
        }

        public String getCode() { 
            return code; 
        }

        @Override
        public String toString() {
            return "Virus[code="+code+"]";
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this) { 
                return true; 
            } 
        
            if (!(o instanceof Virus)) { 
                return false; 
            }

            final Virus that = (Virus) o;
            return virusChecker.areEqual(this, that);      
        }

    @Override
    public int hashCode() {
      // Every virus has its own hashCode value, regardless of whether or not
      // it's equivalent to another
      return System.identityHashCode(code);
    }

    private final String code;
    private final VirusChecker virusChecker;
  }

  /** Returns the index of any virus instance that is a member of the "most
   * prevalent virus class" iff one exists, NO_VIRUS_QUALIFIES if none
   * qualifies.
   *
   * @param viruses Array of viruses to be evaluated
   * @param checker Implements the VirusChecker interface
   * @return index of a virus that, with respect to the specified VirusChecker,
   * determines a "most prevalent virus" equivalence class; or
   * NO_VIRUS_QUALIFIES if no virus set in the input qualifies as a "most
   * prevalent virus".
   *
   * Note: assuming the a "most prevalent virus" exists, multiple indices
   * typically qualify as the return value.
   */
  public static int mostPrevalent(final Virus[] viruses, final VirusChecker checker) {
    if (viruses == null || checker == null) {
        throw new IllegalArgumentException("Cannot have null param(s).");
    }
    if (viruses.length == 0) {
        return -1;
    }
    return recursiveHelper(viruses, checker, 0, viruses.length - 1);
  }

  private static int recursiveHelper(Virus [] viruses, VirusChecker checker, int start, int end ) {
    // Recursively split the array until there is only on element per "array" so that each element is it's dominant pair:
    if (start == end) {
        return start;
    }
    else {
        int firstHalfFrequent = recursiveHelper(viruses, checker, start, (start + end)/2);
        int secondHalfFrequent= recursiveHelper(viruses, checker, ((start+end)/2)+1, end);
        // Avoid Null Pointers for calculating prevalent virus:
        if (firstHalfFrequent == -1 && secondHalfFrequent == -1) {
            return NO_VIRUS_QUALIFIES;
        }
        else if (firstHalfFrequent == -1) {
            firstHalfFrequent = secondHalfFrequent;
        }
        else if (secondHalfFrequent == -1) {
            secondHalfFrequent = firstHalfFrequent;
        }
        // calculate most prevalent virus:
        int firstHalfCounter = 0;
        int secondHalfCounter = 0;
        int target = ((end-start+1)/2)+1;
        for (int i = start; i <= end; i ++) {
            // check if any half occurs most frequently:
            if (viruses[firstHalfFrequent].equals(viruses[i]) ){
                firstHalfCounter += 1;
            }
            if (viruses[secondHalfFrequent].equals(viruses[i]) ){
                secondHalfCounter += 1;
            }
        }
        if (firstHalfCounter >= target) {
            return firstHalfFrequent;
        }
        if (secondHalfCounter >= target) {
            return secondHalfFrequent;
        }
        return -1;
    }
  }

  private final static Logger logger = LogManager.getLogger(ComputerVirusDetection.class);
}