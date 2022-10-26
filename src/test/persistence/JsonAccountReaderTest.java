package persistence;

import model.Account;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonAccountReaderTest {
    protected void checkAccount(String name, Account account) {
        assertEquals(name, account.getNameAccount());
    }

    @Test
    void testReaderNonExistentFile() {
        JsonAccountReader accountReader = new JsonAccountReader(".data/noSuchFile.json");
        try {
            AccountList accountList = accountReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testAccountReaderEmptyAccountList() {
        JsonAccountReader accountReader = new JsonAccountReader(".data/testAccountReaderEmptyAccountList.json");
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
        JsonAccountReader accountReader = new JsonAccountReader(".data/testAccountReaderGeneralAccountList.json");
        try {
            AccountList accountList = accountReader.read();
            assertEquals("My account list", accountList.getName());
            List<Account> accounts = accountList.getAccountList();
            assertEquals(2, accounts.size());
            checkAccount("account1", accounts.get(0));
            checkAccount("account2", accounts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
