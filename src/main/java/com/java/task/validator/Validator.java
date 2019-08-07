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
        boolean flag = true;


        StringBuilder inputFirstRow = new StringBuilder(input);
        StringBuilder inputSecondRow = new StringBuilder(input);

        Set<String> validateSet = new TreeSet<String>();

        while (flag) {   //remove first and last invalid chars from input row

            if (inputFirstRow.charAt(0) == CLOSE) {
                inputFirstRow.deleteCharAt(0);
            } else {
                int lastChar = inputFirstRow.length() - 1;
                if (inputFirstRow.charAt(lastChar) == OPEN) {
                    inputFirstRow.deleteCharAt(lastChar);
                } else {
                    flag = false;
                }
            }

        }

        for (int i = 0; i < inputFirstRow.length(); i++) {
            if (inputFirstRow.charAt(i) == OPEN) {
                openCount++;
            }
            if (inputFirstRow.charAt(i) == CLOSE) {
                closeCount++;
            }

        }

        if (openCount == closeCount) { //First check
            validateSet.add(inputFirstRow.toString());
            return validateSet;
        } else {
            residual = openCount - closeCount;
            if (residual > 0) {     //open>close

                for (int i = 0; i < inputFirstRow.length(); i++) {
                    if (inputFirstRow.charAt(i) == OPEN && residual != 0) {
                        inputFirstRow.deleteCharAt(i);
                        residual--;
                        i--;
                    }
                }
            } else {    //open<close
                for (int i = 0; i < inputFirstRow.length(); i++) {
                    if (inputFirstRow.charAt(i) == CLOSE && residual != 0) {
                        inputFirstRow.deleteCharAt(i);
                        residual++;
                        i--;
                    }
                }
            }
            validateSet.add(inputFirstRow.toString());

        }

        for (int i = 0; i < inputSecondRow.length(); i++) {//Second check
            if (inputSecondRow.charAt(i)==OPEN && inputSecondRow.charAt(i+1)==OPEN) {
                inputSecondRow.deleteCharAt(i+1);

            }
            if (inputSecondRow.charAt(i)==CLOSE && inputSecondRow.charAt(i+1)==CLOSE) {
                inputSecondRow.deleteCharAt(i+1);

            }
        }
        validateSet.add(inputSecondRow.toString());
        return validateSet;
    }
}
