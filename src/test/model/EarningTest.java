package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarningTest {
    private Earning testEarning;

    @BeforeEach
        //Create an Earning to apply into the test and run before
        //every single test.
    void runBefore() {
        testEarning = new Earning("Salary", 1000.00, EarningCategories.Salary);
    }

    @Test
    void testConstructor() {
        assertEquals("Salary", testEarning.getName());
        assertEquals(1000.00, testEarning.getAmount());
        assertEquals(EarningCategories.Salary, testEarning.getCategories());
        assertEquals(LocalDate.now(), testEarning.getDate());
    }
}
