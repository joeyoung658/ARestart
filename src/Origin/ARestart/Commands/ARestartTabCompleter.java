package Origin.ARestart.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ARestartTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("arestart")
                || cmd.getName().equalsIgnoreCase("stop")
                || cmd.getName().equalsIgnoreCase("restart")
                || cmd.getName().equalsIgnoreCase("quit")
                || cmd.getName().equalsIgnoreCase("end")){
            if ((sender instanceof Player) && (args.length == 1)){
                ARestartCommandHandler Commands = new ARestartCommandHandler();
                List<String> newList = Commands.getCommands();
                if (newList.isEmpty()){
                    return null;
                } else {
                    return newList;
                }
            }
        }
        return null;
    }
}
