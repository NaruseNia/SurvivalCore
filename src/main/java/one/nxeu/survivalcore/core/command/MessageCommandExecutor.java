package one.nxeu.survivalcore.core.command;

import one.nxeu.survivalcore.core.text.TranslatableTexts;
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
