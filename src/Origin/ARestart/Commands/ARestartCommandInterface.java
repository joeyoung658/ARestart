package Origin.ARestart.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ARestartCommandInterface {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
