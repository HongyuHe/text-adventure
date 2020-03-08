package deserialiser;

import com.google.gson.reflect.TypeToken;
import entity.Player;
import entity.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDeserializer implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement json,
                              Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Player(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createArray(jsonObject.get("inventory").getAsJsonArray()),
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("currentLocation").getAsString(),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandArray(jsonObject.get("commands").getAsJsonArray())

        );
    }

    private ArrayList<String> createArray(JsonArray jArray) {
        ArrayList<String> arrayList = new ArrayList<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
            }
        }

        return arrayList;
    }

    private ArrayList<CommandBlueprint> createCommandArray(JsonArray jArray) {
        ArrayList<CommandBlueprint> arrayList = new ArrayList<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(createObject(e.getAsJsonObject()));
            }
        }

        return arrayList;
    }

    private CommandBlueprint createObject(JsonObject jObject) {
        return new CommandBlueprint(
                jObject.get("name").getAsString(),
                jObject.get("function").getAsString(),
                createArray(jObject.get("params").getAsJsonArray())
        );
    }

    private HashMap<String, Integer> createMap(JsonObject jObject) {
        HashMap<String, Integer> map = new HashMap<>();

        if (jObject != null) {
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            map = new Gson().fromJson(jObject.getAsString(), type);
        }

        return map;
    }
}
