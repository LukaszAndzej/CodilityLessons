package com.company;

/*
A DNA sequence can be represented as a string consisting of the letters A, C, G and T,
which correspond to the types of successive nucleotides in the sequence.
Each nucleotide has an impact factor, which is an integer.
Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively.
You are going to answer several queries of the form: What is the minimal
impact factor of nucleotides contained in a particular part of the given DNA sequence?

The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters.
There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained
in the DNA sequence between positions P[K] and Q[K] (inclusive).

For example, consider string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
The answers to these M = 3 queries are as follows:

    * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice),
        whose impact factors are 3 and 2 respectively, so the answer is 2.
    * The part between positions 5 and 5 contains a single nucleotide T,
        whose impact factor is 4, so the answer is 4.
    * The part between positions 0 and 6 (the whole string) contains all nucleotides,
        in particular nucleotide A whose impact factor is 1, so the answer is 1.

Write a function:

        class Solution { public int[] solution(String S, int[] P, int[] Q); }

that, given a non-empty string S consisting of N characters and two non-empty arrays
P and Q consisting of M integers, returns an array consisting of M integers specifying
the consecutive answers to all queries.

Result array should be returned as an array of integers.

For example, given the string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
the function should return the values [2, 4, 1], as explained above.

Write an efficient algorithm for the following assumptions:

    * N is an integer within the range [1..100,000];
    * M is an integer within the range [1..50,000];
    * each element of arrays P, Q is an integer within the range [0..N − 1];
    * P[K] ≤ Q[K], where 0 ≤ K < M;
    * string S consists only of upper-case English letters A, C, G, T.
* */

public class GenomicRangeQuery {

    enum Nucleotides {
        A(1), C(2), G(3), T(4);

        int impactFactor;

        Nucleotides(int impactFactor) {
            this.impactFactor = impactFactor;
        }

        int getImpactFactor() {
            return impactFactor;
        }
    }

    static void fillPrefixSumArray(int i, String nucleotide, int[][] prefixSumArray) {

        int j = 0;
        for(Nucleotides n: Nucleotides.values()) {
            if(nucleotide.equals(n.toString())) prefixSumArray[j][i] += prefixSumArray[j][i-1] + 1;
            else prefixSumArray[j][i] += prefixSumArray[j][i-1];

            if(++j == prefixSumArray.length) break;
        }
    }

    static void getAnswersArray(int[] index, int[] answers, int[][] prefixSumArray) {

        int indexAnswers = index[2];

        for(Nucleotides n: Nucleotides.values()) {
            //because array starts from 0 but Impact Factor from 1 (A).
            int nucleotideIndex = n.getImpactFactor() - 1;

            if(nucleotideIndex < prefixSumArray.length) {
                if(checkDiff(index,prefixSumArray[nucleotideIndex])) {
                    answers[indexAnswers] = n.getImpactFactor();
                    break;
                }
            } else answers[indexAnswers] = n.getImpactFactor();
        }
    }

    static boolean checkDiff(int[] index, int[] prefixSumArray) {

        int indexBegin = index[0];
        int indexEnd = index[1];
        int diff = prefixSumArray[indexEnd] - prefixSumArray[indexBegin];

        return diff > 0;
    }

    public int[] solution(String S, int[] P, int[] Q) {

        int M = P.length;
        //because I use prefix sum then first element in array should be 0
        int lengthOfDNASequence = S.length() + 1;

        int[] answers = new int[M];
        int[][] prefixSumArray = new int[3][lengthOfDNASequence];

        for(int i = 1; i < lengthOfDNASequence; i++) {
            String nucleotide = S.substring(i-1,i);
            fillPrefixSumArray(i,nucleotide,prefixSumArray);
        }

        for(int j = 0; j < M; j++) {
            // + 1 because first element is 0 in ArrayList
            int[] index = {P[j], Q[j] + 1, j};
            getAnswersArray(index,answers,prefixSumArray);
        }

        return answers;
    }

}