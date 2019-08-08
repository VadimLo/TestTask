package com.java.task.validator;

import java.util.*;

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
                if (inputSecondRow.charAt(i - 1) == OPEN && inputSecondRow.charAt(i) == OPEN) {
                    inputSecondRow.deleteCharAt(i);

                    residual2--;
                }
            } else {
                if (inputSecondRow.charAt(i - 1) == CLOSE && inputSecondRow.charAt(i) == CLOSE) {
                    inputSecondRow.deleteCharAt(i - 1);
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

    public static ArrayList<TreeSet<Integer>> findAllRemoveCombinationsIndexes(StringBuilder stringBuilder, int numOfRemove, char removedChar) { //return all combination of removable chars
        ArrayList<String> preList = new ArrayList<String>();


        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == removedChar) {
                list.add(String.valueOf(i));
            }
        }


        //start
        int N = list.size();//N - размер алфавита

        int[] pow = new int[numOfRemove + 1];//массив для степеней числа N: N^0, N^1, .., N^K
        pow[0] = 1;
        for (int i = 1; i <= numOfRemove; i++) {//вычисляем степени числа N
            pow[i] = pow[i - 1] * N;
        }

        //перебираем все номера комбинаций
        for (int i = 0; i < pow[numOfRemove]; i++) {
            String[] arr = new String[numOfRemove];

            for (int j = 0; j < numOfRemove; j++) {

                arr[j] = list.get((i / pow[j]) % N);
            }
            //вывод в консоль
            StringBuilder builder = new StringBuilder();
            for (String ch : arr) {
                //System.out.print(ch);
                builder.append(ch).append(' ');
            }

            preList.add(builder.toString());
        }


        ArrayList<TreeSet<Integer>> afterPreList = new ArrayList<TreeSet<Integer>>();
        for (String str : preList) {
            TreeSet<Integer> innerSet = new TreeSet<Integer>();
            int foundSpaceIndex = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {

                    innerSet.add(Integer.valueOf(str.substring(foundSpaceIndex, i)));
                    foundSpaceIndex = i + 1;
                }
            }
            if (innerSet.size() == numOfRemove) {
                afterPreList.add(innerSet);
            }

        }
        return afterPreList;
    }

    public static LinkedHashSet<String> removeCharsByCombinationList(ArrayList<TreeSet<Integer>> combinationList, StringBuilder validRow) {
        LinkedHashSet<String> finalList = new LinkedHashSet<String>();
        ;
        for (TreeSet<Integer> innerSet :
                combinationList) {
            StringBuilder stringBuilder = new StringBuilder(validRow);
            int k = 0;
            for (int inner : innerSet) {
                stringBuilder.deleteCharAt(inner - k);
                k++;
            }
            finalList.add(stringBuilder.toString());

        }
        return finalList;
    }
}
