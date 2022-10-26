package persistence;

import model.Account;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    protected void checkAccount(String name, Account account) {
        assertEquals(name, account.getNameAccount());
    }

    @Test
    void testWriterInvalidFile() {
        try {
            AccountList accountList = new AccountList("My account list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccountList() {
        try {
            AccountList accountList = new AccountList("My account list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccountList.json");
            accountList = reader.read();
            assertEquals("My account list", accountList.getName());
            assertEquals(0, accountList.getAccountList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccountList() {
        try {
            AccountList accountList = new AccountList("My account list");
            accountList.addAccount(new Account("account1"));
            accountList.addAccount(new Account("account2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccountList.json");
            writer.open();
            writer.write(accountList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccountList.json");
            accountList = reader.read();
            assertEquals("My account list", accountList.getName());
            List<Account> accounts = accountList.getAccountList();
            assertEquals(2, accounts.size());
            checkAccount("account1", accounts.get(0));
            checkAccount("account2", accounts.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
