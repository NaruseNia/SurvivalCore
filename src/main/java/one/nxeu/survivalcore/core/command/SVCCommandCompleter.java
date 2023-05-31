package one.nxeu.survivalcore.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SVCCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completes = new ArrayList<>();
        switch (args.length){
            case 1:
                completes.add("get");
                return completes;
            case 2:
                completes.add("mspt");
                completes.add("tps");
                return completes;
            default:
                break;
        }
        return null;
    }
}
