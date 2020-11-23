package Origin.ARestart;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener{
    //Defines all the variables and staticss
    public static Main instance;
    public int count = 10;
    public int restart = 1;
    public int can = 1;

    @Override
    public void onEnable() { //When Server Start

        //this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
       //this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //logs to the console the plugin has been enabled
        getLogger().info("ARestart has been enabled");

        // Registers arestart command
        this.getCommand("arestart").setExecutor(this);

        instance = this;
        asave(); // Runs the auto save function
        auto(); //Runs the auto restart function
    }

    @Override
    public void onDisable() { //When server terminates
        getLogger().info("ARestart has been disabled");
    }

//    @Override
//    public void onPluginMessageReceived(String s, Player player, byte[] bytyes){
//
//    }

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
                        can = 0; //sets can to 0 so the else will run in tens
                        restart = 1; //Enables /arestart to be typed again
                        tens(null); //Runs tens function
                    } else if (args[0].equalsIgnoreCase("notimer")) {
                        count = 0;
                        if (b.toString().equals("")) {
                            tens("No reason given");
                        } else {
                            tens(b.toString());
                        }


                    } else if (args[0].equalsIgnoreCase("restart")) {
                        if (restart == 1) { // if restart command has not already been typed

                            if (b.toString().equals("")) {
                                mins("No reason given");
                            } else {
                                mins(b.toString());
                            }
                            //mins(b.toString()); // runs the mins functions



                            restart--; //sets restart varible to 0
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Server is already restarting!"); //Sends to the user if arestart has already been run and not cancled
                        }
                    } else if (args[0].equalsIgnoreCase("save")) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f World Saving - You might experience some lag"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all ");
                        getLogger().info("World has been saved!");
                        sender.sendMessage(ChatColor.AQUA + "Worlds has been saved");
                    }  else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments. Type /arestart for help.");
                    }
                }

        }
        return true;
    }

    public void auto() {
        //Auto restarts the server every 6 hours
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() { // Restarts the server every 6 hours
            @Override
            public void run() {
                mins(null); //Runs the mins fuction
            }
        }, ((20l * 60) * 60) * 6);
    }

    public void mins(String a){ //First 60/30 warnings
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
    public void tens(String a){ //Ten section warning
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

                                        p.kickPlayer(ChatColor.AQUA + "The server is automatically restarting!" + ChatColor.WHITE + p.getDisplayName() + System.lineSeparator() + ChatColor.GREEN
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
                                    //p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
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
                           auto();
                        }
                    }, 20l * 60);
                }
            }
        }, 20l);
    }
    public void asave(){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f World Saving - You might experience some lag"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all ");
                getLogger().info("World has been saved!");
                asave();
            }
        }, 20l * 60 * 30 );
    }

}