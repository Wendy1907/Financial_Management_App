package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This JsonAccountReader references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads AccountList from JSON data stored in file
public class JsonAccountReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonAccountReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads Account from file and returns it;
    //throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses Account from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        String name = jsonObject.getString("nameAccountList");
        AccountList accountList = new AccountList(name);
        addAccounts(accountList, jsonObject);
        return accountList;
    }

    //MODIFIES: accountList
    //EFFECTS: parses accounts from JSON object and adds them to AccountList
    private void addAccounts(AccountList accountList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(accountList, nextAccount);
        }
    }

    //MODIFIES: accountList
    //EFFECTS: parses Account from JSON object and adds it to AccountList
    private void addAccount(AccountList accountList, JSONObject jsonObject) {
        String name = jsonObject.getString("nameAccount");
        Account account = new Account(name);
        accountList.addAccount(account);
        addSpendingList(account, jsonObject);
        addEarningList(account, jsonObject);
    }

    //MODIFIES: account
    //EFFECTS: parses spending from JSON object and adds them to SpendingList
    private void addSpendingList(Account spendingList, JSONObject jsonObject) {
        JSONArray jsonAll = jsonObject.getJSONArray("spendingList");
        for (Object json : jsonAll) {
            JSONObject next = (JSONObject) json;
            addSpendingItem(spendingList, next);
        }
    }

    //MODIFIES: account
    //EFFECTS: parses spending from JSON object and add it to SpendingList
    private void addSpendingItem(Account spendingList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        SpendingCategories categories = SpendingCategories.valueOf(jsonObject.getString("categories"));
        Spending spending = new Spending(name, amount, categories);
        spendingList.addSpending(spending);
    }

    //MODIFIES: earning
    //EFFECTS: parses earning from JSON object and adds them to EarningList
    private void addEarningList(Account earningList, JSONObject jsonObject) {
        JSONArray jsonAll = jsonObject.getJSONArray("earningList");
        for (Object json : jsonAll) {
            JSONObject next = (JSONObject) json;
            addEarning(earningList, next);
        }
    }

    //MODIFIES: earning
    //EFFECTS: parses earning from JSON object and adds it to EarningList
    private void addEarning(Account earningList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        EarningCategories categories = EarningCategories.valueOf(jsonObject.getString("categories"));
        Earning earning = new Earning(name, amount, categories);
        earningList.addEarning(earning);
    }
}
