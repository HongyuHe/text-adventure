package deserialiser;

import entity.Npc;
import com.google.gson.*;

import java.lang.reflect.Type;

public class NpcDeserializer extends Deserializer implements JsonDeserializer<Npc> {

    @Override
    public Npc
    deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        JsonObject jsonObject = json.getAsJsonObject();

        return new Npc(
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createSet(jsonObject.get("inventory").getAsJsonArray()),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandSet(jsonObject.get("commands").getAsJsonArray()),
                jsonObject.get("currentLocation").getAsString(),
                jsonObject.get("isFriendly").getAsBoolean()
        );
    }
}


