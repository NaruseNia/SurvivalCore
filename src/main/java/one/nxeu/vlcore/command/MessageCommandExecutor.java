package one.nxeu.vlcore.command;

import one.nxeu.vlcore.util.TranslatableTexts;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("get")) {
            if (sender instanceof Player player) {
                player.sendMessage(TranslatableTexts.fmt(args[1]), player.getName());
                return true;
            }
        }
        return false;
    }
}
