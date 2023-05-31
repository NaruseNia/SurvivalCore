package one.nxeu.vlcore.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import one.nxeu.vlcore.util.TranslatableTexts;
import one.nxeu.vlcore.util.PermissionHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonHandler implements Listener {
    private static Gson gson = new Gson();

    @EventHandler
    private static void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(TranslatableTexts.fmt("common.join", event.getPlayer().getName()));
    }

    @EventHandler
    private static void onChat(AsyncPlayerChatEvent event) {
        String prefix = "";

        if (PermissionHelper.isPlayerInGroup(event.getPlayer(), "owner")) {
            prefix = TranslatableTexts.colorize("chat.prefix.owner");
        } else if (PermissionHelper.isPlayerInGroup(event.getPlayer(), "kusurichan")) {
            prefix = TranslatableTexts.iridium("chat.prefix.kusurichan");
        } else if (PermissionHelper.isPlayerInGroup(event.getPlayer(), "tempowner")) {
            prefix = TranslatableTexts.colorize("chat.prefix.tempowner");
        } else if (PermissionHelper.isPlayerInGroup(event.getPlayer(), "admin")) {
            prefix = TranslatableTexts.colorize("chat.prefix.admin");
        }

        if (event.getMessage().startsWith("!")) {
            event.setFormat(prefix + TranslatableTexts.fmt("common.chat.no_suggest", event.getPlayer().getName(), event.getMessage().substring(1)));
        } else if (event.getMessage().startsWith("$$")) {
            event.setFormat(prefix + IridiumColorAPI.process("<RAINBOW1>" + TranslatableTexts.fmt("common.chat.no_suggest", event.getPlayer().getName(), event.getMessage().substring(2)) + "</RAINBOW>"));
        } else {
            event.setFormat(prefix + TranslatableTexts.fmt("common.chat", event.getPlayer().getName(), event.getMessage(), suggest(event.getMessage())));
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, Sound.UI_BUTTON_CLICK, 0.4f, 1.5f);
        }
    }

    private static String suggest(String in) {
        try {
            String urlName = "http://www.google.com/transliterate?langpair=en|ja&text=" + in.replace(' ', '+');
            URL url = new URL(urlName);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String s;
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }

            String raw = builder.toString();
            String formattedText = raw.substring(1, raw.length() - 1);
            JsonObject resp = gson.fromJson(formattedText, JsonObject.class);
            return resp.get("hws").getAsJsonArray().get(0).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "?";
        }
    }
}
