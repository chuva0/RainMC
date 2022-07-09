package pldo0.maquinas;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pldo0.kits.KitsItens;
import pldo0.saldo.SaldoManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static pldo0.Pldozero.*;

public class MaquinaInventory extends MaquinaManager implements Listener {

    static int i;
    public static void guiMaquinasPlayer(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Maquinas");

        ItemStack maq;

        i= 10;

        Arrays.asList(Maquinas.values()).stream().forEach(e->{
            ItemStack is;
            if (containsMaquina(player, e)) {
                is = new ItemStack(e.getMaterial());
                ItemMeta im = is.getItemMeta();
                im.setDisplayName(e.getDisplayname());
                ArrayList<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("   "+ChatColor.GRAY + e.getDesc() + "  ");
                lore.add("  ");
                lore.add("   "+strWorking(player, e)+"  ");
                lore.add(" ");
                lore.add("   "+ChatColor.GRAY +"Nivel: " + ChatColor.GREEN + getLevel(player, e));
                lore.add(" ");
                im.setLore(lore);
                is.setItemMeta(im);
            } else {
                is = new ItemStack(Material.BARRIER);
                ItemMeta im = is.getItemMeta();
                im.setDisplayName(e.getDisplayname());
                ArrayList<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("   "+ChatColor.GRAY + e.getDesc() + "  ");
                lore.add("   "+ChatColor.RED+"Nao possui   ");
                lore.add(" ");
                im.setLore(lore);
                is.setItemMeta(im);
            }
            inv.setItem(i, is);
            i+=3;
        });

        player.openInventory(inv);
    }
    public static void guiComprarMaquinas(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Comprar maquinas");

        ItemStack maq;

        i= 10;

        Arrays.asList(Maquinas.values()).stream().forEach(e->{
            ItemStack is;
            is = new ItemStack(e.getMaterial());
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(e.getDisplayname());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("   "+ChatColor.GRAY + e.getDesc() + "  ");
            lore.add("   "+ChatColor.GRAY + "Preco: " + ChatColor.GREEN + new SaldoManager().formatMoney(e.getPrice()) + "  ");
            lore.add(" ");
            im.setLore(lore);
            is.setItemMeta(im);
            inv.setItem(i, is);
            i+=3;
        });

        player.openInventory(inv);
    }
    public static void guiMaquina(Player player, Maquinas maq) {
        Inventory inv = Bukkit.createInventory(null, 45, "§aSua maquina " + maq.getNome());

        ItemStack work;

        if (working(player, maq)) {
            work = new ItemStack(Material.GREEN_WOOL);
            ItemMeta im = work.getItemMeta();
            im.setDisplayName(ChatColor.GREEN + "Sua maquina esta trabalhando...!");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.GRAY + "   Tempo restante: "+ChatColor.YELLOW+ KitsItens.formatador(getWorkingTemp(player, maq) - System.currentTimeMillis()));
            lore.add(ChatColor.GRAY + "  ");
            im.setLore(lore);
            work.setItemMeta(im);
        } else {
            work = new ItemStack(Material.RED_WOOL);
            ItemMeta im = work.getItemMeta();
            im.setDisplayName(ChatColor.RED + "Sua maquina nao esta trabalhando!");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.GRAY + "   Para sua maquina trabalhar");
            lore.add(ChatColor.GRAY + "   voce precisa gastar combustivel    ");
            lore.add(ChatColor.GRAY + "   ");
            lore.add(ChatColor.GRAY + "   Use "+ChatColor.YELLOW+"/combustivel comprar");
            lore.add(ChatColor.GRAY + "   e clique aqui com o combustivel na mao!   ");
            lore.add(ChatColor.GRAY + "  ");
            lore.add(ChatColor.GRAY + "   Um " + ChatColor.YELLOW + " combustivel " + ChatColor.GRAY + "faz sua maquina");
            lore.add(ChatColor.GRAY + "   funcionar por "+ChatColor.LIGHT_PURPLE+"30 segundos!");
            lore.add(ChatColor.GRAY + "  ");
            lore.add(ChatColor.GRAY + "   A cada segundo tem uma chance");
            lore.add(ChatColor.GRAY + "   de " + ChatColor.GREEN + "40%" + ChatColor.GRAY + " da maquina gerar");
            lore.add(ChatColor.GRAY + "   uma nuvem!");
            lore.add(ChatColor.GRAY + "  ");
            im.setLore(lore);
            work.setItemMeta(im);
        }

        inv.setItem(10, work);

        ItemStack info = new ItemStack(Material.PAPER);
        ItemMeta im = info.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Informacoes:");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "   Nivel: " + ChatColor.GREEN + getLevel(player,maq));
        lore.add(ChatColor.GRAY + "   Quantidade: " + ChatColor.GREEN + getQuantidade(player,maq) + "   ");
        lore.add(ChatColor.GRAY + "    ");
        im.setLore(lore);
        info.setItemMeta(im);

        ItemStack sell = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta im2 = (SkullMeta) sell.getItemMeta();
        im2.setDisplayName(ChatColor.GREEN + "Nuvens:");
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add("");
        lore2.add(ChatColor.GREEN + "   (Clique aqui para vender suas nuvens)   ");
        lore2.add(ChatColor.GRAY + "   ");
        lore2.add(ChatColor.GRAY + "          Nuvens criadas: " + ChatColor.YELLOW + getNuvens(player.getName(), maq));
        lore2.add(ChatColor.GRAY + "    ");
        im2.setLore(lore2);
        im2.setOwner("MHF_Chest");
        sell.setItemMeta(im2);

        inv.setItem(16, info);
        inv.setItem(13, sell);

        player.openInventory(inv);
    }
    @EventHandler
    void maquina(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block bloco = event.getBlockPlaced();
        Arrays.asList(Maquinas.values()).stream().forEach(e->{
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(e.getDisplayname())) {
                    MaquinaManager.setMaquina(player, e, bloco.getLocation(), player.getInventory().getItemInMainHand().getAmount());
                    /*

                    Remover o item da mao!

                     */
                    event.setCancelled(true);
                }
            }
        });
    }
    @EventHandler
    void click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Comprar maquinas")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            Arrays.asList(Maquinas.values()).forEach(e->{
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(e.getDisplayname())) {
                    MaquinaManager.giveItemMaquina(player, e);
                }
            });
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Maquinas")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            event.setCancelled(true);
        }
        if (event.getView().getTitle().contains("§aSua maquina")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            String maquina = event.getView().getTitle().replace("§aSua maquina ", "");
            if (event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sua maquina nao esta trabalhando!")) {
                    if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(net.md_5.bungee.api.ChatColor.RED + "Combustivel")) {

                            int amount = player.getInventory().getItemInMainHand().getAmount();

                            setWorking(player, Maquinas.valueOf(maquina.toUpperCase()), true, amount);
                            guiMaquina(player, Maquinas.valueOf(maquina.toUpperCase()));
                            player.getInventory().remove(player.getInventory().getItemInMainHand());
                        } else {
                            player.sendMessage(ChatColor.RED + "Sem combustivel em maos!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Sem combustivel em maos!");
                    }
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Nuvens:")) {
                    if (getNuvens(player.getName(), Maquinas.valueOf(maquina.toUpperCase())) == 0) {
                        player.sendMessage(ChatColor.RED + "Voce ainda nao possui nuvens para vender!");
                    } else {
                        sellNuvens(player.getName(), Maquinas.valueOf(maquina.toUpperCase()), 5000);
                        player.sendMessage(ChatColor.GREEN + "Nuvens vendidas!");
                        new SaldoManager().setTab(player);
                        guiMaquina(player, Maquinas.valueOf(maquina.toUpperCase()));
                    }
                }
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    void inv(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand as = (ArmorStand) event.getRightClicked();

            Location loc = as.getLocation();

            for (Maquinas maq : Maquinas.values()) {
                if (containsMaquina(player, maq)) {
                    World world = Bukkit.getWorld(getMaquinaConfig().getConfig().getString("Player." + player.getName() + "." + maq.getNome() + ".Location.World"));
                    int x = getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.X");
                    int y = getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.Y");
                    int z = getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.Z");

                    if (as.getLocation().equals(new Location(world, x, y, z).add(-.5, -1, -.5))) {
                        guiMaquina(player, maq);
                    } else {
                        player.sendMessage(ChatColor.RED + "Sem acesso!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Sem acesso!");
                }
            }

            event.setCancelled(true);
        }
    }
}
