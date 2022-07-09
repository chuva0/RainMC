package pldo0.ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pldo0.grupos.Grupos;
import pldo0.saldo.Saldo;
import pldo0.saldo.SaldoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RanksInvetory extends SaldoManager implements Listener {

    public static void ranksInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Ranks");

        int i = 11;

        for (RanksType type : RanksType.values()) {
            ItemStack item = new ItemStack(type.getM());
            ItemMeta zitem = item.getItemMeta();
            zitem.setDisplayName(type.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            for (Ranks ranks : Ranks.values()) {
                if (ranks.getType().equals(type)) {
                    if (!(new RanksManager().getRank(player).getOrder() < ranks.getOrder())) {
                        lore.add(ChatColor.GREEN +"  ✔ " + ranks.getPrefix() + " : R$" + ranks.getCost() + ",00");
                    } else {
                        lore.add(ChatColor.RED +"  ✖ " + ranks.getPrefix() + " : R$" + ranks.getCost() + ",00");
                    }
                    lore.add(" ");
                }
            }
            lore.add(" ");
            zitem.setLore(lore);
            item.setItemMeta(zitem);

            inv.setItem(i, item);

            i++;
            if (i == 16) {
                i = 21;
            }
            if (i == 24) {
                i = 31;
            }
            if (i == 33) {
                i = 40;
            }
            if (i == 42) {
                i = 49;
            }

        }

        player.openInventory(inv);
    }
    @EventHandler
    void click(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Ranks")) {

            if(event.getCurrentItem()==null){return;}
            if(event.getClickedInventory()==null){return;}

            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.RED + "Administrar economia")) {

            if(event.getCurrentItem()==null){return;}
            if(event.getClickedInventory()==null){return;}

            event.setCancelled(true);
        }
    }
}
