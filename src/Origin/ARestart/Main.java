package Origin.ARestart;

import Origin.ARestart.Commands.ARestartCommandHandler;
import Origin.ARestart.Commands.ARestartTabCompleter;
import Origin.ARestart.Commands.Sub.noTimerCmd;
import Origin.ARestart.Commands.Sub.restartCmd;
import Origin.ARestart.Commands.Sub.saveCmd;
import Origin.ARestart.Commands.Sub.stopRestartCmd;
import Origin.ARestart.Commands.aRestartCmd;
import Origin.ARestart.Data.ARestartConfig;
import Origin.ARestart.Server.Restart.autoRestart;
import Origin.ARestart.Server.Save.autoSave;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

//todo Make commands to edit the Auto save and restart times and update the config acordling

//todo Create a toggle to send players to hub on restart rather than kick them from server

//todo change to a command handler & implement tab listener
//WIP

//todo Upload to Spigot & make a public plugin

public class Main extends JavaPlugin implements Listener{
    //Defines all the variables and staticss
    public static Main instance;
    @Override
    public void onEnable() { //When Server Start
        //this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
       //this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        new ARestartConfig(this);
        registerARestartCommands();
        instance = this;

        //Sets up auto save/restart
        long min = 60L * 60;
        int autoSaveTime = ARestartConfig.getAutoSaveTime();
        int autoRestartTime = ARestartConfig.getAutoRestartTime();
        BukkitTask autoSave = new autoSave(this).runTaskTimer(this, (min * autoSaveTime), (min * autoSaveTime));
        BukkitTask autoRestart = new autoRestart(this).runTaskTimer(this, (min * autoRestartTime), (min * autoRestartTime));


        getLogger().info("ARestart has been enabled");
    }
    @Override
    public void onDisable() { //When server terminates
        getLogger().info("ARestart has been disabled");
    }


    private void registerARestartCommands(){
        ARestartCommandHandler aRestartCommands = new ARestartCommandHandler();
        aRestartCommands.register("arestart", new aRestartCmd());

        aRestartCommands.register("save", new saveCmd());
        aRestartCommands.register("restart", new restartCmd());
        aRestartCommands.register("notimer", new noTimerCmd());
        aRestartCommands.register("stoprestart", new stopRestartCmd());

        getCommand("arestart").setExecutor(aRestartCommands);
        getCommand("arestart").setTabCompleter(new ARestartTabCompleter());
    }
}