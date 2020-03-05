package deserialiser;

import entity.Obstacle;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObstacleDeserializer implements JsonDeserializer<Obstacle> {

    @Override
    public Obstacle deserialize(JsonElement json,
                                     Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        return new Obstacle(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("currentLocation").getAsString(),
                jsonObject.get("state").getAsBoolean(),
                jsonObject.get("blocks").getAsString(),
                jsonObject.get("requiredObject").getAsString(),
                createCommandArray(jsonObject.get("commands").getAsJsonArray())
        );
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

    private ArrayList<String> createArray(JsonArray jArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
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


}
