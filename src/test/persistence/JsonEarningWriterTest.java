package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonEarningWriterTest {
    protected void checkEarning(String name, double amount, EarningCategories categories, Earning earning) {
        assertEquals(name, earning.getName());
        assertEquals(amount, earning.getAmount());
        assertEquals(categories, earning.getCategories());
    }

    @Test
    void testEarningWriterInvalidFile() {
        try {
            Account account = new Account("My account");
            JsonEarningWriter earningWriter = new JsonEarningWriter("./data/my\0illegal:fileName.json");
            earningWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testEarningWriterEmptyAccount() {
        try {
            Account account = new Account("My account");
            JsonEarningWriter earningWriter = new JsonEarningWriter("./data/testEarningWriterEmptyAccount.json");
            earningWriter.open();
            earningWriter.write(account);
            earningWriter.close();

            JsonEarningReader earningReader = new JsonEarningReader("./data/testEarningWriterEmptyAccount.json");
            account = earningReader.read();
            assertEquals("My account", account.getNameAccount());
            assertEquals(0, account.getEarningList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testEarningWriterGeneralAccount() {
        try {
            Account account = new Account("My account");
            account.addEarning(new Earning("a", 1.00, EarningCategories.Salary));
            account.addEarning(new Earning("b", 2.00, EarningCategories.Others));
            JsonEarningWriter earningWriter = new JsonEarningWriter("./data/testEarningWriterGeneralAccount.json");
            earningWriter.open();
            earningWriter.write(account);
            earningWriter.close();

            JsonEarningReader earningReader = new JsonEarningReader("./data/testEarningWriterGeneralAccount.json");
            account = earningReader.read();
            assertEquals("My account", account.getNameAccount());
            List<Earning> earningList = account.getEarningList();
            assertEquals(2, earningList.size());
            checkEarning("a", 1.00, EarningCategories.Salary, account.getEarningList().get(0));
            checkEarning("b", 2.00, EarningCategories.Others, account.getEarningList().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
