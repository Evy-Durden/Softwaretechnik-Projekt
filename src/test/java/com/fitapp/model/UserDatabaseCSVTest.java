package com.fitapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDatabaseCSVTest {

    @Test
    public void testUserDatabaseCSVClass(){

        UserDatabaseCSV uDCSV = new UserDatabaseCSV();

        // testing exceptions for validateInput and validateUser method with JUnit assertions
        // JUnit receives lambda, executes it, validateInput is run, exception is thrown, JUnit catches it and
        // compares/asserts the thrown exception
        assertThrows(EmptyFieldException.class, ()-> uDCSV.validateInput("User",""));
        assertThrows(EmptyFieldException.class, ()-> uDCSV.validateInput("","pass"));
        assertThrows(InvalidCredentialsException.class, ()-> uDCSV.validateInput("User", "pass"));

    }

}
