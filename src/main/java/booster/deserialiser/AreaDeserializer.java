package booster.deserialiser;

import entity.Area;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AreaDeserializer implements JsonDeserializer<Area> {

    @Override
    public Area deserialize(JsonElement json,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        return new Area(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("description").getAsString(),
                createArray(jsonObject.get("inventory").getAsJsonArray()),
                createArray(jsonObject.get("npcs").getAsJsonArray()),
                createMap(jsonObject.get("connections").getAsJsonObject())
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

    private HashMap<String, String> createMap(JsonObject jObject) {
        HashMap<String, String> map = new HashMap<>();

        if (jObject != null) {
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            map = new Gson().fromJson(jObject.getAsString(), type);
        }

        return map;
    }


}
