package Origin.ARestart.Commands.Sub;

import Origin.ARestart.Commands.ARestartCommandInterface;
import Origin.ARestart.Data.ARestartConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class currentIntervalCmd implements ARestartCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length > 1) return false;
        int autoSaveTime = ARestartConfig.getAutoSaveTime();
        int autoRestartTime = ARestartConfig.getAutoRestartTime();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.BLUE + "AutoSave - Every " + autoSaveTime + " minutes");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.BLUE + "AutoRestart - Every " + autoRestartTime + " minutes");
        return false;
    }
}
