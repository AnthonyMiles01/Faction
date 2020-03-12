package FWmain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class FWsort implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("玩家才能用這個指令歐OAO");
            return false;
        }
        Player player = (Player) commandSender;

        Inventory inv = player.getInventory();

        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

//        for (ItemStack stack : inv) {
//            if (stack != null) {
//                array.add(stack);
//            }
//        }


//        for (int i = 0; i < ; i++) {
//            inv.remove(i);
//        }


        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size() - 1-i; j++){
                if (array.get(j).getTypeId() > array.get(j + 1).getTypeId()){
                    ItemStack tmp = array.get(j);
                    array.set(j, array.get(j+1));
                    array.set(j + 1, tmp);
                }
            }
        }
        for(ItemStack i: array){
            inv.addItem(i);
        }

        return false;
    }
}
