package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Server.autoRestart;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class restartCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /arestart [restart] [reason]");
            return false;
        }
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");

        if ( autoRestart.restart == 1) {
            if (b.toString().equals("")) {
                autoRestart.mins("No reason given");
            } else {
                autoRestart.mins(b.toString());
            }
            autoRestart.restart--; //sets restart varible to 0
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "Server is already restarting!"); //Sends to the user if arestart has already been run and not cancled
        }
        return false;
    }
}
