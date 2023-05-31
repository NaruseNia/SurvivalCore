package one.nxeu.vlcore.command;

import net.minecraft.server.MinecraftServer;
import one.nxeu.vlcore.util.Messages;
import one.nxeu.vlcore.util.ServerInfoHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Player;

public class MessageCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("get")) {
            if (sender instanceof Player player) {
                player.sendMessage(Messages.fmt(args[1]), player.getName());
                return true;
            }
        }
        return false;
    }
}
