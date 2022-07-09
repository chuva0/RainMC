package pldo0.grupos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pldo0.kits.Kits;
import pldo0.kits.KitsItens;
import pldo0.ranks.Ranks;
import pldo0.saldo.Saldo;
import pldo0.saldo.SaldoManager;

import java.lang.invoke.LambdaConversionException;
import java.util.Arrays;
import java.util.Date;

import static pldo0.Pldozero.getRankConfig;

public class GrupoManager {

    public boolean existPlayer(Player player) {
        if (getRankConfig().getConfig().contains("Player." + player.getName())) {
            return true;
        }
        return false;
    }
    public boolean existPlayer(String player) {
        if (getRankConfig().getConfig().contains("Player." + player)) {
            return true;
        }
        return false;
    }
    public void createPlayer(Player player) {
        player.sendMessage("Bem vindo!");
        getRankConfig().getConfig().set("Player." + player.getName() + ".Saldo", 0);
        getRankConfig().getConfig().set("Player." + player.getName() + ".Rank", Ranks.TERRA3.toString());
        getRankConfig().getConfig().set("Player." + player.getName() + ".Grupo", Grupos.MEMBRO.toString());
        getRankConfig().getConfig().set("Player." + player.getName() + ".Data", new Date());
        Arrays.asList(Kits.values()).stream().forEach(e->{
            getRankConfig().getConfig().set("Player." + player.getName() + ".Kit."+e.getNome()+".Cooldown", 0);
        });
        getRankConfig().saveConfig();
    }
    public void createPlayer(String player) {
        getRankConfig().getConfig().set("Player." + player + ".Saldo", 0);
        getRankConfig().getConfig().set("Player." + player + ".Rank", Ranks.TERRA3.toString());
        getRankConfig().getConfig().set("Player." + player + ".Grupo", Grupos.MEMBRO.toString());
        getRankConfig().getConfig().set("Player." + player + ".Data", new Date());
        getRankConfig().saveConfig();
    }
    public Object getData(Player player) {
        if (existPlayer(player)) {
            return getRankConfig().getConfig().get("Player." + player.getName() + ".Data");
        }
        return null;
    }
    public Object getData(String player) {
        if (existPlayer(player)) {
            return getRankConfig().getConfig().get("Player." + player + ".Data");
        }
        return null;
    }

