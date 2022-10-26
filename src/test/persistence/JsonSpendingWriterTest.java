package persistence;

import model.Account;
import model.Spending;
import model.SpendingCategories;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonSpendingWriterTest {

    protected void checkSpending(String name, double amount, SpendingCategories categories, Spending spending) {
        assertEquals(name, spending.getName());
        assertEquals(amount, spending.getAmount());
        assertEquals(categories, spending.getCategories());
    }

    @Test
    void testSpendingWriterInvalidFile() {
        try {
            Account account = new Account("My account");
            JsonSpendingWriter spendingWriter = new JsonSpendingWriter("./data/my\0illegal:fileName.json");
            spendingWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testSpendingWriterEmptyAccount() {
        try {
            Account account = new Account("My account");
            JsonSpendingWriter spendingWriter = new JsonSpendingWriter("./data/testSpendingWriterEmptyAccount.json");
            spendingWriter.open();
            spendingWriter.write(account);
            spendingWriter.close();

            JsonSpendingReader spendingReader = new JsonSpendingReader("./data/testSpendingWriterEmptyAccount.json");
            account = spendingReader.read();
            assertEquals("My account", account.getNameAccount());
            assertEquals(0, account.getSpendingList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testSpendingWriterGeneralAccount() {
        try {
            Account account = new Account("My account");
            account.addEarning(new Spending("a", 1.00, SpendingCategories.Dinning));
            account.addEarning(new Spending("b", 2.00, SpendingCategories.Others));
            JsonSpendingWriter spendingWriter = new JsonSpendingWriter("./data/testSpendingWriterGeneralAccount.json");
            spendingWriter.open();
            spendingWriter.write(account);
            spendingWriter.close();

            JsonSpendingReader spendingReader = new JsonSpendingReader("./data/testSpendingWriterGeneralAccount.json");
            account = spendingReader.read();
            assertEquals("My account", account.getNameAccount());
            List<Spending> spendingList = account.getSpendingList();
            assertEquals(2, spendingList.size());
            checkSpending("a", 1.00, SpendingCategories.Dinning, account.getSpendingList().get(0));
            checkSpending("b", 2.00, SpendingCategories.Others, account.getSpendingList().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
