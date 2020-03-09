package deserialiser;

import com.google.gson.reflect.TypeToken;
import entity.Item;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Item(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                jsonObject.get("consumable").getAsBoolean(),
                jsonObject.get("active").getAsBoolean(),
                createMap(jsonObject.get("stats").getAsJsonObject())
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
