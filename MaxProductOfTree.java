package com.company;

import java.util.Arrays;

/*
A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R)
equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

    * (0, 1, 2), product is −3 * 1 * 2 = −6
    * (1, 2, 4), product is 1 * 2 * 5 = 10
    * (2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Write an efficient algorithm for the following assumptions:

    * N is an integer within the range [3..100,000];
    * each element of array A is an integer within the range [−1,000..1,000].
* */

public class MaxProductOfTree {

    private boolean valueIsBiggerThenElementInArray(int value, int triple) { return (value >= triple);}

    private boolean twoNegativeNumbersAreGreaterThenPositive(int minPair, int maxPair, int max) { return ((minPair > maxPair) && (max > 0));}

    private boolean itIsBigNumber(boolean flag, int value, int arr) { return (flag && valueIsBiggerThenElementInArray(value,arr));}

    private boolean itIsSmallNumber(boolean flag, int value, int arr) { return (!(flag || valueIsBiggerThenElementInArray(value,arr)));}

    private void shiftATriplet(int[] array, int i) {
        for(int j = 0; j < (array.length - 1 - i); j++){
            array[j] = array[j+1];
        }
    }

    private void updateValuesInArray(int[] array, int j, int i, int value) {
        shiftATriplet(array,j);
        array[i] = value;
    }

    private void checkIfCanPutValueIntoArray(int[] array, int value, boolean flag) {
        for(int i = array.length - 1, j = 0; i >= 0; i--, j++) {
            if(itIsBigNumber(flag,value,array[i])) {
                updateValuesInArray(array,j,i,value);
                break;
            }
            if(itIsSmallNumber(flag,value,array[i])){
                updateValuesInArray(array,j,i,value);
                break;
            }
        }
    }

    public int solution(int[] A) {

        int[] threeMaxNumbers = new int[3];
        int[] twoMinNumbers = new int[2];

        Arrays.fill(threeMaxNumbers, Integer.MIN_VALUE);
        Arrays.fill(twoMinNumbers, Integer.MAX_VALUE);

        for(int value: A) {
            checkIfCanPutValueIntoArray(threeMaxNumbers,value,true);
            checkIfCanPutValueIntoArray(twoMinNumbers,value,false);
        }

        int minPairOfNumbers = (twoMinNumbers[0]*twoMinNumbers[1]);
        int maxPairOfNumbers = (threeMaxNumbers[0]*threeMaxNumbers[1]);

        if(twoNegativeNumbersAreGreaterThenPositive(minPairOfNumbers,maxPairOfNumbers,threeMaxNumbers[2])) return minPairOfNumbers * threeMaxNumbers[2];
        else return maxPairOfNumbers * threeMaxNumbers[2];
    }
}