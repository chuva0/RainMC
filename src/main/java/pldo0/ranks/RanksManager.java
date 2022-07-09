package pldo0.ranks;

import org.bukkit.entity.Player;
import pldo0.saldo.SaldoManager;
import pldo0.grupos.GrupoManager;

import static pldo0.Pldozero.*;

public class RanksManager extends GrupoManager {

    public Ranks getRank(Player player) {
        if (!existPlayer(player)) {
            createPlayer(player);
        }
        return Ranks.valueOf(getRankConfig().getConfig().getString("Player." + player.getName() + ".Rank"));
    }
    public Ranks getRank(String player) {
        if (!existPlayer(player)) {
            createPlayer(player);
        }
        return Ranks.valueOf(getRankConfig().getConfig().getString("Player." + player + ".Rank"));
    }
    public Integer getNextRankInteger(Player player) {
        if (existPlayer(player)) {
            return getRank(player).getOrder() + 1;
        }
        return null;
    }
    public Integer getNextRankInteger(String player) {
        if (existPlayer(player)) {
            return getRank(player).getOrder() + 1;
        }
        return null;
    }
    public Ranks getNextRank(String player) {
        if (existPlayer(player)) {
            for(Ranks ranks : Ranks.values()) {
                if (ranks.getOrder().equals(getNextRankInteger(player))) {
                    return ranks;
                }
            }
        }
        return null;
    }
    public Ranks getNextRank(Player player) {
        if (existPlayer(player)) {
            for(Ranks ranks : Ranks.values()) {
                if (ranks.getOrder().equals(getNextRankInteger(player))) {
                    return ranks;
                }
            }
        }
        return null;
    }
    public void setNextRank(Player player) {
        getRankConfig().getConfig().set("Player." + player.getName() + ".Rank", getNextRank(player).toString());
        getRankConfig().saveConfig();
        new SaldoManager().removeSaldo(player, getNextRank(player).getCost());
    }
    public void setNextRank(String player) {
        getRankConfig().getConfig().set("Player." + player + ".Rank", getNextRank(player).toString());
        getRankConfig().saveConfig();
        new SaldoManager().removeSaldo(player, getNextRank(player).getCost());
    }
}
