package one.nxeu.survivalcore.helper

import org.bukkit.entity.Player

class PermissionHelper {
    fun Player.isPlayerInGroup(group: String): Boolean {
        return this.hasPermission("group.$group")
    }
}