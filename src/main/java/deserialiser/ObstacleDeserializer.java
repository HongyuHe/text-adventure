package deserialiser;

import entity.Obstacle;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ObstacleDeserializer extends Deserializer implements JsonDeserializer<Obstacle> {

    @Override
    public Obstacle deserialize(JsonElement json,
                                     Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException { // NOSONAR

        JsonObject jsonObject = json.getAsJsonObject();

        return new Obstacle(
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createSet(jsonObject.get("inventory").getAsJsonArray()),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandSet(jsonObject.get("commands").getAsJsonArray()),
                jsonObject.get("currentLocation").getAsString(),
                jsonObject.get("state").getAsBoolean(),
                jsonObject.get("blocks").getAsString(),
                jsonObject.get("requiredObject").getAsString()
        );
    }
}
