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
        int residual2 = 0;
        boolean flag = true;

        Set<String> validateSet = new TreeSet<String>();


        StringBuilder qw = new StringBuilder(input);


        while (flag) {   //remove first and last invalid chars from input row

            if (qw.length() == 0) {
                validateSet.add("");
                return validateSet;

            }
            if (qw.charAt(0) == CLOSE) {
                qw.deleteCharAt(0);
            } else {
                int lastChar = qw.length() - 1;
                if (qw.charAt(lastChar) == OPEN) {
                    qw.deleteCharAt(lastChar);
                } else {
                    flag = false;
                }
            }

        }

        StringBuilder inputFirstRow = new StringBuilder(qw);
        StringBuilder inputSecondRow = new StringBuilder(qw);
        //StringBuilder inputThirdRow = new StringBuilder(input);


        for (int i = 0; i < inputFirstRow.length(); i++) { //compare count of open and close
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
            residual2 = openCount - closeCount;

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

        for (int i = 1; i < inputSecondRow.length(); i++) {//Second check
            if (residual2 > 0) {
                if (inputSecondRow.charAt(i-1) == OPEN && inputSecondRow.charAt(i ) == OPEN) {
                    inputSecondRow.deleteCharAt(i);

                    residual2--;
                }
            } else {
                if (inputSecondRow.charAt(i-1) == CLOSE && inputSecondRow.charAt(i ) == CLOSE  ) {// || inputSecondRow.charAt(i ) == CLOSE && inputSecondRow.charAt(i + 1) == OPEN
                    inputSecondRow.deleteCharAt(i-1);
                    residual2++;
                    i--;
                }
            }
            if (residual2 == 0) {
                break;
            }


        }
        validateSet.add(inputSecondRow.toString());
        return validateSet;

    }
}
