package FWmain.commands;

import FWmain.FileManager.FactionData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FWaddplayer implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            String playername = args[0];

            FactionData factionData = FactionData.getInstance();
            if (sender instanceof Player) {
                Player p = (Player) sender; //Sender轉型成Player
                if (factionData.BeaconData.get(p.getDisplayName()) != null) {
                    List<String> players;
                    if (factionData.BeaconData.get(p.getDisplayName() + ".player") == null) {
                        players = new ArrayList<String>();
                    }else {
                        players = factionData.BeaconData.getStringList(p.getDisplayName() + ".player");
                    }
                    players.add(playername);
                    factionData.BeaconData.set(p.getDisplayName() + ".player", players);
                    factionData.save();
                }
            }
        }
        return false;
    }
}