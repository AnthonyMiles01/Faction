package FWmain;

import FWmain.commands.FWTestBeacon;
import FWmain.commands.FWaddplayer;
import FWmain.commands.FWsort;
import FWmain.events.Breakbeacon;
import FWmain.events.Placebeacon;
import FWmain.events.PlayerPVP;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionWar extends JavaPlugin {
    public static FactionWar plugin;

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("【FWmain.FactionWar】領地大戰插件讀取完成");
        Bukkit.getPluginManager().registerEvents(new Placebeacon(), this);
        Bukkit.getPluginManager().registerEvents(new Breakbeacon(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPVP(), this);
        Bukkit.getPluginCommand("FWAP").setExecutor(new FWaddplayer());
        Bukkit.getPluginCommand("test").setExecutor(new FWTestBeacon());
        Bukkit.getPluginCommand("sort").setExecutor(new FWsort());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BeaconEffect(), 2L, 2L);

    }


}
