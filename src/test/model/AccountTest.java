package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Account class
 */
public class AccountTest {
    private Account testAccount;
    private Event e1;
    private Event e2;
    private Event e3;

    @BeforeEach
        //Create an Account to apply into the test and this run before
        //every single test.
    void runBefore() {
        testAccount = new Account("TD Account");
        e1 = new Event("A1");
        e2 = new Event("A2");
        e3 = new Event("A3");
        EventLog el = EventLog.getInstance();
        el.clear();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
    }

    @Test
        //Test for the constructor of the given example
    void testConstructor() {
        assertEquals("TD Account", testAccount.getNameAccount());
        assertEquals(0, testAccount.getSpendingList().size());
        assertEquals(0, testAccount.getEarningList().size());
        assertEquals(0, testAccount.getDinningList().size());
        assertEquals(0, testAccount.getShoppingList().size());
        assertEquals(0, testAccount.getTravelList().size());
        assertEquals(0, testAccount.getGroceriesList().size());
        assertEquals(0, testAccount.getHealthList().size());
        assertEquals(0, testAccount.getOthersSpendingList().size());
        assertEquals(0, testAccount.getSalaryList().size());
        assertEquals(0, testAccount.getInterestList().size());
        assertEquals(0, testAccount.getOthersEarningList().size());
    }

    @Test
        //Test for the adding Spending into the SpendingList
    void testAddSpendingOneTime() {
        Spending s = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        testAccount.addSpending(s);

        assertEquals(1, testAccount.getSpendingList().size());
        assertEquals(s, testAccount.getSpendingList().get(0));
    }

