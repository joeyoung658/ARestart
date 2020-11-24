package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Data.ARestartConfig;
import Origin.ARestart.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class saveIntervalCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (args.length != 2) return false;
        int delay;
        try {
            delay =  Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Time must be a number!");
            return false;
        }
        ARestartConfig.updateConfig(Main.instance, delay, "autoSave");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                + ChatColor.BLUE + "The server will now save every " + delay  + " minutes!");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                + ChatColor.RED + "Your changes will take effect from the next server restart!");
        return false;
    }
}
