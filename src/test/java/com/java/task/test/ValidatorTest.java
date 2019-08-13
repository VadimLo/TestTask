package com.java.task.test;

import com.java.task.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import static com.java.task.validator.Validator.validate;

public class ValidatorTest {

    @Test
    public void shouldReturnValidSetLikeExampleOne(){
        String testRow ="{}}{}}";
        System.out.println(validate(testRow));

    }
    @Test
    public void shouldReturnValidSetLikeExampleTwo(){
        String testRow ="{}x}x}";
        System.out.println(validate(testRow));

    }
    @Test
    public void shouldReturnValidSetLikeExampleThree(){
        String testRow ="{";
        System.out.println(validate(testRow));

    }
    @Test
    public void shouldReturnValidSetLikeReworkOne(){
        String testRow ="a";
        System.out.println(validate(testRow));

    }
    @Test
    public void shouldReturnValidSetLikeReworkTwo(){
        String testRow ="{}}{{}}{";
        System.out.println(validate(testRow));

    }
    @Test
    public void shouldReturnValidSet(){
        String testRow ="XX{X}t{{e{}}}x}t}{}X";
        System.out.println(validate(testRow));


    }
    @Test
    public void shouldReturnSet() {
        String testRow ="text";
        Assert.assertNotNull(validate(testRow));

    }


    @Test
    public void shouldDeleteFirstAndLastInvalidChars(){
        String expectedValue = "a{text}a";
        String testRow ="a}{text}{a";
        Assert.assertEquals(expectedValue, Validator.removeFirstAndLastChars(testRow).toString());

    }

}
