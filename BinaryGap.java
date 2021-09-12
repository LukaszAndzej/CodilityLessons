package com.company;

import java.util.*;

/*
* A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is
* surrounded by ones at both ends in the binary representation of N.

* For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The
* number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and
* one of length 3. The number 20 has binary representation 10100 and contains one binary gap of
* length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has
* binary representation 100000 and has no binary gaps.

* Write a function:

        class Solution { public int solution(int N); }

* that, given a positive integer N, returns the length of its longest binary gap. The function should return
* 0 if N doesn't contain a binary gap.

* For example, given N = 1041 the function should return 5, because N has binary representation
* 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0,
* because N has binary representation '100000' and thus no binary gaps.

* Write an efficient algorithm for the following assumptions:
        * N is an integer within the range [1..2,147,483,647].
* */

public class BinaryGap {

    private static boolean resultIsBiggerThenN(long powerOfTwo, int diffN) {
        return (powerOfTwo - diffN) > 0;
    }

    private static int getRest(int x, int y) {
        return (x - y);
    }

    private static int[] exponentiation(int n) {

        final int base = 2;

        int[] array = new int[2];
        long resultOfExponentiation = 1;
        int exponent = 0;

        while (true) {

            exponent++;
            resultOfExponentiation *= base;

            if(resultIsBiggerThenN(resultOfExponentiation,n)) {
                resultOfExponentiation /= 2;
                exponent -= 1;
                break;
            } else if(resultOfExponentiation == n) break;
        }

        array[0] = (int) resultOfExponentiation;
        array[1] = exponent;

        return array;
    }

    private static List<Integer> positionOneInBinarySequence(final int N) {

        List<Integer> listOfOnes = new ArrayList<>();
        int diffN = N;

        while (diffN > 0) {

            int [] array = exponentiation(diffN);
            int resultOfExponentiation = array[0];
            int exponent = array[1];

            if(!resultIsBiggerThenN(resultOfExponentiation,diffN)) {
                diffN -= resultOfExponentiation;
                listOfOnes.add(exponent);
            } else if(diffN == resultOfExponentiation) {
                listOfOnes.add(exponent);
                diffN -= resultOfExponentiation;
            }
        }

        return listOfOnes;
    }

    public int solution (int N) {

        List<Integer> list = positionOneInBinarySequence(N);
        int result = 0;

        if(list.isEmpty() || (list.size() == 1)) return 0;

        for(int i = 1; i < list.size(); i++) {
            int checkADiffOfExponent = getRest(list.get(i-1), list.get(i)) - 1;
            if(result < checkADiffOfExponent) result = checkADiffOfExponent;
        }

        return result;
    }


    public static void main(String[] args){

        BinaryGap solution2 = new BinaryGap();
        System.out.println(solution2.solution(1041));

    }
}