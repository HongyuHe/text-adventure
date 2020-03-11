package deserialiser;

import entity.Item;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ItemDeserializer extends Deserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Item(
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createSet(jsonObject.get("inventory").getAsJsonArray()),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandSet(jsonObject.get("commands").getAsJsonArray()),
                jsonObject.get("consumable").getAsBoolean()
        );
    }
}
