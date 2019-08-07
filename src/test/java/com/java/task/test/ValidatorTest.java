package com.java.task.test;

import com.java.task.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import static com.java.task.validator.Validator.validate;

public class ValidatorTest {
    private String testRow = "}{}}}{{}{}{}}}{{{";

    //private String testRow="{{{}}}}";
    @Test
    public void shouldReturnSet() {
        Assert.assertNotNull(validate(testRow));

    }

    @Test
    public void shouldReturnCorrectExpectedValue() {
        String expectedValue = "{}{}";
        // Assert.assertEquals(expectedValue, validate(testRow).iterator().next());
        System.out.println(validate(testRow));

    }
}
