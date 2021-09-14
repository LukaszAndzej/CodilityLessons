package com.company;

/*
* A non-empty array A consisting of N integers is given. Array A represents numbers on
* a tape.

* Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1],
* ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

* The difference between the two parts is the value of:
* |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

* In other words, it is the absolute difference between the sum of the first part and the
* sum of the second part.

* For example, consider array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3

* We can split this tape in four places:

    * P = 1, difference = |3 − 10| = 7
    * P = 2, difference = |4 − 9| = 5
    * P = 3, difference = |6 − 7| = 1
    * P = 4, difference = |10 − 3| = 7

* Write a function:

        class Solution { public int solution(int[] A); }

* that, given a non-empty array A of N integers, returns the minimal difference that can
* be achieved.

* For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3
* the function should return 1, as explained above.

* Write an efficient algorithm for the following assumptions:

    * N is an integer within the range [2..100,000];
    * each element of array A is an integer within the range [−1,000..1,000].
* */

public class TapeEquilibrium {

    private int absoluteDiffValue(int a, int b) {
        int result = a - b;
        if(result < 0) return (-1)*result;
        else return result;
    }

    private int sumFromOneN(int[] A) {
        int sum = 0;
        for (int j : A) sum += j;
        return sum;
    }

    private boolean compareTwoResult(int result, int newResult) { return (result > newResult);}

    public int solution(int[] A) {

        int firstSumOfArray = 0;
        int secondSumOfArray = sumFromOneN(A);

        int result = Integer.MAX_VALUE;
        for(int j = 0; j < A.length - 1; j++) {
            firstSumOfArray += A[j];
            secondSumOfArray -= A[j];

            int newResult = absoluteDiffValue(firstSumOfArray,secondSumOfArray);
            if(compareTwoResult(result, newResult)) result = newResult;
        }

        return result;
    }

}
