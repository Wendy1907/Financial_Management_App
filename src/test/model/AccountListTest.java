package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for AccountList class
 */
public class AccountListTest {
    private AccountList testAccountList;

    @BeforeEach
        //Create an AccountList to apply for the test and run before every test
    void runBefore() {
        testAccountList = new AccountList("AccountList");
    }

    @Test
    void testConstructor() {
        assertEquals("AccountList", testAccountList.getName());
        assertEquals(0, testAccountList.getAccountList().size());
    }

    @Test
    void testTotalAccount() {
        assertEquals(0, testAccountList.totalAccount());
    }

    @Test
    void testTotalAccountAddAccount() {
        Account a1 = new Account("TD Account");
        Account a2 = new Account("RBC Account");
        Account a3 = new Account("Cash Deposit Account");
        testAccountList.addAccount(a1);
        testAccountList.addAccount(a2);
        testAccountList.addAccount(a3);

        assertEquals(3, testAccountList.totalAccount());
    }

    @Test
    void testAddOneAccount() {
        Account a = new Account("TD Account");
        testAccountList.addAccount(a);

        assertEquals(1, testAccountList.getAccountList().size());
        assertEquals(a, testAccountList.getAccountList().get(0));
    }

    @Test
    void testAddMultipleAccounts() {
        Account a1 = new Account("TD Account");
        Account a2 = new Account("RBC Account");
        Account a3 = new Account("Cash Deposit Account");
        testAccountList.addAccount(a1);
        testAccountList.addAccount(a2);
        testAccountList.addAccount(a3);

        assertEquals(3, testAccountList.getAccountList().size());
        assertEquals(a1, testAccountList.getAccountList().get(0));
        assertEquals(a2, testAccountList.getAccountList().get(1));
        assertEquals(a3, testAccountList.getAccountList().get(2));
    }

    @Test
    void testRemoveOneAccount() {
        Account a = new Account("TD Account");
        testAccountList.addAccount(a);
        testAccountList.removeAccount(a);

        assertEquals(0, testAccountList.getAccountList().size());
    }

    @Test
    void testRemoveMultipleAccounts() {
        Account a1 = new Account("TD Account");
        Account a2 = new Account("RBC Account");
        Account a3 = new Account("Cash Deposit Account");
        testAccountList.addAccount(a1);
        testAccountList.addAccount(a2);
        testAccountList.addAccount(a3);
        testAccountList.removeAccount(a2);
        testAccountList.removeAccount(a3);

        assertEquals(1, testAccountList.getAccountList().size());
        assertEquals(a1, testAccountList.getAccountList().get(0));
    }

    @Test
    void testCalculateTotalSpendingOneAccount() {
        Account a = new Account("TD Account");
        testAccountList.addAccount(a);

        assertEquals(0.0, testAccountList.calculateTotalSpendingAllAccount());
    }

    @Test
    void testCalculateTotalSpendingMultipleAccount() {
        Account a1 = new Account("TD Account");
        Account a2 = new Account("RBC Account");
        Account a3 = new Account("Cash Deposit Account");
        testAccountList.addAccount(a1);
        testAccountList.addAccount(a2);
        testAccountList.addAccount(a3);

        assertEquals(0.0, testAccountList.calculateTotalSpendingAllAccount());
    }

    @Test
    void testCalculateTotalEarningOneAccount() {
        Account a = new Account("TD Account");
        testAccountList.addAccount(a);

        assertEquals(0.0, testAccountList.calculateTotalEarningAllAccount());
    }

    @Test
    void testCalculateTotalEarningMultipleAccount() {
        Account a1 = new Account("TD Account");
        Account a2 = new Account("RBC Account");
        Account a3 = new Account("Cash Deposit Account");
        testAccountList.addAccount(a1);
        testAccountList.addAccount(a2);
        testAccountList.addAccount(a3);

        assertEquals(0.0, testAccountList.calculateTotalEarningAllAccount());
    }
}
