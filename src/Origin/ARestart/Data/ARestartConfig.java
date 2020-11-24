package Origin.ARestart.Data;

import Origin.ARestart.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class ARestartConfig {

    private static int autoSaveTime;
    private static int autoRestartTime;

    Main plugin;
    public ARestartConfig(Main plugin){
            this.plugin = plugin;
            setUpConfig(plugin);
            String autoSave = "autoSave";
            String autoRestart = "autoRestart";
            FileConfiguration config = plugin.getConfig();
            autoSaveTime = config.getInt(autoSave);
            autoRestartTime = config.getInt(autoRestart);
        }

    public static int getAutoSaveTime(){
        return autoSaveTime;
    }

    public static int getAutoRestartTime(){
        return autoRestartTime;
    }

    private void setUpConfig(Main plugin){
        String autoSave = "autoSave";
        String autoRestart = "autoRestart";
        FileConfiguration config = plugin.getConfig();
        if (config.get(autoSave) == null){
            config.addDefault(autoSave, 30);
        }
        if (config.get(autoRestart) == null){
            config.addDefault(autoRestart, (60 * 6));
        }
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}
