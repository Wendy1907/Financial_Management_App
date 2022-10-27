package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonAccountReaderTest {
    protected void checkAccount(String name, Account account) {
        assertEquals(name, account.getNameAccount());
    }

    protected void checkSpending(String name, double amount, SpendingCategories categories, Spending spending) {
        assertEquals(name, spending.getName());
        assertEquals(amount, spending.getAmount());
        assertEquals(categories, spending.getCategories());
    }

    protected void checkEarning(String name, double amount, EarningCategories categories, Earning earning) {
        assertEquals(name, earning.getName());
        assertEquals(amount, earning.getAmount());
        assertEquals(categories, earning.getCategories());
    }

    @Test
    void testReaderNonExistentFile() {
        JsonAccountReader accountReader = new JsonAccountReader("./data/noSuchFile.json");
        try {
            AccountList accountList = accountReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testAccountReaderEmptyAccountList() {
        JsonAccountReader accountReader = new JsonAccountReader("./data/testAccountReaderEmptyAccountList.json");
        try {
            AccountList accountList = accountReader.read();
            assertEquals("My account list", accountList.getName());
            assertEquals(0, accountList.getAccountList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testAccountReaderGeneralAccountList() {
        JsonAccountReader accountReader = new JsonAccountReader("./data/testAccountReaderGeneralAccountList.json");
        try {
            AccountList accountList = accountReader.read();
            assertEquals("My account list", accountList.getName());
            List<Account> accounts = accountList.getAccountList();
            assertEquals(2, accounts.size());
            checkAccount("account1", accounts.get(0));
            checkAccount("account2", accounts.get(1));
            List<Spending> spendingList1 = accountList.getAccountList().get(0).getSpendingList();
            assertEquals(1, spendingList1.size());
            checkSpending("Sushi", 15.00, SpendingCategories.Dinning, spendingList1.get(0));
            List<Spending> spendingList2 = accountList.getAccountList().get(1).getSpendingList();
            assertEquals(1, spendingList2.size());
            checkSpending("Shopping", 100.00, SpendingCategories.Shopping, spendingList2.get(0));
            List<Earning> earningList = accountList.getAccountList().get(0).getEarningList();
            assertEquals(1, earningList.size());
            checkEarning("Salary", 500.00, EarningCategories.Salary, earningList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
