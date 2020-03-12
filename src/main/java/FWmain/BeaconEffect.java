package FWmain;

import FWmain.FileManager.FactionData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Set;

public class BeaconEffect implements Runnable {
    public void run() {
        FactionData factionData = FactionData.getInstance();
        Set<String> BeaconOwners = factionData.BeaconData.getKeys(false);
        for (String owner : BeaconOwners) {
            List<String> members = factionData.BeaconData.getStringList(owner + ".player");
            if (members == null) {
                continue;
            }
        }

        for (String owner : BeaconOwners) {
            if (factionData.BeaconData.get(owner) != null) { //判斷檔案中是否有owner, 判斷是否為空
                Integer x = factionData.BeaconData.getInt(owner + ".x");
                Integer y = factionData.BeaconData.getInt(owner + ".y");
                Integer z = factionData.BeaconData.getInt(owner + ".z");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    Integer px = p.getLocation().getBlockX();
                    Integer py = p.getLocation().getBlockY();
                    Integer pz = p.getLocation().getBlockZ();

                    if (p.getDisplayName().equals(owner)) {
                        if (Math.abs(px - x) < 25 && Math.abs(py - y) < 25 && Math.abs(pz - z) < 50) {
                            PotionEffect effect = new PotionEffect(PotionEffectType.HEAL, 40, 2);
                            p.addPotionEffect(effect, true);
                        }
                    }
                }
            }
        }
    }
}
