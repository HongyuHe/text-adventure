package CustomDeserialisers;

import Entities.Item;
import Entities.Npc;
import Entities.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
    private ArrayList<String> createArray(JsonArray jArray) {
        ArrayList<String> arrayList = new ArrayList<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
            }
        }

        return arrayList;
    }
}


