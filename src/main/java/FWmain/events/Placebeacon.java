package FWmain.events;

import FWmain.FileManager.FactionData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Placebeacon implements Listener {
    @EventHandler
    public void Ownershipevent(BlockPlaceEvent e) {
        //判斷物品是否為烽火台
        if (e.getBlockPlaced().getType().equals(Material.BEACON)) {
            if (e.getItemInHand().getType().equals(Material.BEACON)) {
                //取得手上物品的ItemMeta
                ItemMeta itemMeta = e.getItemInHand().getItemMeta();
                //判斷是否有DisplayName, 以及是否符合
                if (itemMeta.getDisplayName() != null &&
                        itemMeta.getDisplayName().equals("領地能量核心")) {
                    //取得Lore
                    List<String> lore = itemMeta.getLore();
                    ArrayList<String> beaconLore = new ArrayList<String>();
                    beaconLore.add("test");
                    boolean loreCmp = true;
                    //判斷lore是否相同
                    if (lore != null &&
                            lore.size() == beaconLore.size()) {
                        for (int i = 0; i < lore.size(); i++) {
                            if (!lore.get(i).equals(beaconLore.get(i))) {
                                loreCmp = false;
                            }
                        }
                    }
                    //如果名稱和Lore完全相同, 比較玩家檔案, 確認還沒放過
                    if (loreCmp) {


                        FactionData factionData = FactionData.getInstance();
                        String playerName = e.getPlayer().getDisplayName();
                        if (factionData.BeaconData.get(playerName) != null) {
                            e.setCancelled(true);
                            return;
                        }
                        e.getPlayer().sendMessage(ChatColor.BLUE + "領地能量核心放置完成");
                        factionData.BeaconData.set(playerName + ".x", e.getBlock().getLocation().getBlockX());
                        factionData.BeaconData.set(playerName + ".y", e.getBlock().getLocation().getBlockY());
                        factionData.BeaconData.set(playerName + ".z", e.getBlock().getLocation().getBlockZ());
                        ArrayList <String> members = new ArrayList<String>();
                        members.add(playerName);
                        factionData.BeaconData.set(playerName + ".player",members );
                        factionData.save();


                    }
                }
            }
        }
    }
}