    @Test
    void testAddSpendingMultipleTimes() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);

        assertEquals(2, testAccount.getSpendingList().size());
        assertEquals(s1, testAccount.getSpendingList().get(0));
        assertEquals(s2, testAccount.getSpendingList().get(1));
    }

    @Test
    void testRemoveSpendingOneTime() {
        Spending s = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        testAccount.addSpending(s);
        testAccount.removeSpending(s);

        assertEquals(0, testAccount.getSpendingList().size());
    }

    @Test
    void testRemoveSpendingMultipleTimes() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        Spending s3 = new Spending("Food for new week", 120.00, SpendingCategories.Groceries);
        Spending s4 = new Spending("Bus Ticket", 4.00, SpendingCategories.Travel);
        Spending s5 = new Spending("Ferry Ticket", 18.00, SpendingCategories.Travel);
        Spending s6 = new Spending("Clothes", 210.00, SpendingCategories.Shopping);
        Spending s7 = new Spending("Insurance", 75.00, SpendingCategories.Health);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);
        testAccount.addSpending(s5);
        testAccount.addSpending(s6);
        testAccount.addSpending(s7);
        testAccount.removeSpending(s1);
        testAccount.removeSpending(s4);
        testAccount.removeSpending(s5);
        testAccount.removeSpending(s6);

        assertEquals(3, testAccount.getSpendingList().size());
        assertEquals(s2, testAccount.getSpendingList().get(0));
        assertEquals(s3, testAccount.getSpendingList().get(1));
        assertEquals(s7, testAccount.getSpendingList().get(2));
    }

    @Test
    void testCalculateTotalSpendingOneSpending() {
        Spending s = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        testAccount.addSpending(s);

        assertEquals(12.00, testAccount.calculateTotalSpendingAccount());
    }

    @Test
    void testCalculateTotalSpendingMultipleSpending() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        Spending s3 = new Spending("Food for new week", 120.00, SpendingCategories.Groceries);
        Spending s4 = new Spending("Bus Ticket", 4.00, SpendingCategories.Travel);
        Spending s5 = new Spending("Ferry Ticket", 18.00, SpendingCategories.Travel);
        Spending s6 = new Spending("Clothes", 210.00, SpendingCategories.Shopping);
        Spending s7 = new Spending("Insurance", 75.00, SpendingCategories.Health);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);
        testAccount.addSpending(s5);
        testAccount.addSpending(s6);
        testAccount.addSpending(s7);

        assertEquals((12.00 + 40.00 + 120.00 + 4.00 + 18.00 + 210.00 + 75.00), testAccount.calculateTotalSpendingAccount());
    }

    @Test
        //Test for the adding Spending into the SpendingList
    void testAddEarningOneTime() {
        Earning e = new Earning("Salary", 12.00, EarningCategories.Salary);
        testAccount.addEarning(e);

        assertEquals(1, testAccount.getEarningList().size());
        assertEquals(e, testAccount.getEarningList().get(0));
    }

    @Test
    void testAddEarningMultipleTimes() {
        Earning e1 = new Earning("Salary", 12.00, EarningCategories.Salary);
        Earning e2 = new Earning("Interest", 40.00, EarningCategories.Interest);
        testAccount.addEarning(e1);
        testAccount.addEarning(e2);

        assertEquals(2, testAccount.getEarningList().size());
        assertEquals(e1, testAccount.getEarningList().get(0));
        assertEquals(e2, testAccount.getEarningList().get(1));
    }

    @Test
    void testRemoveEarningOneTime() {
        Earning e = new Earning("Salary", 12.00, EarningCategories.Salary);
        testAccount.addEarning(e);
        testAccount.removeEarning(e);

        assertEquals(0, testAccount.getEarningList().size());
    }

    @Test
    void testRemoveEarningMultipleTimes() {
        Earning e1 = new Earning("Salary", 12.00, EarningCategories.Salary);
        Earning e2 = new Earning("Interest", 40.00, EarningCategories.Interest);
        Earning e3 = new Earning("Others Income", 100.00, EarningCategories.Others);
        testAccount.addEarning(e1);
        testAccount.addEarning(e2);
        testAccount.addEarning(e3);
        testAccount.removeEarning(e1);
        testAccount.removeEarning(e3);

        assertEquals(1, testAccount.getEarningList().size());
        assertEquals(e2, testAccount.getEarningList().get(0));
    }

    @Test
    void testCalculateTotalEarningOneEarning() {
        Earning e = new Earning("Salary", 12.00, EarningCategories.Salary);
        testAccount.addEarning(e);

        assertEquals(12.00, testAccount.calculateTotalEarningAccount());
    }

    @Test
    void testCalculateTotalEarningMultipleEarnings() {
        Earning e1 = new Earning("Salary", 12.00, EarningCategories.Salary);
        Earning e2 = new Earning("Interest", 40.00, EarningCategories.Interest);
        Earning e3 = new Earning("Others Income", 100.00, EarningCategories.Others);
        testAccount.addEarning(e1);
        testAccount.addEarning(e2);
        testAccount.addEarning(e3);

        assertEquals((12.00 + 40.00 + 100.000), testAccount.calculateTotalEarningAccount());
    }

    @Test
    void testStoreSpendingZeroSpending() {
        testAccount.storeSpending();
        assertEquals(0, testAccount.getDinningList().size());
        assertEquals(0, testAccount.getShoppingList().size());
        assertEquals(0, testAccount.getTravelList().size());
        assertEquals(0, testAccount.getHealthList().size());
        assertEquals(0, testAccount.getGroceriesList().size());
        assertEquals(0, testAccount.getOthersSpendingList().size());
    }

    @Test
    void testStoreSpendingOneSpending() {
        Spending s = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        testAccount.addSpending(s);
        testAccount.storeSpending();

        assertEquals(1, testAccount.getDinningList().size());
        assertEquals(0, testAccount.getShoppingList().size());
        assertEquals(0, testAccount.getTravelList().size());
        assertEquals(0, testAccount.getHealthList().size());
        assertEquals(0, testAccount.getGroceriesList().size());
        assertEquals(0, testAccount.getOthersSpendingList().size());
        assertEquals(s, testAccount.getDinningList().get(0));
    }

    @Test
    void testStoreSpendingMultipleSpending() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        Spending s3 = new Spending("Food for new week", 120.00, SpendingCategories.Groceries);
        Spending s4 = new Spending("Bus Ticket", 4.00, SpendingCategories.Travel);
        Spending s5 = new Spending("Ferry Ticket", 18.00, SpendingCategories.Travel);
        Spending s6 = new Spending("Clothes", 210.00, SpendingCategories.Shopping);
        Spending s7 = new Spending("Insurance", 75.00, SpendingCategories.Health);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);
        testAccount.addSpending(s5);
        testAccount.addSpending(s6);
        testAccount.addSpending(s7);
        testAccount.storeSpending();

        assertEquals(1, testAccount.getDinningList().size());
        assertEquals(1, testAccount.getShoppingList().size());
        assertEquals(2, testAccount.getTravelList().size());
        assertEquals(1, testAccount.getHealthList().size());
        assertEquals(1, testAccount.getGroceriesList().size());
        assertEquals(1, testAccount.getOthersSpendingList().size());
        assertEquals(s1, testAccount.getDinningList().get(0));
        assertEquals(s2, testAccount.getOthersSpendingList().get(0));
        assertEquals(s3, testAccount.getGroceriesList().get(0));
        assertEquals(s4, testAccount.getTravelList().get(0));
        assertEquals(s5, testAccount.getTravelList().get(1));
        assertEquals(s6, testAccount.getShoppingList().get(0));
        assertEquals(s7, testAccount.getHealthList().get(0));
    }

    @Test
    void testStoreEarningZeroEarning() {
        testAccount.storeEarning();
        assertEquals(0, testAccount.getSalaryList().size());
        assertEquals(0, testAccount.getInterestList().size());
        assertEquals(0, testAccount.getOthersEarningList().size());
    }

    @Test
    void testStoreEarningOneEarning() {
        Earning e = new Earning("Salary", 12.00, EarningCategories.Salary);
        testAccount.addEarning(e);
        testAccount.storeEarning();

        assertEquals(1, testAccount.getSalaryList().size());
        assertEquals(0, testAccount.getInterestList().size());
        assertEquals(0, testAccount.getOthersEarningList().size());
        assertEquals(e, testAccount.getSalaryList().get(0));
    }

    @Test
    void testStoreEarningMultipleEarnings() {
        Earning e1 = new Earning("Salary", 12.00, EarningCategories.Salary);
        Earning e2 = new Earning("Interest", 40.00, EarningCategories.Interest);
        Earning e3 = new Earning("Others Income", 100.00, EarningCategories.Others);
        Earning e4 = new Earning("Others", 500.00, EarningCategories.Others);
        Earning e5 = new Earning("abcde", 10.00, EarningCategories.Others);
        testAccount.addEarning(e1);
        testAccount.addEarning(e2);
        testAccount.addEarning(e3);
        testAccount.addEarning(e4);
        testAccount.addEarning(e5);
        testAccount.storeEarning();

        assertEquals(1, testAccount.getSalaryList().size());
        assertEquals(1, testAccount.getInterestList().size());
        assertEquals(3, testAccount.getOthersEarningList().size());
        assertEquals(e1, testAccount.getSalaryList().get(0));
        assertEquals(e2, testAccount.getInterestList().get(0));
        assertEquals(e3, testAccount.getOthersEarningList().get(0));
        assertEquals(e4, testAccount.getOthersEarningList().get(1));
        assertEquals(e5, testAccount.getOthersEarningList().get(2));
    }

    @Test
    void testConvertToSpendingCategory() {
        assertEquals(testAccount.convertToSpendingCategory("Dinning"), SpendingCategories.Dinning);
        assertEquals(testAccount.convertToSpendingCategory("Shopping"), SpendingCategories.Shopping);
        assertEquals(testAccount.convertToSpendingCategory("Travel"), SpendingCategories.Travel);
        assertEquals(testAccount.convertToSpendingCategory("Health"), SpendingCategories.Health);
        assertEquals(testAccount.convertToSpendingCategory("Groceries"), SpendingCategories.Groceries);
        assertEquals(testAccount.convertToSpendingCategory("a"), SpendingCategories.Others);
    }

    @Test
    void testConvertToEarningCategory() {
        assertEquals(testAccount.convertToEarningCategory("Salary"), EarningCategories.Salary);
        assertEquals(testAccount.convertToEarningCategory("Interest"), EarningCategories.Interest);
        assertEquals(testAccount.convertToEarningCategory("a"), EarningCategories.Others);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
        assertEquals("Event log cleared.", l.get(0).getDescription());
        assertEquals(e1, l.get(1));
        assertEquals(e2, l.get(2));
        assertEquals(e3, l.get(3));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
