package persistence;

import model.Account;
import model.Spending;
import model.SpendingCategories;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonSpendingReaderTest {

    protected void checkSpending(String name, double amount, SpendingCategories categories, Spending spending) {
        assertEquals(name, spending.getName());
        assertEquals(amount, spending.getAmount());
        assertEquals(categories, spending.getCategories());
    }

    @Test
    void testSpendingReaderNonExistentFile() {
        JsonSpendingReader spendingReader = new JsonSpendingReader("./data/noSuchFile.json");
        try {
            Account account = spendingReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testSpendingReaderEmptyAccount() {
        JsonSpendingReader spendingReader = new JsonSpendingReader("./data/testSpendingReaderEmptyAccount.json");

        try {
            Account account = spendingReader.read();
            assertEquals("My account", account.getNameAccount());
            assertEquals(0, account.getSpendingList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSpendingReaderGeneralAccount() {
        JsonSpendingReader spendingReader = new JsonSpendingReader("./data/testSpendingReaderGeneralAccount.json");
        try {
            Account account = spendingReader.read();
            assertEquals("My account", account.getNameAccount());
            List<Spending> spendingList = account.getSpendingList();
            assertEquals(2, spendingList.size());
            checkSpending("a", 1.00, SpendingCategories.Dinning, account.getSpendingList().get(0));
            checkSpending("b", 2.00, SpendingCategories.Others, account.getSpendingList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}
