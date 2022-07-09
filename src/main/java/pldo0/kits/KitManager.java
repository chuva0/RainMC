package pldo0.kits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pldo0.grupos.Grupos;
import pldo0.saldo.SaldoManager;

public class KitManager extends SaldoManager {

    public void sendKit(Player player, Kits kit) {

        kit.getItens().stream().forEach(e->player.getInventory().addItem(e));
        KitsItens.setCooldown(player, kit);

    }
    public static int getTotalAmount(Inventory inventory) {
        int amount = 36;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                amount -= 1;
            }
        }
        return amount;
    }
}
