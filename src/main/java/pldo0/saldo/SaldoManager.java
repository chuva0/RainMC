package pldo0.saldo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pldo0.grupos.GrupoManager;
import pldo0.grupos.Grupos;
import pldo0.ranks.RanksManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pldo0.Pldozero.*;

public class SaldoManager extends RanksManager {

    public long getSaldo(Player player) {
        if (!existPlayer(player)) {
            return 0l;
        }
        return getRankConfig().getConfig().getLong("Player." + player.getName() + ".Saldo");
    }
    public long getSaldo(String player) {
        if (!existPlayer(player)) {
            return 0l;
        }
        return getRankConfig().getConfig().getLong("Player." + player + ".Saldo");
    }
    public void removeSaldo(Player player, long remove) {
        if (getSaldo(player) - remove < 0) {
            setSaldo(player, 0);
            return;
        }
        getRankConfig().getConfig().set("Player." + player.getName() + ".Saldo", Math.subtractExact(getSaldo(player), remove));
        getRankConfig().saveConfig();
        setTab(player);
    }
    public void removeSaldo(String player, long remove) {
        if (getSaldo(player) - remove < 0) {
            setSaldo(player, 0);
            return;
        }
        getRankConfig().getConfig().set("Player." + player + ".Saldo", Math.subtractExact(getSaldo(player), remove));
        getRankConfig().saveConfig();
    }
    public void addSaldo(Player player, long add) {
        getRankConfig().getConfig().set("Player." + player.getName() + ".Saldo", Math.addExact(add, getSaldo(player)));
        getRankConfig().saveConfig();
        setTab(player);
    }
    public void addSaldo(String player, long add) {
        getRankConfig().getConfig().set("Player." + player + ".Saldo", Math.addExact(add, getSaldo(player)));
        getRankConfig().saveConfig();
    }
    public void setSaldo(String player, long set) {
        getRankConfig().getConfig().set("Player." + player + ".Saldo", set);
        getRankConfig().saveConfig();
    }
    public void setSaldo(Player player, long set) {
        getRankConfig().getConfig().set("Player." + player.getName() + ".Saldo", set);
        getRankConfig().saveConfig();
    }
    public void openSaldoAdm(Player player, Player target) {
        if (!existPlayer(target)) {
            player.sendMessage(ChatColor.RED + "Jogador nao existe!");
        } else {
            Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "Administrar economia");

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta zh = (SkullMeta) head.getItemMeta();
            zh.setOwner(target.getName());
            zh.setDisplayName(ChatColor.RED + "Administrando: " + target.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("  " + ChatColor.GREEN + "Saldo: " + ChatColor.GRAY + getSaldo(target));
            lore.add("  " + ChatColor.GREEN + "Grupo: " + ChatColor.GRAY + getGroup(player).toString());
            lore.add("  " + ChatColor.GREEN + "Rank: " + ChatColor.GRAY + getRank(player).toString());
            lore.add("  " + ChatColor.GREEN + "Data de entrada: " + ChatColor.GRAY + getData(player));
            lore.add(" ");
            zh.setLore(lore);
            head.setItemMeta(zh);

            inv.setItem(4, head);

            int i = 19;

            for (Grupos grupos : Grupos.values()) {
                ItemStack c = new ItemStack(grupos.getM());
                ItemMeta zc = c.getItemMeta();
                zc.setDisplayName(ChatColor.GREEN + "Atualizar para: " + grupos.toString());
                c.setItemMeta(zc);

                inv.setItem(i, c);

                i++;

            }

            ItemStack p1 = new ItemStack(Material.PAPER);
            ItemMeta zp1 = p1.getItemMeta();
            zp1.setDisplayName(ChatColor.GREEN + "Adicionar (500)");
            p1.setItemMeta(zp1);

            ItemStack p2 = new ItemStack(Material.PAPER);
            ItemMeta zp2 = p2.getItemMeta();
            zp2.setDisplayName(ChatColor.GREEN + "Remover (500)");
            p2.setItemMeta(zp2);

            inv.setItem(39, p1);
            inv.setItem(41, p2);

            player.openInventory(inv);
        }
    }
    public void openSaldoAdm(Player player, String target) {
        if (!existPlayer(target)) {
            player.sendMessage(ChatColor.RED + "Jogador nao existe!");
        } else {
            Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "Administrar economia");

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta zh = (SkullMeta) head.getItemMeta();
            zh.setOwner(target);
            zh.setDisplayName(ChatColor.RED + "Administrando: " + target);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("  " + ChatColor.GREEN + "Saldo: " + ChatColor.GRAY + getSaldo(target));
            lore.add("  " + ChatColor.GREEN + "Grupo: " + ChatColor.GRAY + getGroup(player).toString());
            lore.add("  " + ChatColor.GREEN + "Rank: " + ChatColor.GRAY + getRank(player).toString());
            lore.add("  " + ChatColor.GREEN + "Data de entrada: " + ChatColor.GRAY + getData(player));
            lore.add(" ");
            zh.setLore(lore);
            head.setItemMeta(zh);

            inv.setItem(4, head);

            int i = 19;

            for (Grupos grupos : Grupos.values()) {
                ItemStack c = new ItemStack(grupos.getM());
                ItemMeta zc = c.getItemMeta();
                zc.setDisplayName(ChatColor.GREEN + "Atualizar para: " + grupos.toString());
                c.setItemMeta(zc);

                inv.setItem(i, c);

                i++;
                if (i==26) {
                    i=29;
                }
                if (i==31) {
                    i = 32;
                }

            }

            ItemStack p1 = new ItemStack(Material.PAPER);
            ItemMeta zp1 = p1.getItemMeta();
            zp1.setDisplayName(ChatColor.GREEN + "Adicionar (500)");
            p1.setItemMeta(zp1);

            ItemStack p2 = new ItemStack(Material.PAPER);
            ItemMeta zp2 = p2.getItemMeta();
            zp2.setDisplayName(ChatColor.GREEN + "Remover (500)");
            p2.setItemMeta(zp2);

            inv.setItem(39, p1);
            inv.setItem(41, p2);

            player.openInventory(inv);
        }
    }
    public String getTop() {
        List<String> streamp = new ArrayList<>();
        streamp.clear();
        for (String players : getRankConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
            streamp.add(players);
        }
        Optional<Long> rank = streamp.stream().map(e -> getSaldo(e)).max(Comparator.naturalOrder());
        List<String> player = streamp.stream().filter(e -> getSaldo(e)==(rank.get())).limit(1).collect(Collectors.toList());
        return player.get(0);
    }
    public List getTop5() {
        List<String> streamp = new ArrayList<>();
        streamp.clear();
        for (String players : getRankConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
            streamp.add(players);
        }
        List<Long> rank = streamp.stream().map(e -> getSaldo(e)).sorted(Comparator.naturalOrder()).limit(5).collect(Collectors.toList());
        return rank;
    }

    public String formatMoney(Player player) {
        if (getSaldo(player) >= 1000l && getSaldo(player) < 999999l) {
            return getSaldo(player)/1000l + "k";
        }
        if (getSaldo(player) >= 1000000l && getSaldo(player) < 999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000001)) + "M";
        }
        if (getSaldo(player) >= 1000000000l && getSaldo(player) < 999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000001)) + "B";
        }
        if (getSaldo(player) >= 1000000000000l && getSaldo(player) < 999999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000000001)) + "T";
        }
        if (getSaldo(player) >= 1000000000000000l && getSaldo(player) < 999999999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000000000001)) + "Q";
        }
        return getSaldo(player) +"";
    }
    public String formatMoney(long amount) {
        if (amount >= 1000l && amount < 999999l) {
            return amount/1000l + "k";
        }
        if (amount >= 1000000l && amount < 999999999l) {
            return new DecimalFormat("#.0").format((amount*0.000001)) + "M";
        }
        if (amount >= 1000000000l && amount < 999999999999l) {
            return new DecimalFormat("#.0").format((amount*0.000000001)) + "B";
        }
        if (amount >= 1000000000000l && amount < 999999999999999l) {
            return new DecimalFormat("#.0").format((amount*0.000000000001)) + "T";
        }
        if (amount >= 1000000000000000l && amount < 999999999999999999l) {
            return new DecimalFormat("#.0").format((amount*0.000000000000001)) + "Q";
        }
        return amount +"";
    }
    public String formatMoney(String player) {
        if (getSaldo(player) >= 1000l && getSaldo(player) < 999999l) {
            return getSaldo(player)/1000l + "k";
        }
        if (getSaldo(player) >= 1000000l && getSaldo(player) < 999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000001)) + "M";
        }
        if (getSaldo(player) >= 1000000000l && getSaldo(player) < 999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000001)) + "B";
        }
        if (getSaldo(player) >= 1000000000000l && getSaldo(player) < 999999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000000001)) + "T";
        }
        if (getSaldo(player) >= 1000000000000000l && getSaldo(player) < 999999999999999999l) {
            return new DecimalFormat("#.0").format((getSaldo(player)*0.000000000000001)) + "Q";
        }
        return getSaldo(player) +"";
    }
}
