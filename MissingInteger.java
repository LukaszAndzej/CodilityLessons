package com.company;

import java.util.*;

/*
* This is a demo task.

Write a function:

        class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:
    * N is an integer within the range [1..100,000];
    * each element of array A is an integer within the range [−1,000,000..1,000,000].

* */

// it's not clear enough...

public class MissingInteger {

    private boolean arrayContainOnlyNumberOne(int[] A) { return ((A.length == 1) && (A[0] == 1));}

    private boolean firstElementIsBiggerThenOne (int[] A, int i) { return ((i == 0) && (A[i] > 1));}

    private boolean numberIsLowerThenZero (int number) { return (number < 0);}

    private boolean nextNumberIsNotGreaterByOne(int number, int nexNumber) { return ((number + 1) < nexNumber);}

    private boolean positiveIntegerIsTheLastOne (int i, int length) { return ((i+2) == length);}

    public int solution(int[] A) {

        if(arrayContainOnlyNumberOne(A)) return 2;

        int result = 1;

        Arrays.sort(A);

        for(int i = 0; i < A.length - 1; i++) {

            if(firstElementIsBiggerThenOne(A,i)) return 1;
            if(numberIsLowerThenZero(A[i])) continue;
            if(nextNumberIsNotGreaterByOne(A[i], A[i+1])) return (A[i]+1);
            if(positiveIntegerIsTheLastOne(i, A.length)) return (A[i+1] + 1);
        }

        return result;
    }

}