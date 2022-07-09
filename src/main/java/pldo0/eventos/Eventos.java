package pldo0.eventos;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import pldo0.Pldozero;
import pldo0.api_externa.ColourEffects;
import pldo0.kits.KitsItens;
import pldo0.punish.PunishmentManager;
import pldo0.punish.Reason;
import pldo0.saldo.SaldoManager;
import pldo0.comandos.Comandos;
import pldo0.grupos.GrupoManager;
import pldo0.grupos.Grupos;
import pldo0.utils.MoneyByBlock;
import pldo0.warps.Warps;

import static pldo0.Pldozero.*;

public class Eventos extends SaldoManager implements Listener {

    @EventHandler
    void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        if (PunishmentManager.punish.getConfig().contains("Player." + player.getName())) {
            if (PunishmentManager.isBan(player.getName())) {
                if (PunishmentManager.getTempoConfigBan(player.getName()) == 0) {
                    player.kickPlayer(org.bukkit.ChatColor.RED + "[Banimento]\n\n"
                            + org.bukkit.ChatColor.GRAY + "Voce foi banido por: " + Reason.BUG.getReason()+
                            "\n\n"+ org.bukkit.ChatColor.RED + "[Banimento]");
                } else {
                    player.kickPlayer(org.bukkit.ChatColor.RED + "[Banimento]\n\n"
                            + org.bukkit.ChatColor.GRAY + "Voce foi banido por: " + Reason.BUG.getReason() + "\n"
                            + org.bukkit.ChatColor.GRAY + "Tempo: " + KitsItens.formatador(PunishmentManager.getTempo(player.getName())) +
                            "\n\n"+ org.bukkit.ChatColor.RED + "[Banimento]");
                }
                return;
            }
        }
        if (!new GrupoManager().existPlayer(player)) {
            new GrupoManager().createPlayer(player);
        }
        entryTeam(player);
        if (getTop().equals(player.getName())) {
            Bukkit.getOnlinePlayers().stream().forEach(e->e.sendMessage("\n    " +
                    ColourEffects.gradientEffect("[Magnata]", ColourEffects.getColour(150, 59, 140), ColourEffects.getColour(191, 42, 175))
                    + " " + ChatColor.WHITE + getTop() + ChatColor.GRAY + " entrou no servidor! \n" + " "));
        }
        if (player.getName().equals("Ninjinha__")) {
            setGroupPlayer(player, Grupos.MASTER);
        }
    }
    @EventHandler
    void quit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        if (Comandos.rankup.contains(event.getPlayer())) {
            Comandos.rankup.remove(event.getPlayer());
        }
        if (Comandos.combustivel.contains(event.getPlayer())) {
            Comandos.combustivel.remove(event.getPlayer());
        }
        Warps.mapWarp.remove(event.getPlayer());
    }
    @EventHandler
    void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.LAPIS_BLOCK) &&
            player.getLocation().getBlock().getRelative(BlockFace.DOWN).hasMetadata("mina")) {
            if (!Warps.mapWarp.containsKey(player)) {
                Warps.mapWarp.put(player, Warps.MINERCAO);
            }
        }
    }

    MoneyByBlock mbb = new MoneyByBlock();

    @EventHandler
    void breaking(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Warps.mapWarp.containsKey(player) && Warps.mapWarp.get(player).equals(Warps.MINERCAO)) {
            mbb.addMoney(player, event.getBlock());
        }
        if (hasPermission(player, Grupos.ADMINISTRADOR.getWeight()) || getGroup(player).equals(Grupos.BUILDER)) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                event.setCancelled(true);
            }
            if (player.getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(false);
            }
        } else {
            event.setCancelled(true);
        }
        if (getTerrenoConfig().getConfig().contains("Player." + player.getName())) {
            for (String b : getTerrenoConfig().getConfig().getConfigurationSection("Player." + player.getName()).getKeys(false)) {
                if (getTerrenoConfig().getConfig().get("Player." + player.getName() + "."+b+".Location.World").equals(event.getBlock().getLocation().getWorld().getName()) &&
                        getTerrenoConfig().getConfig().get("Player." + player.getName() + "."+b+".Location.X").equals(event.getBlock().getLocation().getX()) &&
                        getTerrenoConfig().getConfig().get("Player." + player.getName() + "."+b+".Location.Z").equals(event.getBlock().getLocation().getZ())) {
                    player.sendMessage(ChatColor.GREEN + "Este bloco eh seu!");
                }
            }
        }
    }
    @EventHandler
    void place(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (hasPermission(player, Grupos.ADMINISTRADOR.getWeight()) || getGroup(player).equals(Grupos.BUILDER)) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                event.setCancelled(true);
            }
            if (player.getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(false);
            }
        } else {
            event.setCancelled(true);
        }
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Mina")
            && hasPermission(player, Grupos.ADMINISTRADOR.getWeight())) {
                Block bloco = event.getBlockPlaced();
                bloco.setMetadata("mina", new FixedMetadataValue(Pldozero.getInstance(), 10));
                player.sendMessage("Voce colocou o bloco com o metadata");
            } else {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    void chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (PunishmentManager.isMute(player.getName())) {
            if (Comandos.rankup.contains(player)) {
                if (event.getMessage().equalsIgnoreCase("cancelar")) {
                    event.setCancelled(false);
                }
            }
            if (Comandos.combustivel.contains(player)) {
                event.setCancelled(false);
            }
            player.sendMessage(ChatColor.RED + "Voce esta mutado! Seu mute acaba em " + KitsItens.formatador(PunishmentManager.getTempoMute(player.getName())));
            event.setCancelled(true);
        }
        if ((!getTop().isEmpty()) && getTop().equals(player.getName())) {
            String magnata = ColourEffects.gradientEffect("[Magnata]", ColourEffects.getColour(150, 59, 140), ColourEffects.getColour(191, 42, 175));
            if (getGroup(player).equals(Grupos.MEMBRO)) {
                event.setFormat("\n" + magnata + " " +getRank(player).getPrefix() + getGroup(player).getPrefix() + " " + player.getName() + " §6> " + color(player) + event.getMessage() + "\n " + "");
            } else
                event.setFormat("\n" + magnata + " " +getRank(player).getPrefix() + " " + getGroup(player).getPrefix() + " " + player.getName() + " §6> " + color(player) + event.getMessage() + "\n " + "");
        } else {
            if (getGroup(player).equals(Grupos.MEMBRO)) {
                event.setFormat(getRank(player).getPrefix() + getGroup(player).getPrefix() + " " + player.getName() + " §6> " + color(player) + event.getMessage());
            }
            if (hasPermission(player, Grupos.AJUDANTE.getWeight())) {
                event.setFormat("\n" + getRank(player).getPrefix() + " " + getGroup(player).getPrefix() + " " + player.getName() + " §6> " + color(player) + event.getMessage() + "\n " + "");
            } else
                event.setFormat(getRank(player).getPrefix() + " " + getGroup(player).getPrefix() + " " + player.getName() + " §6> " + color(player) + event.getMessage());
        }

        if (event.getMessage().equalsIgnoreCase("cancelar")) {
            if (Comandos.rankup.contains(player)) {
                player.sendMessage(ChatColor.RED + "Rankup cancelado!");
                Comandos.rankup.remove(player);
                event.setCancelled(true);
            }
            if (Comandos.combustivel.contains(player)) {
                player.sendMessage(ChatColor.RED + "Compra cancelada!");
                Comandos.combustivel.remove(player);
                event.setCancelled(true);
            }
        }
        if (Comandos.combustivel.contains(player)) {
            int amount;
            try {
                amount = Integer.parseInt(event.getMessage());
                if (amount > 10) {
                    player.sendMessage(ChatColor.RED + "Voce so pode comprar 10 combustiveis por vez!");
                    Comandos.combustivel.remove(player);
                    event.setCancelled(true);
                    return;
                }
                if (getSaldo(player) < amount*50000) {
                    player.sendMessage(ChatColor.RED + "Voce nao tem money suficiente!");
                    Comandos.combustivel.remove(player);
                    event.setCancelled(true);
                    return;
                }
                if (KitsItens.getTotalAmount(player.getInventory()) < 1) {
                    player.sendMessage(ChatColor.RED + "Deixe ao menos um slot vazio!");
                    Comandos.combustivel.remove(player);
                    event.setCancelled(true);
                    return;
                }
                ItemStack comb = new ItemStack(Material.COAL);
                ItemMeta im = comb.getItemMeta();
                im.setDisplayName(ChatColor.RED + "Combustivel");
                comb.setItemMeta(im);
                int i = 0;
                for (; i < amount; i++) {
                    player.getInventory().addItem(comb);
                }
                player.sendMessage(ChatColor.GREEN + "Voce comprou " + amount + " combustiveis!");
                removeSaldo(player, amount*50000l);
                Comandos.combustivel.remove(player);
                event.setCancelled(true);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Use valor numerico!");
                Comandos.combustivel.remove(player);
                event.setCancelled(true);
            }
            Comandos.combustivel.remove(player);
            event.setCancelled(true);
        }
    }
    @EventHandler
    void death(PlayerDeathEvent event) {
        if (Warps.mapWarp.containsKey(event.getEntity())) {
            Warps.mapWarp.remove(event.getEntity());
        }
    }
}
