package edu.yu.da;

/** 
 * MaxRectangle assignment.
 * @author Tom Bohbot: implementation
 * @author Avraham Leff: skeleton code
 * @version March 23, 2021
 */

public class MaxRectangle {

    public static class Answer {
        final int max;
        final int left;
        final int right;
        final int height;

        /** 
         * Constructor: do not change signature, or implementation.
         */
        public Answer(final int max, final int left, final int right, final int height) {
            assert max >= 0: "max must be non-negative: "+max;
            assert left >= 0: "left must be non-negative: "+left;
            assert right >= 0: "right must be non-negative: "+right;
            assert height >= 0: "height must be non-negative: "+height;

            this.max = max;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        @Override 
        public boolean equals (Object that) {
            if (this == that) {
                return true;
            }
            if (that == null) {
                return false;
            }
            if (this.getClass() != that.getClass() ) {
                return false;
            }
            Answer thatAnswer = (Answer) that;
            if (this.max != thatAnswer.max) {
                return false;
            }
            if (this.left != thatAnswer.left) {
                return false;
            }
            if (this.right != thatAnswer.right) {
                return false;
            }
            if (this.height != thatAnswer.height) {
                return false;
            }
            return true;
        }
    
        @Override 
        public int hashCode() {
            Integer objMax = (Integer) this.max;
            return objMax.hashCode();
        }

        @Override
        public String toString() {
        return " {max:" + this.max + " , left: " + this.left + " , right: " + this.right + " , height: " + this.height + "}";
        }
    }

    /** 
     * Returns the area of the biggest rectangle that can be constructed from
     * the "heights" representation.
     *
     * @param heights an array of integers which implicitly specify one or more
     * rectangles per the assignment's requirements doc.
     * @return an Answer "struct" containing the area of the biggest
     * rectangle that can be constructed, the left and right
     * x-coordinates of the selected rectangle, and the height of that
     * rectangle.
     * @throws IllegalArgumentException if null array or fewer than 2 elements
     */
    public static Answer get(final int[] heights) {
        if (heights == null || heights.length < 2) {
            throw new IllegalArgumentException("Illegal input.");
        }
        int minIndex = 0;
        Answer answer = new Answer (0,0,0,0);
        for (int maxIndex = heights.length - 1; maxIndex != minIndex; maxIndex --) {
            int height = Integer.min(heights[minIndex], heights[maxIndex]); 
            int width = maxIndex - minIndex; 
            int currArea = height * width; 
            answer = currArea > answer.max ? new Answer(currArea, minIndex, maxIndex, height) : answer;
            // adjust indices, other case adjusts itself thanks to for loop:
            if (height == heights[minIndex] ) {
                minIndex += 1;
                maxIndex += 1;
            }
        }
	    return answer;
    }
}