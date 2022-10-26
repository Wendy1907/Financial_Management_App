package persistence;

import model.Account;
import model.Earning;
import model.EarningCategories;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonEarningReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonEarningReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads Earning from file and returns it;
    //throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses Earning from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("nameAccount");
        Account account = new Account(name);
        addEarningList(account, jsonObject);
        return account;
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
