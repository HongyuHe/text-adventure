package CustomDeserialisers;

import Entities.IEntity;
import Entities.IInteractable;
import Entities.Obstacle;
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
                jsonObject.get("state").getAsBoolean(),
                jsonObject.get("blocks").getAsString(),
                jsonObject.get("requiredObject").getAsString(),
                createArray(jsonObject.get("commands").getAsJsonArray())
        );
    }

    private ArrayList<Obstacle.Commands> createArray(JsonArray jArray) {
        ArrayList<Obstacle.Commands> arrayList = new ArrayList<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(createObject(e.getAsJsonObject()));
            }
        }

        return arrayList;
    }

    private ArrayList<String> createArray(JsonObject jObject) {
        ArrayList<String> arrayList = new ArrayList<>();
        JsonArray jArray = jObject.getAsJsonArray();
        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
            }
        }

        return arrayList;
    }

    private Obstacle.Commands createObject(JsonObject jObject) {
        return new Obstacle.Commands(
                jObject.get("name").getAsString(),
                jObject.get("function").getAsString(),
                createArray(jObject.get("params").getAsJsonObject())
        );
    }


}
