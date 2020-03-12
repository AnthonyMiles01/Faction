package FWmain.FileManager;

import FWmain.FactionWar;
import org.bukkit.block.Beacon;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sun.security.jca.GetInstance;

import java.io.File;
import java.io.IOException;

public class FactionData {
    public File file;
    public YamlConfiguration BeaconData;
    private static FactionData instance;
    public static FactionData getInstance(){
        if (instance == null){
            instance = new FactionData();
        }
        return instance;
    }

    private FactionData() {
        file = new File(FactionWar.plugin.getDataFolder(), "Beacon.yml");
        BeaconData = new YamlConfiguration();
        if (file.exists()) {
            read();
        }
    }

    public boolean save() {
        try {
            BeaconData.save(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean read() {
        try {
            BeaconData = new YamlConfiguration();
            BeaconData.load(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

}
