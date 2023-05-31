package one.nxeu.vlcore.services;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import one.nxeu.vlcore.util.TranslatableTexts;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.function.Predicate;

public class ActionBarService implements Runnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location location = player.getLocation();
            Location down = location.clone();
            down.setY(down.getY() - 1);

            Location eyeLocation = player.getEyeLocation();
            Vector dir = eyeLocation.getDirection();
            Predicate<Entity> filter = entity -> !entity.equals(player);
            RayTraceResult traceResult = player.getWorld().rayTrace(eyeLocation, dir, 100, FluidCollisionMode.NEVER, false, 0, filter);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(TranslatableTexts.fmt(
                            "actionbar.text",
                            player.getName(),
                            down.getBlock().getType().toString(),
                            location.getBlock().getType().toString(),
                            Objects.isNull(traceResult) || Objects.isNull(traceResult.getHitBlock()) ? "NaN" : traceResult.getHitBlock().getType().toString(),
                            Objects.isNull(traceResult) || Objects.isNull(traceResult.getHitEntity()) ? "NaN" : traceResult.getHitEntity().getName(),
                            player.getHealth()))
            );
        }
    }
}
