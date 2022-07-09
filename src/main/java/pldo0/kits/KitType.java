package pldo0.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pldo0.utils.BukkitItemStack;

public enum KitType {

    BASICO(new BukkitItemStack(Material.BREAD).setDisplayName(ChatColor.GREEN + "Kits basicos").finish()),
    VIP(new BukkitItemStack(Material.DIAMOND).setDisplayName(ChatColor.GREEN + "Kits vips").finish()),
    ESPECIAL(new BukkitItemStack(Material.GOLD_INGOT).setDisplayName(ChatColor.GREEN + "Kits especiais").finish()),
    ;

    ItemStack item;

    KitType(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }
}
