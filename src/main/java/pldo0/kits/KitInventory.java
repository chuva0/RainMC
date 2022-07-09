package pldo0.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pldo0.grupos.GrupoManager;
import pldo0.grupos.Grupos;
import pldo0.saldo.SaldoManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class KitInventory extends SaldoManager implements Listener {


    public static int i;
    public static void guiKits(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Kits");
        List<KitType> itens = Arrays.asList(KitType.values());
        i = 11;
        itens.stream().map(e->e.getItem()).forEach(e->{
            inv.setItem(i, e);
            i+=2;
        });
        player.openInventory(inv);
    }
    public static void guiKitsBasicos(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Kits basicos");



        i = 19;
        List<Kits> itens = Arrays.asList(Kits.values());

        ItemStack voltar = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta zvoltar = (SkullMeta) voltar.getItemMeta();
        zvoltar.setDisplayName(ChatColor.RED + "Voltar");
        zvoltar.setOwner("MHF_ArrowLeft");
        voltar.setItemMeta(zvoltar);

        inv.setItem(0, voltar);

        itens.stream().filter(e->e.getType().equals(KitType.BASICO)).forEach(e-> {

            ItemMeta im = e.getItem_gui().getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(e.getLore());
            if (KitsItens.isCooldown(player, e)) {
                lore.add(ChatColor.RED + "  Restam " + KitsItens.getCooldown(player, e) + "  ");
            } else {
                lore.add(ChatColor.GREEN + "    (Ja pode pegar)    ");
            }
            lore.add(" ");
            im.setLore(lore);
            im.setDisplayName(ChatColor.GREEN + e.getDisplaynome());
            e.getItem_gui().setItemMeta(im);

            inv.setItem(i, e.getItem_gui());
            i+=2;

            if (i==26) {
                i=28;
            }
            if (i==35) {
                i=37;
            }
            if (i==44) {
                i=46;
            }

        });



        player.openInventory(inv);
    }
    public static void guiKitsVips(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Kits vips");



        i = 19;
        List<Kits> itens = Arrays.asList(Kits.values());

        ItemStack voltar = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta zvoltar = (SkullMeta) voltar.getItemMeta();
        zvoltar.setDisplayName(ChatColor.RED + "Voltar");
        zvoltar.setOwner("MHF_ArrowLeft");
        voltar.setItemMeta(zvoltar);

        inv.setItem(0, voltar);

        itens.stream().filter(e->e.getType().equals(KitType.VIP)).forEach(e-> {

            ItemMeta im = e.getItem_gui().getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(e.getLore());
            if (new GrupoManager().getGroup(player).equals(e.getMinGroup())) {
                if (KitsItens.isCooldown(player, e)) {
                    lore.add(ChatColor.RED + "  Restam " + KitsItens.getCooldown(player, e) + "  ");
                } else {
                    lore.add(ChatColor.GREEN + "    (Ja pode pegar)    ");
                }
            } else {
                lore.add(ChatColor.RED + "  (Kit indisponivel)  ");
            }
            lore.add(" ");
            im.setLore(lore);
            im.setDisplayName(ChatColor.GREEN + e.getDisplaynome());
            e.getItem_gui().setItemMeta(im);

            inv.setItem(i, e.getItem_gui());
            i+=9;

            if (i==46) {
                i=22;
            }
            if (i==49) {
                i=25;
            }
        });



        player.openInventory(inv);
    }
    public static void guiAmostra(Player player, List<ItemStack> itens) {

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Amostra do kit");

        ItemStack voltar = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta zvoltar = (SkullMeta) voltar.getItemMeta();
        zvoltar.setDisplayName(ChatColor.RED + "Voltar");
        zvoltar.setOwner("MHF_ArrowLeft");
        voltar.setItemMeta(zvoltar);

        inv.setItem(0, voltar);

        i = 10;
        itens.stream().forEach(e->{
            inv.setItem(i, e);
            i++;
        });

        player.openInventory(inv);
    }
    public static void guiAmostraVip(Player player, List<ItemStack> itens) {

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Amostra do kit!");

        ItemStack voltar = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta zvoltar = (SkullMeta) voltar.getItemMeta();
        zvoltar.setDisplayName(ChatColor.RED + "Voltar");
        zvoltar.setOwner("MHF_ArrowLeft");
        voltar.setItemMeta(zvoltar);

        inv.setItem(0, voltar);

        i = 10;
        itens.stream().forEach(e->{
            inv.setItem(i, e);
            i++;
        });

        player.openInventory(inv);
    }

    @EventHandler
    void click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Amostra do kit")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Voltar")) {
                guiKitsBasicos(player);
                return;
            }
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Amostra do kit!")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Voltar")) {
                guiKitsVips(player);
                return;
            }
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Kits")) {

            if(event.getCurrentItem()==null){return;}
            if(event.getClickedInventory()==null){return;}

            if(event.getCurrentItem().getType().equals(Material.BREAD)) {
                guiKitsBasicos(player);
            }
            if(event.getCurrentItem().getType().equals(Material.DIAMOND)) {
                guiKitsVips(player);
            }

            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Kits basicos")) {

            if(event.getCurrentItem()==null){return;}
            if(event.getClickedInventory()==null){return;}

            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Voltar")) {
                guiKits(player);
                return;
            }
            Arrays.asList(Kits.values()).stream().forEach(e->{
                KitManager km = new KitManager();
                if (e.getType().equals(KitType.BASICO)) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + e.getDisplaynome())) {
                        if (event.getClick().equals(ClickType.LEFT)) {
                            if (KitsItens.isCooldown(player, e)) {
                                player.sendMessage(ChatColor.RED + "Voce esta em cooldown! " + KitsItens.getCooldown(player, e));
                                event.setCancelled(true);
                                return;
                            }

                            int amount = Arrays.asList(e.getItens()).size();

                            if (KitManager.getTotalAmount(player.getInventory()) < amount) {
                                player.sendMessage(ChatColor.RED + "Esvazie seu inventario!");
                                return;
                            }

                            player.sendMessage(ChatColor.GREEN + "Voce pegou o kit!");
                            km.sendKit(player, e);
                        }
                        if (event.getClick().equals(ClickType.RIGHT)) {
                            guiAmostra(player, e.getItens());
                            return;
                        }
                    }
                }
            });
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Kits vips")) {

            if(event.getCurrentItem()==null){return;}
            if(event.getClickedInventory()==null){return;}

            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Voltar")) {
                guiKits(player);
                return;
            }
            Arrays.asList(Kits.values()).stream().forEach(e->{
                KitManager km = new KitManager();
                if (e.getType().equals(KitType.VIP)) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + e.getDisplaynome())) {
                        if (event.getClick().equals(ClickType.LEFT)) {
                            if (hasPermission(player, Grupos.GERENTE.getWeight())) {
                                int amount = e.getItens().size();

                                if (KitManager.getTotalAmount(player.getInventory()) < amount) {
                                    player.sendMessage(ChatColor.RED + "Esvazie seu inventario!");
                                    return;
                                }
                                player.sendMessage(ChatColor.GREEN + "Voce pegou o kit!");
                                km.sendKit(player, e);
                                return;
                            }
                            if (!getGroup(player).equals(e.getMinGroup())) {
                                player.sendMessage(ChatColor.RED + "Voce nao pode pegar este kit!");
                                return;
                            }
                            if (KitsItens.isCooldown(player, e)) {
                                player.sendMessage(ChatColor.RED + "Voce esta em cooldown! " + KitsItens.getCooldown(player, e));
                                event.setCancelled(true);
                                return;
                            }
                            int amount = e.getItens().size();

                            if (KitManager.getTotalAmount(player.getInventory()) < amount) {
                                player.sendMessage(ChatColor.RED + "Esvazie seu inventario!");
                                return;
                            }
                            player.sendMessage(ChatColor.GREEN + "Voce pegou o kit!");
                            km.sendKit(player, e);
                        }
                        if (event.getClick().equals(ClickType.RIGHT)) {
                            guiAmostraVip(player, e.getItens());
                            return;
                        }
                    }
                }
            });
            event.setCancelled(true);
        }
    }
}
