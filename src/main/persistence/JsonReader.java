package persistence;

import model.Account;
import model.AccountList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        String nameAccountList = jsonObject.getString("nameAccountList");
        AccountList accountList = new AccountList(nameAccountList);
        addAccounts(accountList, jsonObject);
        return accountList;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addAccounts(AccountList accountList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(accountList, nextAccount);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addAccount(AccountList accountList, JSONObject jsonObject) {
        String nameAccount = jsonObject.getString("nameAccount");
        Account account = new Account(nameAccount);
        accountList.addAccount(account);
    }
}
