package com.company;

import java.util.*;

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















