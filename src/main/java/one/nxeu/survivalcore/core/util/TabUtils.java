package one.nxeu.survivalcore.core.util;

import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TabUtils {
    public static void setTabText(String header, String footer) {
        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header +"\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter tabPacket = new PacketPlayOutPlayerListHeaderFooter(headerComponent, footerComponent);

        if(Bukkit.getOnlinePlayers().size() != 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().b.a(tabPacket);
            }
        }
    }
}
