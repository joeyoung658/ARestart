package Origin.ARestart.Server;

import Origin.ARestart.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class autoRestart extends BukkitRunnable {
    public static int count = 10;
    public static int restart = 1;
    public static int can = 1;


    Main plugin;
    public autoRestart(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        mins(null);
    }

    public static void mins(String a){ //First 60/30 warnings
        Bukkit.broadcastMessage(ChatColor.BLUE + "Server Will Restart in 60 Seconds!");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() { //Until 30 second warning is run
            @Override
            public void run() {
                if (can == 1) {
                    Bukkit.broadcastMessage(ChatColor.BLUE + "Server Will Restart in 30 Seconds!");
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() { //Until 20 second warning is run
                    @Override
                    public void run() {
                        tens(a);
                    }
                }, 20l * 20);
            }
        }, 20l * 30);

    }
    public static void tens(String a){ //Ten section warning
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                if (can == 1) { //If can is 0 Restart will be cancled
                    if (count != -1) { //If count is not equal to -1
                        if (count != 0) { //if count is not equal to 0
                            if (count <= 3) {
                                Bukkit.broadcastMessage(ChatColor.RED + "Server Will Restart in " + count + " Seconds!");
                            } else {
                                Bukkit.broadcastMessage(ChatColor.BLUE + "Server Will Restart in " + count + " Seconds!");
                            }
                            count--; //takes one away from count1
                            tens(a);
                        } else {
                            Bukkit.broadcastMessage(ChatColor.RED + "Server Restarting.");

                            for (Player p : Bukkit.getOnlinePlayers()) { //gets all online players
                                if (a == null || a.equals("")) {

                                    p.kickPlayer(ChatColor.AQUA + "The server is automatically restarting! " + ChatColor.WHITE + p.getDisplayName() + System.lineSeparator() + ChatColor.GREEN
                                            + "The server will be back shortly!" + System.lineSeparator() + ChatColor.RED + "Check out our Discord!"
                                            + ChatColor.GOLD + "https://discord.gg/7nAGXXV");

//                                    p.sendMessage(ChatColor.AQUA + "The server is automatically restarting!"  +
//                                            ChatColor.GREEN + "The server should be back up shortly!" + ChatColor.RED + "Check out our Discord!"
//                                             + ChatColor.GOLD + "https://discord.gg/7nAGXXV");
                                    //p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
                                } else {

                                    p.kickPlayer(ChatColor.AQUA + "The server is automatically restarting!" + ChatColor.WHITE + p.getDisplayName() + System.lineSeparator() + ChatColor.GREEN
                                            + "The server will be back shortly!" + System.lineSeparator() + ChatColor.DARK_PURPLE + "Reason: " + ChatColor.WHITE + a +  System.lineSeparator() + ChatColor.RED + "Check out our Discord!"
                                            + ChatColor.GOLD + "https://discord.gg/7nAGXXV");
//                                    p.sendMessage(ChatColor.AQUA + "The server is restarting " + System.lineSeparator() +
//                                            ChatColor.GREEN + "The server should be back up shortly!" + System.lineSeparator() + ChatColor.DARK_PURPLE + "Reason: " + ChatColor.WHITE + a +  System.lineSeparator() + ChatColor.RED + "Check out our Discord!"
//                                            + System.lineSeparator() + ChatColor.GOLD + "https://discord.gg/7nAGXXV");
                                    //p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray())
                                }
                            }
                            count = 10;
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                                @Override
                                public void run() {
                                    Bukkit.getServer().shutdown();
                                }
                            }, 20l);
                        }

                    }
                } else {
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                        @Override
                        public void run() {
                            count = 10;
                            can = 1;
                        }
                    }, 20l * 60);
                }
            }
        }, 20l);
    }
}