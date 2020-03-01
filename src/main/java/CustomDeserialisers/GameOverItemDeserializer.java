package CustomDeserialisers;

import Entities.GameOverItem;
import Entities.Player;
import Entities.Stat;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GameOverItemDeserializer implements JsonDeserializer<GameOverItem> {

    @Override
    public GameOverItem deserialize(JsonElement json,
                              Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Stat stat = new Stat(
                jsonObject.get("stat").getAsJsonObject().get("name").getAsString(),
                jsonObject.get("stat").getAsJsonObject().get("value").getAsInt()
        );

        return new GameOverItem(
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("description").getAsString(),
                jsonObject.get("consumable").getAsBoolean(),
                jsonObject.get("active").getAsBoolean(),
                stat
        );
    }
}
