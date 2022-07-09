package pldo0.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pldo0.kits.KitsItens;

import java.util.Arrays;

public class PunishmentInventory implements Listener {

    static int i = 0;

    public static void guiPunish(Player player, String target) {

        Inventory inv = Bukkit.createInventory(null, 36, "§cPunir " + target);

        i = 19;

        Arrays.asList(Reason.values()).forEach(e->{
            ItemStack item = e.getItem();
            ItemMeta it = item.getItemMeta();
            it.setDisplayName("§7"+e.getReason());
            item.setItemMeta(it);

            inv.setItem(i, item);
            i++;
        });

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sk = (SkullMeta) skull.getItemMeta();
        sk.setOwner(target);
        sk.setDisplayName(ChatColor.RED + target);
        skull.setItemMeta(sk);

        inv.setItem(4, skull);


        player.openInventory(inv);

    }
    @EventHandler
    void inv(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().contains("§cPunir ")) {

            String target = event.getView().getTitle().replace("§cPunir ", "");

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            Arrays.asList(Reason.values()).stream().forEach(e->{
                if (event.getCurrentItem().hasItemMeta()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "").equalsIgnoreCase(e.getReason())) {
                        PunishmentManager.punishPlayer(target, e);
                        player.sendMessage(ChatColor.GREEN + "Voce puniu " + target + ", motivo: " + e.getReason());
                        for (Player on : Bukkit.getOnlinePlayers()) {
                           if (!on.getName().equalsIgnoreCase(target)) {
                               on.sendMessage(ChatColor.RED + "                      [Punicao]");
                               on.sendMessage(ChatColor.RED + "");
                               on.sendMessage(ChatColor.GRAY + "O jogador " + ChatColor.WHITE + target + ChatColor.GRAY + " foi punido por " + ChatColor.RED + e.getReason());
                               on.sendMessage(ChatColor.GRAY + "");
                               on.sendMessage(ChatColor.RED + "                      [Punicao]");
                               on.playSound(on.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                           }
                        }
                    }
                }
            });
            event.setCancelled(true);
        }
    }
}
