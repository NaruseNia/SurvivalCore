package one.nxeu.vlcore.core.helper;

import org.bukkit.entity.Player;

public class PermissionHelper {
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
