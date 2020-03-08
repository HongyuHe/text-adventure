package deserialiser;

import com.google.gson.reflect.TypeToken;
import entity.Npc;
import entity.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NpcDeserializer implements JsonDeserializer<Npc> {
    @Override
    public Npc deserialize(JsonElement json,
                            Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Npc(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createArray(jsonObject.get("inventory").getAsJsonArray()),
                jsonObject.get("active").getAsBoolean(),
                createMap(jsonObject.get("stats").getAsJsonObject())
        );

    }
    private Set<String> createArray(JsonArray jArray) {
        Set<String> arrayList = new HashSet<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
            }
        }

        return arrayList;
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


