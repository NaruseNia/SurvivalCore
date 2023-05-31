package one.nxeu.vlcore.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class ItemUtils {
    public static ItemStack createItem(final Material material, final String name, @Nullable final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        if (!Objects.isNull(lore)) meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

}
