package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonEarningReaderTest {
    protected void checkEarning(String name, double amount, EarningCategories categories, Earning earning) {
        assertEquals(name, earning.getName());
        assertEquals(amount, earning.getAmount());
        assertEquals(categories, earning.getCategories());
    }

    @Test
    void testEarningReaderNonExistentFile() {
        JsonEarningReader earningReader = new JsonEarningReader("./data/noSuchFile.json");
        try {
            Account account = earningReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEarningReaderEmptyAccount() {
        JsonEarningReader earningReader = new JsonEarningReader("./data/testEarningReaderEmptyAccount.json");

        try {
            Account account = earningReader.read();
            assertEquals("My account", account.getNameAccount());
            assertEquals(0, account.getEarningList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testEarningReaderGeneralAccount() {
        JsonEarningReader earningReader = new JsonEarningReader("./data/testEarningReaderGeneralAccount.json");
        try {
            Account account = earningReader.read();
            assertEquals("My account", account.getNameAccount());
            List<Earning> earningList = account.getEarningList();
            assertEquals(2, earningList.size());
            checkEarning("a", 1.00, EarningCategories.Salary, account.getEarningList().get(0));
            checkEarning("b", 2.00, EarningCategories.Others, account.getEarningList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
