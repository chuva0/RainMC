package pldo0.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pldo0.file.Punish;
import pldo0.kits.KitsItens;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class PunishmentManager {

    public static Punish punish = new Punish();

    public static void ban(String player, Reason reason) {
        punish.getConfig().set("Player." + player + ".Banido.Value",true);
        punish.getConfig().set("Player." + player + ".Banido.Reason", reason.getReason());
        punish.getConfig().set("Player." + player + ".Banido.Tempo", 0l);
        punish.saveConfig();
    }
    public static void ban(String player, long time, Reason reason) {
        punish.getConfig().set("Player." + player + ".Banido.Value",true);
        long tempo = System.currentTimeMillis() + time;
        punish.getConfig().set("Player." + player + ".Banido.Tempo", tempo);
        punish.getConfig().set("Player." + player + ".Banido.Reason", reason.getReason());
        punish.saveConfig();
    }
    public static boolean isBan(String player) {
        return punish.getConfig().getBoolean("Player." + player + ".Banido.Value");
    }
    public static boolean isMute(String player) {
        return punish.getConfig().getBoolean("Player." + player + ".Mutado.Value");
    }
    public static String getReason(String player) {
        return punish.getConfig().getString("Player." + player + ".Banido.Reason");
    }
    public static void mute(String player, Reason reason) {
        punish.getConfig().set("Player." + player + ".Mutado.Value",true);
        punish.getConfig().set("Player." + player + ".Mutado.Reason", reason.getReason());
        punish.getConfig().set("Player." + player + ".Mutado.Tempo", 0l);
        punish.saveConfig();
    }
    public static void mute(String player, long time, Reason reason) {
        punish.getConfig().set("Player." + player + ".Mutado.Value",true);
        long tempo = System.currentTimeMillis() + time;
        punish.getConfig().set("Player." + player + ".Mutado.Reason", reason.getReason());
        punish.getConfig().set("Player." + player + ".Mutado.Tempo", tempo);
        punish.saveConfig();
    }
    public static int getWarnAmount(String player) {
        return punish.getConfig().getInt("Player." + player + ".Warns");
    }
    public static void addWarn(String player) {
        punish.getConfig().set("Player." + player + ".Warns", getWarnAmount(player)+1);
        punish.saveConfig();
    }
    public static void setWarn(String player, int amount) {
        punish.getConfig().set("Player." + player + ".Warns", amount);
        punish.saveConfig();
    }
    public static long getTempo(String player) {
        return (punish.getConfig().getLong("Player." + player + ".Banido.Tempo")-System.currentTimeMillis());
    }
    public static long getTempoMute(String player) {
        return (punish.getConfig().getLong("Player." + player + ".Mutado.Tempo")-System.currentTimeMillis());
    }
    public static long getTempoConfigBan(String player) {
        return punish.getConfig().getLong("Player." + player + ".Banido.Tempo");
    }
    public static long getTempoConfigMute(String player) {
        return punish.getConfig().getLong("Player." + player + ".Mutado.Tempo");
    }
    public static void punishPlayer(String player, Reason reason) {
        switch (reason) {
            case BUG:
                ban(player, TimeUnit.DAYS.toMillis(10), Reason.BUG);
                for (Player on : Bukkit.getOnlinePlayers()) {
                    if (on.getName().equalsIgnoreCase(player)) {
                        on.kickPlayer(ChatColor.RED + "[Banimento]\n\n"
                                + ChatColor.GRAY + "Voce foi banido por: " + Reason.BUG.getReason() + "\n"
                                + ChatColor.GRAY + "Tempo: " + KitsItens.formatador(getTempo(player)) +
                                "\n\n"+ChatColor.RED + "[Banimento]");
                    }
                }
                break;
            case HACK:
                ban(player, Reason.HACK);
                for (Player on : Bukkit.getOnlinePlayers()) {
                    if (on.getName().equalsIgnoreCase(player)) {
                        on.kickPlayer(ChatColor.RED + "[Banimento]\n\n" + ChatColor.GRAY + "Voce foi banido por: " + Reason.BUG.getReason()+"\n\n"+ChatColor.RED + "[Banimento]");
                    }
                }
                break;
            case SPAM:
               if (getWarnAmount(player) < 4) {
                   for (Player on : Bukkit.getOnlinePlayers()) {
                       if (on.getName().equalsIgnoreCase(player)) {
                           addWarn(player);
                           on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Voce tomou um aviso por: " + Reason.SPAM.getReason());
                           on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Seus avisos: " + ChatColor.RED + getWarnAmount(player));
                           on.sendMessage("");
                           on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Depois de 3 avisos, uma punicao e aplicada!");
                           on.sendMessage("");
                       }
                   }
               } else {
                   mute(player, TimeUnit.DAYS.toMillis(1), Reason.SPAM);
                   for (Player on : Bukkit.getOnlinePlayers()) {
                       if (on.getName().equalsIgnoreCase(player)) {
                           on.sendMessage(ChatColor.RED + "[Mute] " + ChatColor.GRAY + "Voce foi mutado por: " + Reason.SPAM.getReason());
                           setWarn(on.getName(), 0);
                       }
                   }
               }
                break;
            case FLOOD:
                if (getWarnAmount(player) < 4) {
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (on.getName().equalsIgnoreCase(player)) {
                            addWarn(player);
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Voce tomou um aviso por: " + Reason.FLOOD.getReason());
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Seus avisos: " + ChatColor.RED + getWarnAmount(player));
                            on.sendMessage("");
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Depois de 3 avisos, uma punicao e aplicada!");
                            on.sendMessage("");
                        }
                    }
                } else {
                    mute(player, TimeUnit.DAYS.toMillis(1), Reason.FLOOD);
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (on.getName().equalsIgnoreCase(player)) {
                            on.sendMessage(ChatColor.RED + "[Mute] " + ChatColor.GRAY + "Voce foi mutado por: " + Reason.FLOOD.getReason());
                            setWarn(on.getName(), 0);
                        }
                    }
                }
                break;
            case GRIFFIN:
                ban(player, Reason.GRIFFIN);
                for (Player on : Bukkit.getOnlinePlayers()) {
                    if (on.getName().equalsIgnoreCase(player)) {
                        on.kickPlayer(ChatColor.RED + "[Banimento]\n\n"
                                + ChatColor.GRAY + "Voce foi banido por: " + Reason.GRIFFIN.getReason() +
                                "\n\n"+ChatColor.RED + "[Banimento]");
                    }
                }
                break;
            case ANTIJOGO:
                ban(player, TimeUnit.DAYS.toMillis(20) ,Reason.ANTIJOGO);
                for (Player on : Bukkit.getOnlinePlayers()) {
                    if (on.getName().equalsIgnoreCase(player)) {
                        on.kickPlayer(ChatColor.RED + "[Banimento]\n\n"
                                + ChatColor.GRAY + "Voce foi banido por: " + Reason.ANTIJOGO.getReason() + "\n"
                                + ChatColor.GRAY + "Tempo: " + KitsItens.formatador(getTempo(player)) +
                                "\n\n"+ChatColor.RED + "[Banimento]");
                    }
                }
                break;
            case DIVULGACAO:
                if (getWarnAmount(player) < 4) {
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (on.getName().equalsIgnoreCase(player)) {
                            addWarn(player);
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Voce tomou um aviso por: " + Reason.DIVULGACAO.getReason());
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Seus avisos: " + ChatColor.RED + getWarnAmount(player));
                            on.sendMessage("");
                            on.sendMessage(ChatColor.RED + "[Aviso] " + ChatColor.GRAY + "Depois de 3 avisos, uma punicao e aplicada!");
                            on.sendMessage("");
                        }
                    }
                } else {
                    mute(player, TimeUnit.DAYS.toMillis(1), Reason.DIVULGACAO);
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (on.getName().equalsIgnoreCase(player)) {
                            on.sendMessage(ChatColor.RED + "[Mute] " + ChatColor.GRAY + "Voce foi mutado por: " + Reason.DIVULGACAO.getReason());
                            setWarn(on.getName(), 0);
                        }
                    }
                }
                break;
        }
    }
}
