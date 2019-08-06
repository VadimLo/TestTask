package com.java.task.test;

import com.java.task.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {
    private String testRow="{}{{}";
    @Test
    public void shouldReturnSet(){
        Assert.assertNotNull(Validator.validate(testRow));

    }
    @Test
    public void shouldReturnCorrectExpectedValue(){
        String expectedValue ="{}{}";
        Assert.assertEquals(expectedValue,Validator.validate(testRow).iterator().next());

    }
}
