package pldo0.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pldo0.grupos.Grupos;
import pldo0.utils.BukkitItemStack;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static pldo0.Pldozero.*;

public class KitsItens extends KitManager{


    public static List<ItemStack> kitPvP() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.IRON_SWORD).setDisplayName(ChatColor.GREEN + "Espada").finish());
        itens.add(new BukkitItemStack(Material.IRON_HELMET).setDisplayName(ChatColor.GREEN + "Capacete").finish());
        itens.add(new BukkitItemStack(Material.IRON_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete").finish());
        itens.add(new BukkitItemStack(Material.IRON_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça").finish());
        itens.add(new BukkitItemStack(Material.IRON_BOOTS).setDisplayName(ChatColor.GREEN + "Botas").finish());
        itens.add(new BukkitItemStack(Material.GOLDEN_APPLE, 16).setDisplayName(ChatColor.GREEN + "Maça dourada").finish());

        return itens;
    }
    public static List<ItemStack> kitMineracao() {
        ArrayList<ItemStack> itens = new ArrayList<>();
        itens.add(new BukkitItemStack(Material.IRON_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta")
                        .addEnchantment(Enchantment.DIG_SPEED, 2)
                .finish());
        return itens;
    }
    public static boolean isCooldown(Player player, Kits kit) {
        if(getRankConfig().getConfig().getLong("Player." + player.getName() + ".Kit."+kit.getNome()+".Cooldown") > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }
    static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static String getCooldown(Player player, Kits kit) {
        return format.format((getRankConfig().getConfig().getLong("Player." + player.getName() + ".Kit."+kit.getNome()+".Cooldown") - System.currentTimeMillis()));
    }
    public static String formatador(long number) {
        long tempo = number/1000;
        if (tempo > 60 && tempo < 3599) {
            long minutos = tempo/60;
            long segundos = tempo%60;
            return minutos + "m " + segundos + "s";
        }
        if (tempo > 3600 && tempo < (3600*24)-1) {
            long hora = tempo/3600;
            long minutos = (tempo%3600)/60;
            long segundos = (tempo%3600)%60;
            return hora + "h:" + minutos + "m:" + segundos + "s";
        }
        if (tempo > 3600*24) {
            long dias = tempo/(3600*24);
            long resto = tempo%(3600*24);
            long horas = resto/3600;
            long minutos = (resto%3600)/60;
            long segundos = (resto%3600)%60;

            return dias + "d:" + horas + "h:" + minutos + "m:" + segundos + "s";
        }

        return tempo + "s";
    }
    public static void setCooldown(Player player, Kits kit) {
        long cooldown = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(kit.getCooldown());
        getRankConfig().getConfig().set("Player." + player.getName() + ".Kit."+kit.getNome()+".Cooldown", cooldown);
        getRankConfig().saveConfig();
    }
    public static List<ItemStack> kitMensal() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada")
                .addEnchantment(Enchantment.DAMAGE_ALL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 24).setDisplayName(ChatColor.GREEN + "Maça dourada").finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta")
                .addEnchantment(Enchantment.DIG_SPEED, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchantment(Enchantment.DURABILITY, 3).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira").finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira").finish());
        return itens;
    }
    public static List<ItemStack> kitSemanal() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.IRON_SWORD).setDisplayName(ChatColor.GREEN + "Espada")
                .addEnchantment(Enchantment.DAMAGE_ALL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.IRON_HELMET).setDisplayName(ChatColor.GREEN + "Capacete")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.IRON_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.IRON_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.IRON_BOOTS).setDisplayName(ChatColor.GREEN + "Botas")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 12).setDisplayName(ChatColor.GREEN + "Maça dourada").finish());
        itens.add(new BukkitItemStack(Material.IRON_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta")
                .addEnchantment(Enchantment.DIG_SPEED, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchantment(Enchantment.DURABILITY, 3).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira").finish());
        return itens;
    }
    public static List<ItemStack> kitMensal_Chuva() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.NETHERITE_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 7)
                        .addEnchantment(Enchantment.DURABILITY, 7)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 7)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 7)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 7)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 7)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 7)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 7)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 7)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 7)
                .addEnchantment(Enchantment.PROTECTION_FALL, 7)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 36).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 7).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 7).addEnchantment(Enchantment.DURABILITY, 7).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitMensal_Tespestade() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.NETHERITE_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 9)
                        .addEnchantment(Enchantment.DURABILITY, 9)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 9)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 9)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 9)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 9)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 9)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 9)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 9)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 9)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 9)
                .addEnchantment(Enchantment.PROTECTION_FALL, 8)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 56).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 9).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 9).addEnchantment(Enchantment.DURABILITY, 9).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitMensal_Temporal() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.NETHERITE_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 10)
                        .addEnchantment(Enchantment.DURABILITY, 10)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 10)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 10)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 10)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10)
                .finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 10)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10)
                .addEnchantment(Enchantment.PROTECTION_FALL, 9)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.NETHERITE_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 11).addEnchantment(Enchantment.DURABILITY, 10).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitSemanal_Temporal() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 8)
                        .addEnchantment(Enchantment.DURABILITY, 8)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 8)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 8)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 8)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 8)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 8)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 8)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 8)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 8)
                .addEnchantment(Enchantment.PROTECTION_FALL, 9)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 8).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 9).addEnchantment(Enchantment.DURABILITY, 8).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitDiario_Temporal() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 6)
                        .addEnchantment(Enchantment.DURABILITY, 6)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .addEnchantment(Enchantment.PROTECTION_FALL, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPORAL.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 6).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 7).addEnchantment(Enchantment.DURABILITY, 6).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPORAL.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitSemanal_Chuva() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 5)
                        .addEnchantment(Enchantment.DURABILITY, 6)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 5)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 5)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 5)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 5)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 5)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 5)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 5)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 5)
                .addEnchantment(Enchantment.PROTECTION_FALL, 5)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 28).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5).addEnchantment(Enchantment.DURABILITY, 5).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitSemanal_Tempestade() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 5)
                        .addEnchantment(Enchantment.DURABILITY, 6)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 6)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 6)
                .addEnchantment(Enchantment.PROTECTION_FALL, 5)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 28).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5).addEnchantment(Enchantment.DURABILITY, 5).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitDiario_Tempestade() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 4)
                        .addEnchantment(Enchantment.DURABILITY, 4)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 4)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 4)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 4)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 4)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4)
                .addEnchantment(Enchantment.PROTECTION_FALL, 4)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 18).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.TEMPESTADE.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 4).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 4).addEnchantment(Enchantment.DURABILITY, 4).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.TEMPESTADE.getPrefix()).finish());
        return itens;
    }
    public static List<ItemStack> kitDiario_Chuva() {

        ArrayList<ItemStack> itens = new ArrayList<>();

        itens.add(new BukkitItemStack(Material.DIAMOND_SWORD).setDisplayName(ChatColor.GREEN + "Espada " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DAMAGE_ALL, 3)
                        .addEnchantment(Enchantment.DURABILITY, 3)
                        .addEnchantment(Enchantment.FIRE_ASPECT, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_HELMET).setDisplayName(ChatColor.GREEN + "Capacete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 3)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.GREEN + "Colete " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 3)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_LEGGINGS).setDisplayName(ChatColor.GREEN + "Calça " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 3)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_BOOTS).setDisplayName(ChatColor.GREEN + "Botas " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .addEnchantment(Enchantment.PROTECTION_FIRE, 3)
                .addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3)
                .addEnchantment(Enchantment.PROTECTION_FALL, 3)
                .finish());
        itens.add(new BukkitItemStack(Material.ENCHANTED_GOLDEN_APPLE, 20).setDisplayName(ChatColor.GREEN + "Maça dourada " + Grupos.CHUVA.getPrefix()).finish());
        itens.add(new BukkitItemStack(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GREEN + "Picareta " + Grupos.CHUVA.getPrefix())
                .addEnchantment(Enchantment.DIG_SPEED, 3).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchantment(Enchantment.DURABILITY, 3).finish());
        itens.add(new BukkitItemStack(Material.DARK_OAK_WOOD, 64).setDisplayName(ChatColor.GREEN + "Madeira " + Grupos.CHUVA.getPrefix()).finish());
        return itens;
    }
}
