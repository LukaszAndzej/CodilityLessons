package com.company;

import java.util.*;

public class PermMissingElem {

    private boolean setContainANumber(Set<Integer> set, int number) { return (!set.add(number));}

    public int solution(int[] A) {

        Set<Integer> setOfNumbers = new HashSet<>();
        setOfNumbers.add(1);

        for(int i = 0; i < A.length; i++) {
            if(setContainANumber(setOfNumbers, (i + 2))) setOfNumbers.remove(i+2);
            if(setContainANumber(setOfNumbers, A[i])) setOfNumbers.remove(A[i]);
        }

        return setOfNumbers.iterator().next();
    }

}