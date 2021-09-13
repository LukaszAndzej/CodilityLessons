package com.company;

/*
* An array A consisting of N integers is given. Rotation of the array means that each element is shifted
*  right by one index, and the last element of the array is moved to the first place. For example, the
* rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is
* moved to the first place).

* The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

* Write a function:

        class Solution { public int[] solution(int[] A, int K); }

* that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

* For example, given

    A = [3, 8, 9, 7, 6]
    K = 3
* the function should return [9, 7, 6, 3, 8]. Three rotations were made:

    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
* For another example, given

    A = [0, 0, 0]
    K = 1
* the function should return [0, 0, 0]

* Given

    A = [1, 2, 3, 4]
    K = 4
* the function should return [1, 2, 3, 4]

* Assume that:

    * N and K are integers within the range [0..100];
    * each element of array A is an integer within the range [−1,000..1,000].
* In your solution, focus on correctness. The performance of your solution will not be the focus of the
* assessment.

* */

public class CyclicRotation {

    private boolean arrayLengthIsKOrZero(int length, int K) { return ((length == K) || (length == 0)); }

    private boolean KIsBiggerThenArrayLength(int K, int length) { return (K > length);}

    private static boolean useEveryElementsFromShiftArray(int i, int shiftArrayLength) { return (i < shiftArrayLength); }

    private static int[] getShiftArray(final int[] A,final int K) {
        int[] shiftArray = new int[K];
        for(int beginShift = A.length - K, i = 0; beginShift < A.length; beginShift++, i++) shiftArray[i] = A[beginShift];
        return shiftArray;
    }

    private static void fillAnArray(final int[] A, int[] newArray,final int K) {

        int[] shiftArray = getShiftArray(A,K);

        for(int i = 0, j = 0; i < A.length; i++) {

            if(useEveryElementsFromShiftArray(i, shiftArray.length)) newArray[i] = shiftArray[i];
            else {
                newArray[i] = A[j];
                j++;
            }
        }
    }

    public int[] solution(int[] A, int K){

        int newK = K;
        int arrayLength = A.length;

        if(arrayLengthIsKOrZero(arrayLength, K)) return A;
        if(KIsBiggerThenArrayLength(K,arrayLength)) newK = K % arrayLength;

        int[] arrayRotation = new int[arrayLength];
        fillAnArray(A,arrayRotation,newK);

        return arrayRotation;
    }

}