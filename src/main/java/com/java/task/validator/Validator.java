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
        char removedChar;

        Set<String> validateSet = new TreeSet<String>();



        StringBuilder builderFromInputRow = removeFirstAndLastChars(input);
        if (builderFromInputRow.length() <= 1) {
            validateSet.add("");
            return validateSet;

        }

        for (int i = 0; i < builderFromInputRow.length(); i++) { //compare count of open and close
            if (builderFromInputRow.charAt(i) == OPEN) {
                openCount++;
            }
            if (builderFromInputRow.charAt(i) == CLOSE) {
                closeCount++;
            }

        }
        residual = openCount - closeCount;
        if (residual > 0) {
            removedChar = OPEN;
        } else {
            removedChar = CLOSE;
        }


        validateSet = removeCharsByCombinationList(findAllRemoveCombinationsIndexes(builderFromInputRow, abs(residual), removedChar), builderFromInputRow);


        for (Iterator<String> it = validateSet.iterator(); it.hasNext(); ) {

            String element = it.next();

            if (element.length() != removeFirstAndLastChars(element).length()) {

                it.remove();
            }
        }


        return validateSet;

    }

    public static ArrayList<TreeSet<Integer>> findAllRemoveCombinationsIndexes(
            StringBuilder stringBuilder,
            int numOfRemove,
            char removedChar) { //return all combination of removable chars
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

            StringBuilder builder = new StringBuilder();
            for (String ch : arr) {

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

    public static Set<String> removeCharsByCombinationList(ArrayList<TreeSet<Integer>> combinationList, StringBuilder validRow) {
        Set<String> finalList = new LinkedHashSet<String>();
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

    public static StringBuilder removeFirstAndLastChars(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        boolean flag = true;
        while (flag) {   //remove first and last invalid chars from input row

            if(stringBuilder.length()==0){
                return stringBuilder;
            }
            if (stringBuilder.charAt(0) == CLOSE) {
                stringBuilder.deleteCharAt(0);
            } else {
                int lastChar = stringBuilder.length() - 1;
                if (stringBuilder.charAt(lastChar) == OPEN) {
                    stringBuilder.deleteCharAt(lastChar);
                } else {
                    flag = false;
                }
            }

        }
        return stringBuilder;
    }
}

