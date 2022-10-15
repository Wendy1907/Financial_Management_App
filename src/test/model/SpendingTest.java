package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpendingTest {
    private Spending testSpending;

    @BeforeEach
        //Create a Spending to apply into the test and this run before
        //every single test.
    void runBefore() {
        testSpending = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
    }

    @Test
    void testConstructor() {
        assertEquals("Sushi", testSpending.getName());
        assertEquals(12.00, testSpending.getAmount());
        assertEquals(SpendingCategories.Dinning, testSpending.getCategories());
    }
}
