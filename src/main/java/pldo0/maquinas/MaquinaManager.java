package pldo0.maquinas;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pldo0.Pldozero;
import pldo0.kits.KitManager;
import pldo0.saldo.SaldoManager;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static pldo0.Pldozero.*;

public class MaquinaManager {

    public static boolean containsMaquina(String player, Maquinas maq) {
        if (getMaquinaConfig().getConfig().contains("Player." + player + "." + maq.getNome())) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean containsMaquina(Player player, Maquinas maq) {
        if (getMaquinaConfig().getConfig().contains("Player." + player.getName() + "." + maq.getNome())) {
            return true;
        } else {
            return false;
        }
    }
    public static int getLevel(Player player, Maquinas maq) {
        if (!containsMaquina(player, maq)) {
            return 0;
        }
        return getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Level");
    }
    public static int getLevel(String player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            return 0;
        }
        return getMaquinaConfig().getConfig().getInt("Player." + player + "." + maq.getNome() + ".Level");
    }
    public static boolean working(Player player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            return getMaquinaConfig().getConfig().getBoolean("Player." + player.getName() + "." + maq.getNome() + ".Funcionando");
        }
        return false;
    }
    public static void setWorking(Player player, Maquinas maq, boolean value, int quantia) {
        getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Funcionando", value);
        if (quantia==0) {
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Funcionando_Tempo",
                    0
            );
        } else {
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Funcionando_Tempo",
                    (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30*quantia))
            );
        }
        getMaquinaConfig().saveConfig();
    }
    public static void setWorking(String player, Maquinas maq, boolean value, int quantia) {
        getMaquinaConfig().getConfig().set("Player." + player + "." + maq.getNome() + ".Funcionando", value);
        if (quantia==0) {
            getMaquinaConfig().getConfig().set("Player." + player + "." + maq.getNome() + ".Funcionando_Tempo",
                    0
            );
        } else {
            getMaquinaConfig().getConfig().set("Player." + player + "." + maq.getNome() + ".Funcionando_Tempo",
                    (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30*quantia))
            );
        }
        getMaquinaConfig().saveConfig();
    }
    public static long getWorkingTemp(Player player, Maquinas maq) {
        return getMaquinaConfig().getConfig().getLong("Player." + player.getName() + "." + maq.getNome() + ".Funcionando_Tempo");
    }
    public static long getWorkingTemp(String player, Maquinas maq) {
        return getMaquinaConfig().getConfig().getLong("Player." + player + "." + maq.getNome() + ".Funcionando_Tempo");
    }
    public static boolean working(String player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            return getMaquinaConfig().getConfig().getBoolean("Player." + player + "." + maq.getNome() + ".Funcionando");
        }
        return false;
    }
    public static String strWorking(Player player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            if (working(player, maq)) {
                return ChatColor.GREEN + "Esta trabalhando!";
            } else {
                return ChatColor.RED + "Nao esta trabalhando";
            }
        }
        return ChatColor.RED + "Nao esta trabalhando";
    }
    public static String strWorking(String player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            if (working(player, maq)) {
                return ChatColor.GREEN + "Esta trabalhando!";
            } else {
                return ChatColor.RED + "Nao esta trabalhando";
            }
        }
        return ChatColor.RED + "Nao esta trabalhando";
    }
    public static void setLevel(Player player, Maquinas maq, int lvl) {
        if (containsMaquina(player, maq)) {
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Level", (
                    getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Level") + 1));
            getMaquinaConfig().saveConfig();
        }
    }

    public static void giveItemMaquina(Player player, Maquinas maq) {
        ItemStack is = new ItemStack(maq.getMaterial());
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(maq.getDisplayname());
        is.setItemMeta(im);

        if (new SaldoManager().getSaldo(player) < maq.getPrice()) {
            player.sendMessage(ChatColor.RED + "Seu money suficiente!");
            return;
        }
        if (KitManager.getTotalAmount(player.getInventory()) < 1) {
            player.sendMessage(ChatColor.RED + "Abra um slot para receber a maquina!");
            return;
        }
        player.getInventory().addItem(is);
        player.sendMessage(ChatColor.GREEN + "Voce comprou a maquina " + maq.getDisplayname());
        new SaldoManager().removeSaldo(player, maq.getPrice());
        new SaldoManager().setTab(player);
    }

    public static int getQuantidade(Player player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
           return getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Quantidade");
        }
        return 0;
    }
    public static int getQuantidade(String player, Maquinas maq) {
        if (containsMaquina(player, maq)) {
            return getMaquinaConfig().getConfig().getInt("Player." + player + "." + maq.getNome() + ".Quantidade");
        }
        return 0;
    }

    public static long getNuvens(String player, Maquinas maq) {
        return getMaquinaConfig().getConfig().getLong("Player." + player + "." + maq.getNome() + ".Nuvens");
    }
    public static int getStack(String player, Maquinas maq) {
        return getMaquinaConfig().getConfig().getInt("Player." + player + "." + maq.getNome() + ".Quantidade");
    }
    public static void addNuvens(String player, Maquinas maq) {
        getMaquinaConfig().getConfig().set("Player." + player + "." + maq.getNome() + ".Nuvens", (getNuvens(player, maq) + 1));
        getMaquinaConfig().saveConfig();
    }
    public static void sellNuvens(String player, Maquinas maq, double pricePerNuvem) {

        long nuv = getNuvens(player, maq);

        new SaldoManager().addSaldo(player, (nuv*7500l)*(1+getStack(player, maq)/10));
        getMaquinaConfig().getConfig().set("Player." + player + "." + maq.getNome() + ".Nuvens", 0);
        getMaquinaConfig().saveConfig();
    }
    static int task;
    public static void setMaquina(Player player, Maquinas maq, Location loc, int amount) {

        Location locc = new Location(loc.getWorld(),
                (getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.X")-0.5),
                (getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.Y")-1),
                (getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Location.Z")-0.5));

        if (getQuantidade(player, maq) < 1) {
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Level", 1);
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Quantidade", amount);
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Funcionando", false);
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Location.World", loc.getWorld().getName());
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Location.X", loc.getX());
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Location.Y", loc.getY());
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Location.Z", loc.getZ());
            getMaquinaConfig().saveConfig();

            ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc.add(-0.5, -1, -0.5), EntityType.ARMOR_STAND);
            as.setVisible(false);
            as.setCustomNameVisible(true);
            as.setGravity(false);
            as.setInvulnerable(true);
            as.getEquipment().setHelmet(new ItemStack(maq.getMaterial()));
            as.setCustomName(ChatColor.GRAY + "Maquina " + maq.getDisplayname() + ChatColor.GRAY + " (Stack: "+ChatColor.GREEN + getQuantidade(player,maq)+ChatColor.GRAY+")");
        } else {
            getMaquinaConfig().getConfig().set("Player." + player.getName() + "." + maq.getNome() + ".Quantidade",
                    (getMaquinaConfig().getConfig().getInt("Player." + player.getName() + "." + maq.getNome() + ".Quantidade")+amount));
            getMaquinaConfig().saveConfig();
            for (ArmorStand as : loc.getWorld().getEntitiesByClass(ArmorStand.class)) {
                if (as.getLocation().equals(locc)) {
                    as.setCustomName(ChatColor.GRAY + "Maquina " + maq.getDisplayname() + ChatColor.GRAY + " (Stack: "+ChatColor.GREEN + getQuantidade(player,maq)+ChatColor.GRAY+")");
                }
            }


        }
        return;
    }
    public static void loadArmorStands() {
        for (ArmorStand as : Bukkit.getWorld("world").getEntitiesByClass(ArmorStand.class)) {
            as.remove();
        }
        if (!getMaquinaConfig().getConfig().contains("Player")) {
            return;
        }
        for (String player : getMaquinaConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
            for (Maquinas e : Maquinas.values()) {
                if (getMaquinaConfig().getConfig().contains("Player." + player + "." + e.getNome())) {
                    Location locc = new Location(Bukkit.getWorld(getMaquinaConfig().getConfig().getString("Player." + player + "." + e.getNome() + ".Location.World")),
                            (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.X")-0.5),
                            (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.Y")-1),
                            (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.Z")-0.5));
                    ArmorStand as = (ArmorStand) locc.getWorld().spawnEntity(locc, EntityType.ARMOR_STAND);
                    as.setVisible(false);
                    as.setCustomNameVisible(true);
                    as.setGravity(false);
                    as.setInvulnerable(true);
                    if (!working(player, e)) {
                        as.getEquipment().setHelmet(new ItemStack(e.getMaterial()));
                    } else {
                        as.getEquipment().setHelmet(new ItemStack(Material.LIGHT_BLUE_WOOL));
                    }
                    as.setCustomName(ChatColor.GRAY + "Maquina " + e.getDisplayname() + ChatColor.GRAY + " (Stack: "+ChatColor.GREEN + getQuantidade(player,e)+ChatColor.GRAY+")");
                }
            }
        }
    }
    public static void workingParticle() {

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!getMaquinaConfig().getConfig().contains("Player")) {
                    return;
                }
                for (String player : getMaquinaConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
                    if (player == null) {
                        return;
                    }
                    for (Maquinas e : Maquinas.values()) {
                        if (getMaquinaConfig().getConfig().contains("Player." + player + "." + e.getNome())) {
                            Location locc = new Location(Bukkit.getWorld(getMaquinaConfig().getConfig().getString("Player." + player + "." + e.getNome() + ".Location.World")),
                                    (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.X")),
                                    (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.Y")),
                                    (getMaquinaConfig().getConfig().getInt("Player." + player + "." + e.getNome() + ".Location.Z")));

                            Location loc = locc.add(-.5, 0, -.5);
                            if (working(player, e)) {
                                locc.getWorld().spawnParticle(Particle.FALLING_WATER, loc, 15, 1, 0,0);
                                locc.getWorld().spawnParticle(Particle.FALLING_WATER, loc, 15, 0, 0,1);
                                locc.getWorld().spawnParticle(Particle.FALLING_WATER, loc, 15, 1, 0,1);
                                locc.getWorld().spawnParticle(Particle.FALLING_WATER, loc, 15, 0, 0,0);
                                for (Player on : Bukkit.getOnlinePlayers()) {
                                    if (on.getName().equals(player)) {
                                        Random r = new Random();
                                        Integer chance = r.nextInt(100);
                                        if (chance <= 40) {
                                            addNuvens(player, e);
                                        }
                                    }
                                }
                            }
                            if (getWorkingTemp(player, e) <= System.currentTimeMillis()) {
                                for (Player on : Bukkit.getOnlinePlayers()) {
                                    if (on.getName().equals(player) && working(player, e)) {
                                        on.sendMessage(ChatColor.RED + "Sua maquina expirou! Agora voce pode ativa-la novamente!");
                                    }
                                    setWorking(player, e, false, 0);
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Pldozero.getInstance(), 0l, 20l);
    }
}
