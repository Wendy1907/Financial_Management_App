package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for JsonAccountWriter
 */
public class JsonAccountWriterTest {

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
    void testAccountWriterInvalidFile() {
        try {
            AccountList accountList = new AccountList("My account list");
            JsonAccountWriter accountWriter = new JsonAccountWriter("./data/my\0illegal:fileName.json");
            accountWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testAccountWriterEmptyAccountList() {
        try {
            AccountList accountList = new AccountList("My account list");
            JsonAccountWriter accountWriter = new JsonAccountWriter("./data/testAccountWriterEmptyAccountList.json");
            accountWriter.open();
            accountWriter.write(accountList);
            accountWriter.close();

            JsonAccountReader accountReader = new JsonAccountReader("./data/testAccountWriterEmptyAccountList.json");
            accountList = accountReader.read();
            assertEquals("My account list", accountList.getName());
            assertEquals(0, accountList.getAccountList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testAccountWriterGeneralAccountList() {
        try {
            AccountList accountList = new AccountList("My account list");
            Account account1 = new Account("account1");
            Account account2 = new Account("account2");
            Spending spending1 = new Spending("Sushi", 15.00, SpendingCategories.Dinning);
            Spending spending2 = new Spending("Shopping", 100.00, SpendingCategories.Shopping);
            Earning earning = new Earning("Salary", 500.00, EarningCategories.Salary);
            accountList.addAccount(account1);
            accountList.addAccount(account2);
            account1.addSpending(spending1);
            account2.addSpending(spending2);
            account1.addEarning(earning);


            JsonAccountWriter accountWriter = new JsonAccountWriter("./data/testAccountWriterGeneralAccountList.json");
            accountWriter.open();
            accountWriter.write(accountList);
            accountWriter.close();

            JsonAccountReader accountReader = new JsonAccountReader("./data/testAccountWriterGeneralAccountList.json");
            accountList = accountReader.read();
            assertEquals("My account list", accountList.getName());
            List<Account> accounts = accountList.getAccountList();
            assertEquals(2, accounts.size());
            checkAccount("account1", accounts.get(0));
            checkAccount("account2", accounts.get(1));
            List<Spending> spendingList1 = account1.getSpendingList();
            assertEquals(1, spendingList1.size());
            checkSpending("Sushi", 15.00, SpendingCategories.Dinning, spendingList1.get(0));
            List<Spending> spendingList2 = account2.getSpendingList();
            assertEquals(1, spendingList2.size());
            checkSpending("Shopping", 100.00, SpendingCategories.Shopping, spendingList2.get(0));
            List<Earning> earningList = account1.getEarningList();
            assertEquals(1, earningList.size());
            checkEarning("Salary", 500.00, EarningCategories.Salary, earningList.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
