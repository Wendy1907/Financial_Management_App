package persistence;

import model.AccountList;
import org.json.JSONObject;

// This JsonAccountWriter references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a writer that writes JSON representation of AccountList to file
public class JsonAccountWriter extends JsonWriter {

    //EFFECTS: constructs writer to write to destination file
    public JsonAccountWriter(String destination) {
        super(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of AccountList to file
    public void write(AccountList accountList) {
        JSONObject json = accountList.toJson();
        saveToFile(json.toString(TAB));
    }
}
