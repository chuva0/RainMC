package pldo0.warps;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public enum Warps {

    SPAWN(ChatColor.GREEN + "Spawn"),
    MINERCAO(ChatColor.GREEN + "Mineracao"),
    PVP(ChatColor.RED + "PvP");

    public static Map<Player, Warps> mapWarp = new HashMap<>();
    String nome;

    Warps(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