    public void setGroupPlayer(Player player, Grupos grupo) {
        if (!existPlayer(player)) {
            return;
        }
        getRankConfig().getConfig().set("Player." + player.getName() + ".Grupo", grupo.toString());
        getRankConfig().saveConfig();
        entryTeam(player);
    }
    public void setTempGroupPlayer(Player player, Grupos grupo, long tempo) {
        if (!existPlayer(player)) {
            return;
        }
        getRankConfig().getConfig().set("Player." + player.getName() + ".LastGrupo", getGroup(player).toString().toUpperCase());
        getRankConfig().getConfig().set("Player." + player.getName() + ".Grupo", grupo.toString());
        getRankConfig().getConfig().set("Player." + player.getName() + ".hasTempo", true);
        getRankConfig().getConfig().set("Player." + player.getName() + ".Tempo", tempo);
        getRankConfig().saveConfig();
        entryTeam(player);
    }
    public void setTempGroupPlayer(String player, Grupos grupo, long tempo) {
        if (!existPlayer(player)) {
            return;
        }
        getRankConfig().getConfig().set("Player." + player + ".LastGrupo", getGroup(player).toString().toUpperCase());
        getRankConfig().getConfig().set("Player." + player + ".Grupo", grupo.toString());
        getRankConfig().getConfig().set("Player." + player + ".hasTempo", true);
        getRankConfig().getConfig().set("Player." + player + ".Tempo", tempo);
        getRankConfig().saveConfig();
        for (Player on : Bukkit.getOnlinePlayers()) {
            if (on.getName().equalsIgnoreCase(player)) {
                entryTeam(on);
            }
        }
    }
    public boolean hasTempGroup(Player player) {
        if (getRankConfig().getConfig().getBoolean("Player." + player.getName() + ".hasTempo")) {
            return true;
        }
        return false;
    }
    public boolean hasTempGroup(String player) {
        if (getRankConfig().getConfig().getBoolean("Player." + player + ".hasTempo")) {
            return true;
        }
        return false;
    }
    public long getTempo(Player player) {
        return getRankConfig().getConfig().getLong("Player." + player.getName() + ".Tempo");
    }
    public long getTempo(String player) {
        return getRankConfig().getConfig().getLong("Player." + player + ".Tempo");
    }
    public void setGroupPlayer(String player, Grupos grupo) {
        if (!existPlayer(player)) {
            return;
        }
        getRankConfig().getConfig().set("Player." + player + ".Grupo", grupo.toString());
        getRankConfig().saveConfig();
    }
    public Grupos getGroup(Player player) {
        return Grupos.valueOf(getRankConfig().getConfig().getString("Player." + player.getName() + ".Grupo"));
    }
    public Grupos getGroup(String player) {
        return Grupos.valueOf(getRankConfig().getConfig().getString("Player." + player + ".Grupo"));
    }
    public boolean hasPermission(Player player, Integer weight) {
        if (getGroup(player).getWeight()>= weight) {
            return true;
        }
        return false;
    }
    public boolean hasPermission(String player, Integer weight) {
        if (getGroup(player).getWeight()>= weight) {
            return true;
        }
        return false;
    }
    public String color(Player player) {
        if (getGroup(player).getWeight() > 0) {
            return ChatColor.WHITE + "";
        }
        return ChatColor.GRAY + "";
    }
    public String color(String player) {
        if (hasPermission(player, 1)) {
            return ChatColor.WHITE + "";
        }
        return ChatColor.GRAY + "";
    }
    public void entryTeam(Player player) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();

        Team membro = sb.getTeam(Grupos.MEMBRO.getOrder());
        if (membro == null) {
            membro = sb.registerNewTeam(Grupos.MEMBRO.getOrder());
        }
        Team chuva = sb.getTeam(Grupos.CHUVA.getOrder());
        if (chuva == null) {
            chuva = sb.registerNewTeam(Grupos.CHUVA.getOrder());
        }
        Team tempestade = sb.getTeam(Grupos.TEMPESTADE.getOrder());
        if (tempestade == null) {
            tempestade = sb.registerNewTeam(Grupos.TEMPESTADE.getOrder());
        }
        Team temporal = sb.getTeam(Grupos.TEMPORAL.getOrder());
        if (temporal == null) {
            temporal = sb.registerNewTeam(Grupos.TEMPORAL.getOrder());
        }
        Team youtuber = sb.getTeam(Grupos.YOUTUBER.getOrder());
        if (youtuber == null) {
            youtuber = sb.registerNewTeam(Grupos.YOUTUBER.getOrder());
        }
        Team streamer = sb.getTeam(Grupos.STREAMER.getOrder());
        if (streamer == null) {
            streamer = sb.registerNewTeam(Grupos.STREAMER.getOrder());
        }
        Team builder = sb.getTeam(Grupos.BUILDER.getOrder());
        if (builder == null) {
            builder = sb.registerNewTeam(Grupos.BUILDER.getOrder());
        }
        Team ajudante = sb.getTeam(Grupos.AJUDANTE.getOrder());
        if (ajudante == null) {
            ajudante = sb.registerNewTeam(Grupos.AJUDANTE.getOrder());
        }
        Team moderador = sb.getTeam(Grupos.MODERADOR.getOrder());
        if (moderador == null) {
            moderador = sb.registerNewTeam(Grupos.MODERADOR.getOrder());
        }
        Team admin = sb.getTeam(Grupos.ADMINISTRADOR.getOrder());
        if (admin == null) {
            admin = sb.registerNewTeam(Grupos.ADMINISTRADOR.getOrder());
        }
        Team gerente = sb.getTeam(Grupos.GERENTE.getOrder());
        if (gerente == null) {
            gerente = sb.registerNewTeam(Grupos.GERENTE.getOrder());
        }
        Team master = sb.getTeam(Grupos.MASTER.getOrder());
        if (master == null) {
            master = sb.registerNewTeam(Grupos.MASTER.getOrder());
        }
        membro.setPrefix(Grupos.MEMBRO.getPrefix());
        membro.setColor(ChatColor.GRAY);
        chuva.setPrefix(Grupos.CHUVA.getPrefix() + " ");
        tempestade.setPrefix(Grupos.TEMPESTADE.getPrefix() + " ");
        temporal.setPrefix(Grupos.TEMPORAL.getPrefix() + " ");
        youtuber.setPrefix(Grupos.YOUTUBER.getPrefix() + " ");
        streamer.setPrefix(Grupos.STREAMER.getPrefix() + " ");
        builder.setPrefix(Grupos.BUILDER.getPrefix() + " ");
        ajudante.setPrefix(Grupos.AJUDANTE.getPrefix() + " ");
        moderador.setPrefix(Grupos.MODERADOR.getPrefix() + " ");
        admin.setPrefix(Grupos.ADMINISTRADOR.getPrefix() + " ");
        gerente.setPrefix(Grupos.GERENTE.getPrefix() + " ");
        master.setPrefix(Grupos.MASTER.getPrefix() + " ");

