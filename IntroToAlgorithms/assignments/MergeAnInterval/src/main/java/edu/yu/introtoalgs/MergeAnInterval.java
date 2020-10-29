package edu.yu.introtoalgs;
import java.util.*;

public class MergeAnInterval {

  /** An immutable class, holds a left and right integer-valued pair that
   * defines a closed interval
   */
  public static class Interval implements Comparable<Interval>{
    public final int left;
    public final int right;

    /** Constructor
     * 
     * @param left the left endpoint of the interval
     * @param right the right endpoint of the interval
     */
    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public int compareTo(Interval o) {
      // TODO Auto-generated method stub
      return 0;
    }

    // fill in the rest yourself!
  } // Interval class

  /** Merges the new interval into an existing set of disjoint intervals.
   *
   * @param intervals the existing set of intervals
   * @param newInterval the interval to be added
   * @return a new set of disjoint intervals containing the original intervals
   * and the new interval, merging the new interval if necessary into existing
   * interval(s), to preseve the "disjointedness" property.
   */

  private static HashSet <Interval> iterateThrough = new HashSet <Interval>();; // imagine this assignment as the calendar example.


  public static Set<Interval> merge (final Set<Interval> intervals, Interval newInterval) {
    boolean hasMerged = false;
    HashSet <Interval> calendar = (HashSet<Interval>) intervals; // imagine this assignment as the calendar example.
    // This is to avoid a compile time issue:
    for (Interval interval : intervals) {
      iterateThrough.add(interval);
    }
    if (intervals.size() == 0) {
      calendar.add(newInterval);
      return calendar;
    }
    for (Interval interval : iterateThrough) {
      int beginning = interval.left;
      int end = interval.right;
      // the case that an interval is a subset of another no changes need to be made:
      if (newInterval.left >= beginning && newInterval.right <= end) {
        return intervals;
      }
      // If Right end is included in interval:
      if (newInterval.right >= beginning && newInterval.right <= end) {
        if (newInterval.left >= beginning) {
          newInterval = interval;
        }
        // a merge must take place:
        if (newInterval.left < beginning) {
          calendar.remove(interval);
          if (hasMerged == true) {
            calendar.remove(newInterval); // testing w this.
          }
          hasMerged = true;
          Interval updateLeftSide = new Interval(newInterval.left , end);
          calendar.add(updateLeftSide);
          newInterval = updateLeftSide;
        }
      }
      // If left end is included in interval:
      if (newInterval.left >= beginning && newInterval.left <= end) {
        if (newInterval.right <= end) {
          newInterval = interval;
          // return intervals;
        }
        if (newInterval.right > end) {
          calendar.remove(interval);
          if (hasMerged == true) {
            calendar.remove(newInterval); // testing w this.
          }
          hasMerged = true;
          Interval updateRightSide = new Interval(beginning , newInterval.right);
          calendar.add(updateRightSide);
          newInterval = updateRightSide;
        }
      }
      // If new Interval swallows old interval:
      if (newInterval.left <= beginning && newInterval.right >= end) {
        calendar.remove(interval);
        calendar.add(newInterval);
        hasMerged = true;
      }
    }
    // printOut(calendar);
    return calendar;
  }

  // private static void printOut (HashSet <MergeAnInterval.Interval> calendar) {
  //   for (Interval interval : calendar) {
  //     System.out.println("This is left Interval " + interval.left);
  //     System.out.println("This is right Interval " + interval.right);
  //     System.out.println(" ");
  //   }
  // }
}
