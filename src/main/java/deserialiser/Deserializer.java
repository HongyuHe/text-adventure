package deserialiser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Deserializer
{
    protected Set<String>
    createSet(JsonArray jArray) {
        return new HashSet<>(createArray(jArray));
    }

    protected ArrayList<String>
    createArray(JsonArray jArray) {
        ArrayList<String> arrayList = new ArrayList<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                arrayList.add(e.getAsString());
            }
        }

        return arrayList;
    }

    protected Set<CommandBlueprint>
    createCommandSet(JsonArray jArray) {
        Set<CommandBlueprint> set = new HashSet<>();

        if (jArray != null) {
            for (JsonElement e : jArray) {
                set.add(createObject(e.getAsJsonObject()));
            }
        }

        return set;
    }

    protected <T> HashMap<String, T>
    createMap(JsonObject jObject) {
        HashMap<String, T> map = new HashMap<>();

        if (jObject != null) {
            Type type = new TypeToken<HashMap<String, T>>(){}.getType();
            map = new Gson().fromJson(jObject.getAsString(), type);
        }

        return map;
    }

    protected CommandBlueprint
    createObject(JsonObject jObject) {
        return new CommandBlueprint(
                jObject.get("name").getAsString(),
                jObject.get("function").getAsString()
        );
    }
}
