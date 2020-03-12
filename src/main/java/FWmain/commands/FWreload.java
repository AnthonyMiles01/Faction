package FWmain.commands;

import FWmain.FileManager.FactionData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FWreload implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                FactionData reload = FactionData.getInstance();
                reload.read();
            } else if (args[0].equals("save")){
                FactionData save = FactionData.getInstance();
                save.save();
            }
        }

        return true;
    }
}


