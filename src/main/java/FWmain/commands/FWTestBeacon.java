package FWmain.commands;

import FWmain.FileManager.FactionData;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.Set;
// 實作command executor 到這個程式頁面
public class FWTestBeacon implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("測試成功"); //送出訊息給指令輸入者
        FactionData factionData = FactionData.getInstance();
        Set<String> BeaconOwners = factionData.BeaconData.getKeys(false);

        for (String owner : BeaconOwners) {
            if (factionData.BeaconData.get(owner) != null) { //判斷檔案中是否有owner, 判斷是否為空
                commandSender.sendMessage("資料存在");//送出訊息給指令輸入者
                Integer x = factionData.BeaconData.getInt(owner + ".x");
                Integer y = factionData.BeaconData.getInt(owner + ".y");
                Integer z = factionData.BeaconData.getInt(owner + ".z");
                Player p = (Player) commandSender;
                Integer px = p.getLocation().getBlockX();
                Integer py = p.getLocation().getBlockY();
                Integer pz = p.getLocation().getBlockZ();

                if (p.getDisplayName().equals(owner)) {
                    if (Math.abs(px - x) < 25 && Math.abs(py - y) < 25 && Math.abs(pz - z) < 50) {
                        p.sendMessage("在領地內");
                        PotionEffect effect = new PotionEffect(PotionEffectType.HEAL, 20, 2);
                        p.addPotionEffect(effect);
                    }
                }
            }
        }
        return false;
    }
}
