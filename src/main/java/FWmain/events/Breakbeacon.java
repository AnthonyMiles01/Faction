package FWmain.events;

import FWmain.FileManager.FactionData;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Breakbeacon implements Listener {
    @EventHandler
    public void BreakBeacon(BlockBreakEvent event) {
        if (!event.getBlock().getType().equals(Material.BEACON)) {
            return;
        }
        FactionData BeaconBreak = FactionData.getInstance();
        Integer x = event.getBlock().getLocation().getBlockX();
        Integer y = event.getBlock().getLocation().getBlockY();
        Integer z = event.getBlock().getLocation().getBlockZ();

        for (String owner : BeaconBreak.BeaconData.getKeys(false)) {
            if (BeaconBreak.BeaconData.getInt(owner + ".x") == x) {
                if (BeaconBreak.BeaconData.getInt(owner + ".y") == y) {
                    if (BeaconBreak.BeaconData.getInt(owner + ".z") == z) {
                        BeaconBreak.BeaconData.set(owner, null);
                        BeaconBreak.save();
                    }
                }
            }
        }

    }
}
