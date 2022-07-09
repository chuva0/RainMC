package pldo0.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class BukkitItemStack {

    ItemStack is;
    ItemMeta im;

    public BukkitItemStack(Material m) {
        is = new ItemStack(m);

        im = is.getItemMeta();
    }
    public BukkitItemStack(Material m, int amount) {
        is = new ItemStack(m, amount);

        im = is.getItemMeta();
    }
    public BukkitItemStack setDisplayName(String name) {
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }
    public BukkitItemStack setLore(ArrayList<String> lore) {
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }
    public BukkitItemStack isUnbreaking(boolean b) {
        im.setUnbreakable(b);
        is.setItemMeta(im);
        return this;
    }
    public BukkitItemStack addEnchantment(Enchantment enchantment, int lvl) {
        im.addEnchant(enchantment, lvl, true);
        is.setItemMeta(im);
        return this;
    }
    public BukkitItemStack addItemFlag(ItemFlag flag) {
        im.addItemFlags(flag);
        is.setItemMeta(im);
        return this;
    }
    public ItemStack finish() {
        is.setItemMeta(im);
        return is;
    }
}
