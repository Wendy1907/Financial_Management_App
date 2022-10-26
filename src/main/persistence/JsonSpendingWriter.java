package persistence;

import model.Account;
import org.json.JSONObject;

public class JsonSpendingWriter extends JsonWriter {

    //EFFECTS: constructs writer to write to destination file
    public JsonSpendingWriter(String destination) {
        super(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of Account to file
    public void write(Account account) {
        JSONObject json = account.toJson();
        saveToFile(json.toString(TAB));
    }
}
