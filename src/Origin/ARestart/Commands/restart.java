package Origin.ARestart.Commands;

import Origin.ARestart.Main;
import Origin.ARestart.Server.Restart.autoRestart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class restart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Player player = (Player) sender;
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");
        if (alias.equalsIgnoreCase("arestart")) {
            // if (cmd.getName().equalsIgnoreCase("arestart")) { //checks if user types arestart as a command
            if (args.length == 0) { // if only arestart is typed
                sender.sendMessage(ChatColor.AQUA + "arestart commands.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "/arestart [stoprestart] [Reason]  - Cancels the server restart.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "/arestart [notimer] - Restarts the server without a timer.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "/arestart [restart] [Reason] - Retarts the server with countdown.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "/arestart [save] - Saves the servers worlds.");
            } else {
                if (args[0].equalsIgnoreCase("stoprestart")) { //Checks if stop is ran after
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Server Restart canceled!");
                    autoRestart.can = 0; //sets can to 0 so the else will run in tens
                    autoRestart.restart = 1; //Enables /arestart to be typed again
                    autoRestart.tens(null); //Runs tens function
                } else if (args[0].equalsIgnoreCase("notimer")) {
                    autoRestart.count = 0;
                    if (b.toString().equals("")) {
                        autoRestart.tens("No reason given");
                    } else {
                        autoRestart.tens(b.toString());
                    }


                } else if (args[0].equalsIgnoreCase("restart")) {
                    if ( autoRestart.restart == 1) { // if restart command has not already been typed
                        if (b.toString().equals("")) {
                            autoRestart.mins("No reason given");
                        } else {
                            autoRestart.mins(b.toString());
                        }
                        autoRestart.restart--; //sets restart varible to 0
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Server is already restarting!"); //Sends to the user if arestart has already been run and not cancled
                    }
                } else if (args[0].equalsIgnoreCase("save")) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f World Saving - You might experience some lag"));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all ");
                    Main.instance.getLogger().info("World has been saved!");
                    sender.sendMessage(ChatColor.AQUA + "Worlds has been saved");
                }  else {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments. Type /arestart for help.");
                }
            }

        }
        return true;
    }
}
