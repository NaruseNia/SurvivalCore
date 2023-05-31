package one.nxeu.vlcore.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import one.nxeu.vlcore.VLCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.ServerOperator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Objects;

public class Messages {
    public static String get(String key) {
        if (Objects.isNull(getAsJson(key))) return "";
        return Objects.requireNonNull(getAsJson(key)).getAsString();
    }

    public static JsonElement getAsJson(String key) {
        InputStream message = VLCore.getInstance().getResource("messages.json");

        if (Objects.isNull(message)) return null;

        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(message));
            StringBuilder resp = new StringBuilder();

            String in;
            while ((in = streamReader.readLine()) != null) resp.append(in);

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(resp.toString(), JsonObject.class);
            return json.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static String fmt(String key, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', new Formatter().format(get(key), args).toString());
    }

    public static String iridium(String key, Object... args) {
        return IridiumColorAPI.process(fmt(key, args));
    }

    public static String colorize(String key) {
        return ChatColor.translateAlternateColorCodes('&', get(key));
    }
}
