package deserialiser;

import entity.Item;
import entity.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GameOverItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json,
                            Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        Stat stat = new Stat(
                jsonObject.get("stat").getAsJsonObject().get("name").getAsString(),
                jsonObject.get("stat").getAsJsonObject().get("value").getAsInt()
        );

        return new Item(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                jsonObject.get("consumable").getAsBoolean(),
                jsonObject.get("active").getAsBoolean(),
                stat
        );
    }
}
