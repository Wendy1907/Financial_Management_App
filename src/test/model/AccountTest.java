package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    private Account testAccount;

    @BeforeEach
    //Create an Account to apply into the test and this run before
    //every single test.
    void runBefore() {
        testAccount = new Account("TD Account");
    }

    @Test
    //Test for the constructor of the given example
    void testConstructor() {
        assertEquals("TD Account", testAccount.getNameAccount());
        assertEquals(0, testAccount.getTotalSpendingAccount());
        assertEquals(0, testAccount.getTotalEarningAccount());
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
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);
        testAccount.removeSpending(s1);
        testAccount.removeSpending(s4);

        assertEquals(2, testAccount.getSpendingList().size());
        assertEquals(s2, testAccount.getSpendingList().get(0));
        assertEquals(s3, testAccount.getSpendingList().get(1));
    }

    @Test
    void testCalculateTotalSpendingOneSpending() {
        Spending s = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        testAccount.addSpending(s);

        assertEquals(12.00, testAccount.calculateTotalSpendingAccount());
    }

    @Test
    void testCalculateTotalSpendingMultipleSpendings() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        Spending s3 = new Spending("Food for new week", 120.00, SpendingCategories.Groceries);
        Spending s4 = new Spending("Bus Ticket", 4.00, SpendingCategories.Travel);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);

        assertEquals((12.00 + 40.00 + 120.00 + 4.00), testAccount.calculateTotalSpendingAccount());
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
    void testStoreSpendingMultipleSpendings() {
        Spending s1 = new Spending("Sushi", 12.00, SpendingCategories.Dinning);
        Spending s2 = new Spending("Bookstore", 40.00, SpendingCategories.Others);
        Spending s3 = new Spending("Food for new week", 120.00, SpendingCategories.Groceries);
        Spending s4 = new Spending("Bus Ticket", 4.00, SpendingCategories.Travel);
        Spending s5 = new Spending("Ferry Ticket", 18.00, SpendingCategories.Travel);
        testAccount.addSpending(s1);
        testAccount.addSpending(s2);
        testAccount.addSpending(s3);
        testAccount.addSpending(s4);
        testAccount.addSpending(s5);
        testAccount.storeSpending();

        assertEquals(1, testAccount.getDinningList().size());
        assertEquals(0, testAccount.getShoppingList().size());
        assertEquals(2, testAccount.getTravelList().size());
        assertEquals(0, testAccount.getHealthList().size());
        assertEquals(1, testAccount.getGroceriesList().size());
        assertEquals(1, testAccount.getOthersSpendingList().size());
        assertEquals(s1, testAccount.getDinningList().get(0));
        assertEquals(s2, testAccount.getOthersSpendingList().get(0));
        assertEquals(s3, testAccount.getGroceriesList().get(0));
        assertEquals(s4, testAccount.getTravelList().get(0));
        assertEquals(s5, testAccount.getTravelList().get(1));
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
        testAccount.addEarning(e1);
        testAccount.addEarning(e2);
        testAccount.addEarning(e3);
        testAccount.addEarning(e4);
        testAccount.storeEarning();

        assertEquals(1, testAccount.getSalaryList().size());
        assertEquals(1, testAccount.getInterestList().size());
        assertEquals(2, testAccount.getOthersEarningList().size());
        assertEquals(e1, testAccount.getSalaryList().get(0));
        assertEquals(e2, testAccount.getInterestList().get(0));
        assertEquals(e3, testAccount.getOthersEarningList().get(0));
        assertEquals(e4, testAccount.getOthersEarningList().get(1));
    }



}
