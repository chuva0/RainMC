package pldo0.ranks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.awt.*;

public enum RanksType {

    TERRA(Material.DIRT, ChatColor.of(new Color(205, 166, 100)) + "Terra"),
    PEDRA(Material.COBBLESTONE, ChatColor.of(new Color(174, 185, 176)) + "Pedra"),
    LAPIS(Material.LAPIS_ORE, ChatColor.of(new Color(126, 134, 213)) + "Lapis"),
    FERRO(Material.IRON_ORE, ChatColor.of(new Color(195, 222, 212)) + "Ferro"),
    REDSTONE(Material.REDSTONE_ORE, ChatColor.of(new Color(145, 31, 57)) + "Redstone"),
    OURO(Material.GOLD_ORE, ChatColor.of(new Color(204, 173, 88)) + "Ouro"),
    DIAMANTE(Material.DIAMOND_ORE,ChatColor.of(new Color(105, 179, 194)) + "Diamante"),
    OBSIDIAN(Material.OBSIDIAN,ChatColor.of(new Color(61, 34, 108)) + "Obsidian"),
    BEDROCK(Material.BEDROCK,ChatColor.of(new Color(59, 59, 68)) + "Bedrock");

    Material m;
    String name;

    RanksType(Material m, String name) {
        this.m = m;
        this.name = name;
    }

    public Material getM() {
        return m;
    }

    public String getName() {
        return name;
    }
}
