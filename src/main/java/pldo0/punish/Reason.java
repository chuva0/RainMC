package pldo0.punish;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum Reason {

    HACK("Programas ilegais", new ItemStack(Material.BARRIER)),
    BUG("Abuso de Bugs", new ItemStack(Material.BARRIER)),
    FLOOD("Flood no chat", new ItemStack(Material.BARRIER)),
    SPAM("Spam no chat", new ItemStack(Material.BARRIER)),
    DIVULGACAO("Divulgacao dispersa", new ItemStack(Material.BARRIER)),
    ANTIJOGO("Antijogo", new ItemStack(Material.BARRIER)),
    GRIFFIN("Griffin", new ItemStack(Material.BARRIER)),
    ;

    private String reason;
    private ItemStack item;

    Reason(String reason, ItemStack item) {
        this.reason = reason;
        this.item = item;
    }

    public String getReason() {
        return reason;
    }

    public ItemStack getItem() {
        return item;
    }
}