        membro.removeEntry(player.getName());
        chuva.removeEntry(player.getName());
        tempestade.removeEntry(player.getName());
        temporal.removeEntry(player.getName());
        youtuber.removeEntry(player.getName());
        streamer.removeEntry(player.getName());
        builder.removeEntry(player.getName());
        ajudante.removeEntry(player.getName());
        moderador.removeEntry(player.getName());
        admin.removeEntry(player.getName());
        gerente.removeEntry(player.getName());
        master.removeEntry(player.getName());
        switch (getGroup(player)) {
            case MEMBRO:
                membro.addEntry(player.getName());
                break;
            case CHUVA:
                chuva.addEntry(player.getName());
                break;
            case TEMPORAL:
                temporal.addEntry(player.getName());
                break;
            case TEMPESTADE:
                tempestade.addEntry(player.getName());
                break;
            case YOUTUBER:
                youtuber.addEntry(player.getName());
                break;
            case STREAMER:
                streamer.addEntry(player.getName());
                break;
            case BUILDER:
                builder.addEntry(player.getName());
                break;
            case AJUDANTE:
                ajudante.addEntry(player.getName());
                break;
            case MODERADOR:
                moderador.addEntry(player.getName());
                break;
            case ADMINISTRADOR:
                admin.addEntry(player.getName());
                break;
            case GERENTE:
                gerente.addEntry(player.getName());
                break;
            case MASTER:
                master.addEntry(player.getName());
                break;
            default:
                break;
        }

        setTab(player);

    }
    public void setTab(Player player) {
        if (getGroup(player).equals(Grupos.MEMBRO)) {
            player.setPlayerListHeader("§9§lRAIN§f§lMC\n\n§7Servidor de §bRankUp §7com atualizacoes semanais! §71.16.§ax§7!" +
                    "\n\n      §7Grupo: Membro           " + "§7Money: §a" + new SaldoManager().formatMoney(player) + "      \n");
            player.setPlayerListFooter("\n§7Site: §bhttp://rainmc.com.br\n§7Discord: §bhttp://discord.gg/rainmc\n");
        } else {
            player.setPlayerListHeader("§9§lRAIN§f§lMC\n\n§7Servidor de §bRankUp §7com atualizacoes semanais! §71.16.§ax§7!" +
                    "\n\n      §7Grupo: " + getGroup(player).getPrefix() + "           " + "§7Money: §a" + new SaldoManager().formatMoney(player) + "      \n");
            player.setPlayerListFooter("\n§7Site: §bhttp://rainmc.com.br\n§7Discord: §bhttp://discord.gg/rainmc\n");
        }
    }
}
