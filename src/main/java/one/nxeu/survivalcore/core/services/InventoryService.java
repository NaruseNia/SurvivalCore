package one.nxeu.survivalcore.core.services;

import one.nxeu.survivalcore.core.text.TranslatableTexts;
import one.nxeu.survivalcore.core.helper.TimeHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class InventoryService implements Runnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String text = "時計 " + TranslatableTexts.iridium("actionbar.clock", TimeHelper.parseTime(player.getWorld().getTime()));
            for (ItemStack item : player.getInventory().getContents()) {
                if (Objects.isNull(item)) continue;
                if (item.getType().equals(Material.CLOCK)) {
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(text);

                    item.setItemMeta(meta);
                }
            }
        }
    }
}
