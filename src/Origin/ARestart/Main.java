package Origin.ARestart;

import Origin.ARestart.Commands.restart;
import Origin.ARestart.Server.Restart.autoRestart;
import Origin.ARestart.Server.Save.autoSave;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public class Main extends JavaPlugin implements Listener{
    //Defines all the variables and staticss
    public static Main instance;


    @Override
    public void onEnable() { //When Server Start

        //this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
       //this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //logs to the console the plugin has been enabled
        getLogger().info("ARestart has been enabled");

        this.getCommand("arestart").setExecutor(new restart());
        instance = this;

        long min = 60L * 60;

        //Runs every 30 mins
        BukkitTask autoSave = new autoSave(this).runTaskTimer(this, (min * 30), (min * 30));
        //Runs every 6 hours
        BukkitTask autoRestart = new autoRestart(this).runTaskTimerAsynchronously(this, ((min * 60) * 6), ((min * 60) * 6));

    }

    @Override
    public void onDisable() { //When server terminates
        getLogger().info("ARestart has been disabled");
    }
}