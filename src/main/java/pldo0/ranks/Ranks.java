package pldo0.ranks;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public enum Ranks {

    TERRA3(ChatColor.of(new Color(205, 166, 100)) + "[TerraIII]", 5000l, 0, 10*0.1, RanksType.TERRA),
    TERRA2(ChatColor.of(new Color(205, 166, 100)) + "[TerraII]", 5800l, 1,10*0.1, RanksType.TERRA),
    TERRA1(ChatColor.of(new Color(205, 166, 100)) + "[TerraI]", 6300l, 2,10*0.1, RanksType.TERRA),
    PEDRA3(ChatColor.of(new Color(174, 185, 176)) + "[PedraIII]", 7500l, 3,12*0.1, RanksType.PEDRA),
    PEDRA2(ChatColor.of(new Color(174, 185, 176)) + "[PedraII]", 9500l, 4,12*0.1, RanksType.PEDRA),
    PEDRA1(ChatColor.of(new Color(174, 185, 176)) + "[PedraI]", 12000l, 5,12*0.1, RanksType.PEDRA),
    LAPIS3(ChatColor.of(new Color(126, 134, 213)) + "[LapisIII]", 30000l, 6,14*0.1, RanksType.LAPIS),
    LAPIS2(ChatColor.of(new Color(126, 134, 213)) + "[LapisII]", 45000l, 7,14*0.1, RanksType.LAPIS),
    LAPIS1(ChatColor.of(new Color(126, 134, 213)) + "[LapisI]", 50000l, 8,14*0.1, RanksType.LAPIS),
    FERRO3(ChatColor.of(new Color(195, 222, 212)) + "[FerroIII]", 60000l, 9,16*0.1, RanksType.FERRO),
    FERRO2(ChatColor.of(new Color(195, 222, 212)) + "[FerroII]", 72000l, 10,16*0.1, RanksType.FERRO),
    FERRO1(ChatColor.of(new Color(195, 222, 212)) + "[FerroI]", 80000l, 11,16*0.1, RanksType.FERRO),
    REDSTONE3(ChatColor.of(new Color(145, 31, 57)) + "[RedstoneIII]", 95000l, 12,19*0.1, RanksType.REDSTONE),
    REDSTONE2(ChatColor.of(new Color(145, 31, 57)) + "[RedstoneII]", 105500l, 13,19*0.1, RanksType.REDSTONE),
    REDSTONE1(ChatColor.of(new Color(145, 31, 57)) + "[RedstoneI]", 110200l, 14,19*0.1, RanksType.REDSTONE),
    OURO3(ChatColor.of(new Color(204, 173, 88)) + "[OuroIII]", 116000l, 15,21*0.1, RanksType.OURO),
    OURO2(ChatColor.of(new Color(204, 173, 88)) + "[OuroII]", 120000l, 16,21*0.1, RanksType.OURO),
    OURO1(ChatColor.of(new Color(204, 173, 88)) + "[OuroI]", 128000l, 17,21*0.1, RanksType.OURO),
    DIAMANTE3(ChatColor.of(new Color(105, 179, 194)) + "[DiamanteIII]", 133000l, 18,24*0.1, RanksType.DIAMANTE),
    DIAMANTE2(ChatColor.of(new Color(105, 179, 194)) + "[DiamanteII]", 155000l, 19,24*0.1, RanksType.DIAMANTE),
    DIAMANTE1(ChatColor.of(new Color(105, 179, 194)) + "[DiamanteI]", 175000l, 20,24*0.1, RanksType.DIAMANTE),
    OBSIDIAN3(ChatColor.of(new Color(61, 34, 108)) + "[ObsidianIII]", 200000l, 21,26*0.1, RanksType.OBSIDIAN),
    OBSIDIAN2(ChatColor.of(new Color(61, 34, 108)) + "[ObsidianII]", 215000l, 22,26*0.1, RanksType.OBSIDIAN),
    OBSIDIAN1(ChatColor.of(new Color(61, 34, 108)) + "[ObsidianI]", 225000l, 23,26*0.1, RanksType.OBSIDIAN),
    BEDROCK3(ChatColor.of(new Color(59, 59, 68)) + "[BedrockIII]", 260000l, 24,30*0.1, RanksType.BEDROCK),
    BEDROCK2(ChatColor.of(new Color(59, 59, 68)) + "[BedrockII]", 290000l, 25,31*0.1, RanksType.BEDROCK),
    BEDROCK1(ChatColor.of(new Color(59, 59, 68)) + "[BedrockI]", 350000l, 26,32*0.1, RanksType.BEDROCK),
    ;

    String prefix;
    Long cost;
    Integer order;
    Double mult;
    RanksType type;

    Ranks(String prefix, Long cost, Integer order, Double mult, RanksType type) {
        this.prefix = prefix;
        this.cost = cost;
        this.order = order;
        this.mult = mult;
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public Long getCost() {
        return cost;
    }

    public Integer getOrder() {
        return order;
    }

    public RanksType getType() {
        return type;
    }

    public Double getMult() {
        return mult;
    }
}
