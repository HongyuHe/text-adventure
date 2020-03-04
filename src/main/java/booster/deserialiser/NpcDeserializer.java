package booster.deserialiser;

import entity.Npc;
import entity.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class NpcDeserializer implements JsonDeserializer<Npc> {
    @Override
    public Npc deserialize(JsonElement json,
                            Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Stat stat = new Stat(
                jsonObject.get("stat").getAsJsonObject().get("name").getAsString(),
                jsonObject.get("stat").getAsJsonObject().get("value").getAsInt()
        );

        return new Npc(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("description").getAsString(),
                createArray(jsonObject.get("inventory").getAsJsonArray()),
                jsonObject.get("active").getAsBoolean(),
                stat
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
}


