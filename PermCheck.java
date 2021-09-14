package com.company;

import java.util.*;

/*
* A non-empty array A consisting of N integers is given.

A permutation is a sequence containing each element from 1 to N once, and only once.

For example, array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
is a permutation, but array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
is not a permutation, because value 2 is missing.

The goal is to check whether array A is a permutation.

Write a function:

        class Solution { public int solution(int[] A); }

that, given an array A, returns 1 if array A is a permutation and 0 if it is not.

For example, given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
the function should return 1.

Given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
the function should return 0.

Write an efficient algorithm for the following assumptions:

    * N is an integer within the range [1..100,000];
    * each element of array A is an integer within the range [1..1,000,000,000].
* */

public class PermCheck {

    public boolean sizesAreEqual(int size1, int size2) { return (size1 == size2);}

    public int solution(int[] A) {

        Set<Integer> setOfNumbers = new HashSet<>();

        int size = 0;
        for (int j : A) {
            if (size < j) size = j;
            setOfNumbers.add(j);
        }

        if(sizesAreEqual(setOfNumbers.size(),size) && sizesAreEqual(size, A.length)) return 1;
        else return 0;

    }

}