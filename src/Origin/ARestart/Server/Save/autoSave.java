package Origin.ARestart.Server.Save;

import Origin.ARestart.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class autoSave extends BukkitRunnable {
    Main plugin;
    public autoSave(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f World Saving - You might experience some lag"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all ");
        Main.instance.getLogger().info("World has been saved!");
    }
}
