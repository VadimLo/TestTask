package com.java.task.test;

import com.java.task.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import static com.java.task.validator.Validator.validate;

public class ValidatorTest {
    private String testRow = "{{yjt{{ili}";

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

    @Test
    public void shouldWorkFullCheck() {
        Validator validator=new Validator();

        StringBuilder builder = new StringBuilder("{{}}{{}}}}");
        System.out.println(Validator.findAllRemoveCombinationsIndexes(builder, 2, '}'));
        System.out.println();
        System.out.println(Validator.removeCharsByCombinationList(Validator.findAllRemoveCombinationsIndexes(builder, 2, '}'),builder));

        //Validator.fullCheck();

    }
}
