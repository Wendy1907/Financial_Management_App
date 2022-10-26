package persistence;

import model.Account;
import model.Spending;
import model.SpendingCategories;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonSpendingReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonSpendingReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads Spending from file and returns it;
    //throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses Account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Account account = new Account(name);
        addSpendingList(account, jsonObject);
        return account;
    }

    //MODIFIES: account
    //EFFECTS: parses spending from JSON object and adds them to SpendingLíst
    private void addSpendingList(Account spendingList, JSONObject jsonObject) {
        JSONArray jsonAll = jsonObject.getJSONArray("spendingList");
        for (Object json : jsonAll) {
            JSONObject next = (JSONObject) json;
            addSpendingItem(spendingList, next);
        }
    }

    private void addSpendingItem(Account spendingList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        SpendingCategories categories = SpendingCategories.valueOf(jsonObject.getString("categories"));
        Spending spending = new Spending(name, amount, categories);
        spendingList.addEarning(spending);
    }

}