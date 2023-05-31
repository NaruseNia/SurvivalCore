package one.nxeu.vlcore.handler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import one.nxeu.vlcore.VLCore;
import one.nxeu.vlcore.util.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerHandler implements Listener {
    @EventHandler
    private static void onMove(PlayerMoveEvent event) {
        Location playerLoc = event.getPlayer().getLocation();
        String text = "コンパス " + Messages.iridium("actionbar.compass", playerLoc.getX(), playerLoc.getY(), playerLoc.getZ(), event.getFrom().distance(event.getTo()) * 20);
        for (ItemStack item : event.getPlayer().getInventory().getContents()) {
            if (Objects.isNull(item)) continue;
            if (item.getType().equals(Material.COMPASS)) {
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(text);

                item.setItemMeta(meta);
            }
        }
    }

    @EventHandler
    private static void onClick(PlayerInteractEvent event) {
        if (Objects.isNull(event.getClickedBlock())) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.ANVIL)) {
            }
        }
    }

}
