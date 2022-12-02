package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for AccountList class
 */
public class AccountListTest {
    private AccountList testAccountList;
    private Event e1;
    private Event e2;
    private Event e3;

    @BeforeEach
        //Create an AccountList to apply for the test and run before every test
    void runBefore() {
        testAccountList = new AccountList("AccountList");
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
