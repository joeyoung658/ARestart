package Origin.ARestart.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class aRestartCmd implements ARestartCommandInterface{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length > 1) return false;
        sender.sendMessage(ChatColor.GREEN + "                ARestart commands");
        sender.sendMessage(ChatColor.RED + "-------------------------------------------------");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE + "/arestart [stoprestart] [Reason] - Cancels the next server restart.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [notimer] - Restarts the server without a timer.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [restart] [Reason] - Restarts the server with a countdown.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [save] - Saves the worlds.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [checkintervals] - Check how often the server saves/restarts automatically.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [saveinterval] - Changes how often the server automatically restarts.");
        sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.LIGHT_PURPLE +  "/arestart [restartinterval] - Changes how often the server automatically saves.");
        sender.sendMessage(ChatColor.RED + "-------------------------------------------------");
        return false;
    }
}
