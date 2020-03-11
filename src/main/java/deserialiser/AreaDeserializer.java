package deserialiser;

import entity.Area;
import com.google.gson.*;
import java.lang.reflect.Type;

public class AreaDeserializer extends Deserializer implements JsonDeserializer<Area> {

    @Override
    public Area deserialize(JsonElement json,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Area(
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createSet(jsonObject.get("inventory").getAsJsonArray()),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandSet(jsonObject.get("commands").getAsJsonArray()),
                createSet(jsonObject.get("obstacles").getAsJsonArray()),
                createSet(jsonObject.get("npcs").getAsJsonArray()),
                createMap(jsonObject.get("connections").getAsJsonObject())
        );
    }
}
