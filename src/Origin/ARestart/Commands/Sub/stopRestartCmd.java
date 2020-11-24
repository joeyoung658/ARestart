package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Server.autoRestart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class stopRestartCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length > 1) return false;
        Bukkit.broadcastMessage(ChatColor.GREEN + "Server Restart canceled!");
        autoRestart.can = 0; //sets can to 0 so the else will run in tens
        autoRestart.restart = 1; //Enables /arestart to be typed again
        autoRestart.tens(null); //Runs tens function
        return false;
    }
}
