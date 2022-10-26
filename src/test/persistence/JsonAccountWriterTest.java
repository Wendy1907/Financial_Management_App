package persistence;

import model.Account;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonAccountWriterTest {

    protected void checkAccount(String name, Account account) {
        assertEquals(name, account.getNameAccount());
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
            accountList.addAccount(new Account("account1"));
            accountList.addAccount(new Account("account2"));
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
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
