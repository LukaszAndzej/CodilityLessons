package com.company;

import java.util.Arrays;

/*
We draw N discs on a plane. The discs are numbered from 0 to N − 1.
An array A of N non-negative integers, specifying the radiuses of the discs, is given.
The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and
the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0

There are eleven (unordered) pairs of discs that intersect, namely:

    * discs 1 and 4 intersect, and both intersect with all the other discs;
    * disc 2 also intersects with discs 0 and 3.
Write a function:

        class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of
(unordered) pairs of intersecting discs. The function should return −1 if the number
of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Write an efficient algorithm for the following assumptions:

    * N is an integer within the range [0..100,000];
    * each element of array A is an integer within the range [0..2,147,483,647].


                         Detected time complexity: O(N*log(N)) or O(N)

* */

public class NumberOfDiscIntersections {

    private static final int limit = 10_000_000;

    private boolean discsIntersect(long rightBorder, long leftBorder) {
        return (rightBorder >= leftBorder);
    }

    private void getBorders(int[] A, long[] rightBorder, long[] leftBorder) {
        for(int i = 0; i < A.length; i++) {
            rightBorder[i] = i + (long)A[i];
            leftBorder[i] = i - (long)A[i];
        }
    }

    public int solution(int[] A) {

        int countPairsOfIntersectingDiscs = 0;

        long[] rightBorder = new long[A.length];
        long[] leftBorder = new long[A.length];

        getBorders(A,rightBorder,leftBorder);

        Arrays.sort(rightBorder);
        Arrays.sort(leftBorder);

        int j = 0;
        for(int i = 0; i < A.length; i++) {

            while ((j < A.length) && discsIntersect(rightBorder[i], leftBorder[j])) {
                countPairsOfIntersectingDiscs += (j++ - i); // increment - will be increased after
                if(countPairsOfIntersectingDiscs > limit) return -1;
            }
        }

        return countPairsOfIntersectingDiscs;
    }

}