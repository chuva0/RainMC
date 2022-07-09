package pldo0.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pldo0.grupos.Grupos;
import pldo0.utils.BukkitItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public enum Kits {

    PVP("PvP","PvP",
            30,
            ChatColor.GREEN + "Kit basico de combate",
            KitsItens.kitPvP(), KitType.BASICO, new BukkitItemStack(Material.GOLDEN_SWORD).setDisplayName(ChatColor.GREEN + "Kit PvP").finish(), Grupos.MEMBRO),
    MINERACAO("Mineracao","Mineracao",
            15,
            ChatColor.GREEN + "Kit basico de mineracao",
            KitsItens.kitMineracao(), KitType.BASICO, new BukkitItemStack(Material.WOODEN_PICKAXE).setDisplayName(ChatColor.GREEN + "Kit Mineracao").finish(), Grupos.MEMBRO),

    MENSAL("Mensal", "Mensal_Comum",
            2592000,
            ChatColor.GREEN + "Kit mensal basico",
            KitsItens.kitMensal(), KitType.BASICO, new ItemStack(Material.DIAMOND_PICKAXE), Grupos.MEMBRO),
    SEMANAL("Semanal", "Semanal_Comum",
            604800,
            ChatColor.GREEN + "Kit semanal basico",
            KitsItens.kitSemanal(), KitType.BASICO, new ItemStack(Material.IRON_PICKAXE), Grupos.MEMBRO),

    MENSAL_CHUVA("Mensal do Vip Chuva", "Mensal_Chuva",
            2592000,
            ChatColor.GREEN + "Kit Vip Chuva",
            KitsItens.kitMensal_Chuva(), KitType.VIP, new ItemStack(Material.IRON_BLOCK), Grupos.CHUVA),
    SEMANAL_CHUVA("Semanal do Vip Chuva", "Semanal_Chuva",
            604800,
            ChatColor.GREEN + "Kit Vip Chuva",
            KitsItens.kitSemanal_Chuva(), KitType.VIP, new ItemStack(Material.IRON_ORE), Grupos.CHUVA),
    DIARIO_CHUVA("Diaria do Vip Chuva", "Diario_Chuva",
            86400,
            ChatColor.GREEN + "Kit Vip Chuva",
            KitsItens.kitDiario_Chuva(), KitType.VIP, new ItemStack(Material.IRON_INGOT), Grupos.CHUVA),

    MENSAL_TEMPESTADE("Mensal do Vip Tempestade", "Mensal_Tespestade",
            2592000,
            ChatColor.GREEN + "Kit Vip Tempestade",
            KitsItens.kitMensal_Tespestade(), KitType.VIP, new ItemStack(Material.GOLD_BLOCK), Grupos.TEMPESTADE),
    SEMANAL_TEMPESTADE("Semanal do Vip Tempestade", "Semanal_Tespestade",
            604800,
            ChatColor.GREEN + "Kit Vip Tempestade",
            KitsItens.kitSemanal_Tempestade(), KitType.VIP, new ItemStack(Material.GOLD_ORE), Grupos.TEMPESTADE),
    DIARIO_TEMPESTADE("Diario do Vip Tempestade", "Diario_Tespestade",
            86400,
            ChatColor.GREEN + "Kit Vip Tempestade",
            KitsItens.kitDiario_Tempestade(), KitType.VIP, new ItemStack(Material.GOLD_INGOT), Grupos.TEMPESTADE),

    MENSAL_TEMPORAL("Mensal do Vip Temporal", "Mensal_Temporal",
            2592000,
            ChatColor.GREEN + "Kit Vip Temporal",
            KitsItens.kitMensal_Temporal(), KitType.VIP, new ItemStack(Material.DIAMOND_BLOCK), Grupos.TEMPORAL),
    SEMANAL_TEMPORAL("Semanal do Vip Temporal", "Semanal_Temporal",
            604800,
            ChatColor.GREEN + "Kit Vip Temporal",
            KitsItens.kitSemanal_Temporal(), KitType.VIP, new ItemStack(Material.DIAMOND_ORE), Grupos.TEMPORAL),
    DIARIO_TEMPORAL("Diario do Vip Temporal", "Diario_Temporal",
            86400,
            ChatColor.GREEN + "Kit Vip Temporal",
            KitsItens.kitDiario_Temporal(), KitType.VIP, new ItemStack(Material.DIAMOND), Grupos.TEMPORAL),

    /*SEMANAL_STREAMER,
    DIARIO_STREAMER,

    SEMANAL_YOUTUBER,
    DIARIO_YOUTUBER,*/
    ;


    public ArrayList<String> loree;

    String displaynome;
    String nome;
    Integer cooldown;
    String lore;
    List<ItemStack> itens;
    ItemStack item_gui;
    KitType type;
    Grupos minGroup;

    Kits(String displaynome, String nome, Integer cooldown, String lore, List<ItemStack> itens, KitType type, ItemStack item_gui, Grupos minGroup) {
        this.displaynome = displaynome;
        this.nome = nome;
        this.cooldown = cooldown;
        this.lore = lore;
        this.itens = itens;
        this.type = type;
        this.item_gui = item_gui;
        this.minGroup = minGroup;
    }

    public String getDisplaynome() {
        return displaynome;
    }

    public String getNome() {
        return nome;
    }
    public KitType getType() {
        return type;
    }

    public List<ItemStack> getItens() {
        return itens;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public String getLore() {
        return lore;
    }

    public ItemStack getItem_gui() {
        return item_gui;
    }

    public Grupos getMinGroup() {
        return minGroup;
    }
}
