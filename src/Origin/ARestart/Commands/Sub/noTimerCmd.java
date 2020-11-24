package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Server.autoRestart;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class noTimerCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /arestart [notimer] [reason]");
            return false;
        }
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");
        autoRestart.count = 0;
        if (b.toString().equals("")) {
            autoRestart.tens("No reason given");
        } else {
            autoRestart.tens(b.toString());
        }
        return false;
    }
}
