package FWmain.events;

import FWmain.FileManager.FactionData;
import com.sun.xml.internal.ws.resources.SenderMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.security.acl.Owner;
import java.util.List;
import java.util.Set;

public class PlayerPVP implements Listener {
    @EventHandler
    public void BeAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        //取得攻擊者和被攻擊者
        Player player = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();
        //取得yaml物件
        FactionData fd = FactionData.getInstance();
        /*
        //取得領地玩家串列(Player是領地領主)
        List<String> list = fd.BeaconData.getStringList(player.getDisplayName() + ".player");
        if (list == null) {
            return;
        }
        //如果串列內包含Victim
        for (String name : list) {
            if (victim.getDisplayName().equals(name)) {
                //事件取消
                e.setCancelled(true);
            }
        }
        //如果串列內包含領主(Victim是領地領主)
        List<String> list2 = fd.BeaconData.getStringList(victim.getDisplayName() + ".player");
        if (list2 == null) {
            return;
        }
        //如果串列內包含Player
        for (String name : list2) {
            if (player.getDisplayName().equals(name)) {
                //事件取消
                e.setCancelled(true);
            }
        }

         */
        Set<String> set = fd.BeaconData.getKeys(false);
                for (String owner : set) {
                    List<String> members = fd.BeaconData.getStringList(owner + ".player");
                    if (members == null) {
                        continue;
                    }
            if (members.contains(player.getDisplayName()) && members.contains(victim.getDisplayName())) {
                e.setCancelled(true);
            }

        }
    }
}
