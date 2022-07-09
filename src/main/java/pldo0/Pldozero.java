package pldo0;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import pldo0.comandos.Comandos;
import pldo0.eventos.Eventos;
import pldo0.file.Maquina;
import pldo0.file.Punish;
import pldo0.file.Rank;
import pldo0.file.Terreno;
import pldo0.grupos.GrupoManager;
import pldo0.grupos.Grupos;
import pldo0.kits.KitInventory;
import pldo0.maquinas.MaquinaInventory;
import pldo0.maquinas.MaquinaManager;
import pldo0.punish.PunishmentInventory;
import pldo0.punish.PunishmentManager;
import pldo0.ranks.RanksInvetory;

import java.util.Arrays;

public class Pldozero extends JavaPlugin {

    public static Pldozero instance;
    private static Rank rank;
    private static Maquina maquina;
    private static Terreno terreno;
    private static Punish punish;

    @Override
    public void onEnable() {

        instance = this;
        rank = new Rank();
        maquina = new Maquina();
        terreno = new Terreno();
        punish = new Punish();

        eventos();
        comandos();

        checkTempGroup();
        checkTempoPunish();

        MaquinaManager.loadArmorStands();
        MaquinaManager.workingParticle();

        carregarConfig();
    }

    public void checkTempGroup() {
        new BukkitRunnable() {
            @Override
            public void run() {

                if (getRankConfig().getConfig().getConfigurationSection("Player")!=null) {
                    for(String on : getRankConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
                        if (on==null) {
                            return;
                        }
                        if (getRankConfig().getConfig().getBoolean("Player." + on + ".hasTempo")) {
                            long time = getRankConfig().getConfig().getLong("Player." + on + ".Tempo");
                            if (time <= System.currentTimeMillis()) {
                                new GrupoManager().setGroupPlayer(on, Grupos.valueOf(
                                        getRankConfig().getConfig().getString("Player." + on + ".LastGrupo")
                                ));
                                getRankConfig().getConfig().set("Player." + on + ".LastGrupo", null);
                                getRankConfig().getConfig().set("Player." + on + ".hasTempo", false);
                                getRankConfig().getConfig().set("Player." + on + ".Tempo", null);
                                getRankConfig().saveConfig();
                                for (Player onn : Bukkit.getOnlinePlayers()) {
                                    if (onn.getName().equals(on)) {
                                        onn.sendMessage(ChatColor.GREEN + "Seu grupo atual expirou! Setado como " + new GrupoManager().getGroup(on).getPrefix() + ChatColor.GREEN + " novamente!");
                                        new GrupoManager().entryTeam(onn);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0l, 20l);
    }
    public void checkTempoPunish() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getPunishConfig().getConfig().getConfigurationSection("Player") != null) {
                    for(String on : getPunishConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
                        if (on==null) {
                            return;
                        }
                        if (getPunishConfig().getConfig().getBoolean("Player." + on + ".Banido.Value")) {
                            long time = PunishmentManager.getTempoConfigBan(on);
                            if (time <= System.currentTimeMillis()) {
                                PunishmentManager.punish.getConfig().set("Player." + on + ".Banido.Value", false);
                                PunishmentManager.punish.saveConfig();
                            }
                        }
                        if (getPunishConfig().getConfig().getBoolean("Player." + on + ".Mutado.Value")) {
                            long time = PunishmentManager.getTempoConfigMute(on);
                            if (time <= System.currentTimeMillis()) {
                                PunishmentManager.punish.getConfig().set("Player." + on + ".Mutado.Value", false);
                                PunishmentManager.punish.saveConfig();
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0l, 20l);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void carregarConfig() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }
    public static Pldozero getInstance() {
        return instance;
    }

    public static Rank getRankConfig() {
        return rank;
    }
    public static Maquina getMaquinaConfig() {
        return maquina;
    }

    public static Terreno getTerrenoConfig() {
        return terreno;
    }
    public static Punish getPunishConfig() {
        return punish;
    }

    private void comandos() {
        getCommand("magnata").setExecutor(new Comandos());
        getCommand("bc").setExecutor(new Comandos());
        getCommand("rankup").setExecutor(new Comandos());
        getCommand("money").setExecutor(new Comandos());
        getCommand("tempo").setExecutor(new Comandos());
        getCommand("ranks").setExecutor(new Comandos());
        getCommand("grupo").setExecutor(new Comandos());
        getCommand("kit").setExecutor(new Comandos());
        getCommand("kits").setExecutor(new Comandos());
        getCommand("blocos").setExecutor(new Comandos());
        getCommand("maquina").setExecutor(new Comandos());
        getCommand("maquinas").setExecutor(new Comandos());
        getCommand("combustivel").setExecutor(new Comandos());
        getCommand("tell").setExecutor(new Comandos());
        getCommand("s").setExecutor(new Comandos());
        getCommand("forcegrupo").setExecutor(new Comandos());
        getCommand("tempos").setExecutor(new Comandos());
        getCommand("terreno").setExecutor(new Comandos());
        getCommand("punir").setExecutor(new Comandos());
        getCommand("despunir").setExecutor(new Comandos());
    }
    private void eventos() {
        getServer().getPluginManager().registerEvents(new Eventos(), this);
        getServer().getPluginManager().registerEvents(new KitInventory(), this);
        getServer().getPluginManager().registerEvents(new RanksInvetory(), this);
        getServer().getPluginManager().registerEvents(new PunishmentInventory(), this);
        getServer().getPluginManager().registerEvents(new MaquinaInventory(), this);
    }
}
