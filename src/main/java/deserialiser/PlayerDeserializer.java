package deserialiser;

import entity.Player;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PlayerDeserializer extends Deserializer implements JsonDeserializer<Player> {

    @Override
    public Player
    deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        JsonObject jsonObject = json.getAsJsonObject();

        return new Player(
                jsonObject.get("active").getAsBoolean(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString().toLowerCase(),
                jsonObject.get("description").getAsString(),
                createSet(jsonObject.get("inventory").getAsJsonArray()),
                createMap(jsonObject.get("stats").getAsJsonObject()),
                createCommandSet(jsonObject.get("commands").getAsJsonArray()),
                jsonObject.get("currentLocation").getAsString()
        );
    }
}
