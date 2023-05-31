package one.nxeu.vlcore.command;

import net.minecraft.server.MinecraftServer;
import one.nxeu.vlcore.util.TranslatableTexts;
import one.nxeu.vlcore.util.ServerInfoHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Player;

public class VLCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (sender instanceof Player player) {
                MinecraftServer server = ((CraftServer) player.getServer()).getServer();
                if ("get".equals(args[0])) {
                    switch (args[1]) {
                        case "mspt" -> {
                            player.sendMessage("" + ServerInfoHelper.getMspt(server));
                            return true;
                        }
                        case "tps" -> {
                            player.sendMessage("" + ServerInfoHelper.getTps(server));
                            return true;
                        }
                        default -> {
                        }
                    }
                }
                sender.sendMessage(TranslatableTexts.fmt("command.vl.fail"));
            }
            return true;
        } catch (Exception e){
            sender.sendMessage(TranslatableTexts.fmt("command.vl.fail"));
            return true;
        }
    }
}
