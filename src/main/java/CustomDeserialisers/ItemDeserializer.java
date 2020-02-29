package CustomDeserialisers;

import Entities.IEntity;
import Entities.Item;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ItemDeserializer implements JsonDeserializer<IEntity> {

        @Override
        public IEntity deserialize(JsonElement json,
                                   Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();

            Item.Stat stat = new Item.Stat(
                    jsonObject.get("stat").getAsJsonObject().get("name").getAsString(),
                    jsonObject.get("stat").getAsJsonObject().get("value").getAsInt()
            );

            return new Item(
                    jsonObject.get("type").getAsString(),
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("description").getAsString(),
                    jsonObject.get("consumable").getAsBoolean(),
                    jsonObject.get("active").getAsBoolean(),
                    stat
            );
        }
}
