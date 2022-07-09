package pldo0.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pldo0.saldo.SaldoManager;

import java.awt.*;
import java.util.Collection;

import static pldo0.Pldozero.getInstance;

public class MoneyByBlock extends SaldoManager {

    public void addMoney(Player player, Block block) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (block.getType().equals(Material.LAPIS_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*10)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*10)*((getRank(player).getMult())).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.LAPIS_ORE);
                    }
                }.runTaskLater(getInstance(), 400L);
            }
        }
        if (block.getType().equals(Material.COAL_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*16)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*16)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.COAL_ORE);
                    }
                }.runTaskLater(getInstance(), 300L);
            }
        }
        if (block.getType().equals(Material.DIAMOND_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*270)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*270)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.DIAMOND_ORE);
                    }
                }.runTaskLater(getInstance(), 600L);
            }
        }
        if (block.getType().equals(Material.STONE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*10)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*10)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.STONE);
                    }
                }.runTaskLater(getInstance(), 100L);
            }
        }
        if (block.getType().equals(Material.REDSTONE_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*35)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*35)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.REDSTONE_ORE);
                    }
                }.runTaskLater(getInstance(), 500L);
            }
        }
        if (block.getType().equals(Material.GOLD_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*25)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*25)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.GOLD_ORE);
                    }
                }.runTaskLater(getInstance(), 500L);
            }
        }
        if (block.getType().equals(Material.IRON_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*15)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*15)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.IRON_ORE);
                    }
                }.runTaskLater(getInstance(), 500L);
            }
        }
        if (block.getType().equals(Material.EMERALD_ORE)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*320)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*320)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.EMERALD_ORE);
                    }
                }.runTaskLater(getInstance(), 800L);
            }
        }
        if (block.getType().equals(Material.OBSIDIAN)) {
            Collection<ItemStack> dropss = block.getDrops(player.getInventory().getItemInMainHand());
            if (player.getFoodLevel()<=18) {
                player.setFoodLevel(player.getFoodLevel()+2);
            }
            for (ItemStack drops : dropss) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.of(new Color(33, 138, 70)) +
                        "Voce recebeu R$ " + Math.round((drops.getAmount()*700)*((getRank(player).getMult())))));
                addSaldo(player, (drops.getAmount()*700)*(getRank(player).getMult()).longValue());
                setTab(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.BEDROCK);
                    }
                }.runTaskLater(getInstance(), 0);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        block.setType(Material.OBSIDIAN);
                    }
                }.runTaskLater(getInstance(), 1200L);
            }
        }
    }

}
