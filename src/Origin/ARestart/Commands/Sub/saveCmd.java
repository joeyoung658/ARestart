package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class saveCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length > 1) return false;
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f World Saving - You might experience some lag"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all ");
        Main.instance.getLogger().info("World has been saved!");
        return false;
    }
}
