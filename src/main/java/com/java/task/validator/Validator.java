package com.java.task.validator;

import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class Validator {
    private final static char OPEN = '{';
    private final static char CLOSE = '}';


    public static Set<String> validate(String input) {   //Main function
        int openCount = 0;
        int closeCount = 0;
        int residual;
        Set<String> validateSet = new TreeSet<String>();


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPEN) {
                openCount++;
            }
            if (input.charAt(i) == CLOSE) {
                closeCount++;
            }

        }
        if (openCount == closeCount) {
            validateSet.add(input);
            return validateSet;
        }else{
            residual=abs(openCount-closeCount);
        }


        validateSet.add("empty");


        return validateSet;
    }
}
