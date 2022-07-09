package pldo0.grupos;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import pldo0.api_externa.ColourEffects;

import java.awt.*;

public enum Grupos {

    MEMBRO(ChatColor.GRAY + "", 0, "z", Material.GRAY_BANNER),
    CHUVA(ColourEffects.gradientEffect("[Chuva]", ColourEffects.getColour(152, 185, 245), ColourEffects.getColour(110, 154, 235)), 1, "y", Material.BLUE_BANNER),
    TEMPESTADE(ColourEffects.gradientEffect("[Tempestade]", ColourEffects.getColour(0, 125, 154), ColourEffects.getColour(11, 93, 112)), 2, "x", Material.BLUE_BANNER),
    TEMPORAL(ColourEffects.gradientEffect("[Temporal]", ColourEffects.getColour(71, 69, 183), ColourEffects.getColour(44, 43, 138)), 3, "w", Material.BLUE_BANNER),
    YOUTUBER(ColourEffects.gradientEffect("[Youtuber]", ColourEffects.getColour(150, 35, 56), ColourEffects.getColour(94, 17, 31)), 4, "v", Material.RED_BANNER),
    STREAMER(ColourEffects.gradientEffect("[Streamer]", ColourEffects.getColour(127, 17, 173), ColourEffects.getColour(88, 11, 120)), 4, "u", Material.RED_BANNER),
    BUILDER(ColourEffects.gradientEffect("[Builder]", ColourEffects.getColour(86, 54, 99), ColourEffects.getColour(64, 32, 77)), 5, "t", Material.BLACK_BANNER),
    AJUDANTE(ColourEffects.gradientEffect("[Ajudante]", ColourEffects.getColour(176, 166, 88), ColourEffects.getColour(145, 134, 47)), 6, "s", Material.YELLOW_BANNER),
    MODERADOR(ColourEffects.gradientEffect("[Moderador]", ColourEffects.getColour(27, 77, 22), ColourEffects.getColour(13, 54, 9)), 7, "r", Material.GREEN_BANNER),
    ADMINISTRADOR(ColourEffects.gradientEffect("[Admin]", ColourEffects.getColour(153, 34, 58), ColourEffects.getColour(117, 8, 30)), 8, "q", Material.RED_BANNER),
    GERENTE(ColourEffects.gradientEffect("[Gerente]", ColourEffects.getColour(184, 115, 66), ColourEffects.getColour(120, 63, 23)), 9, "p", Material.YELLOW_BANNER),
    MASTER(ColourEffects.gradientEffect("[Master]", ColourEffects.getColour(45, 156, 108), ColourEffects.getColour(10, 87, 54)), 10, "o", Material.RED_BANNER);
    String prefix;
    Integer weight;
    String order;
    Material m;

    Grupos(String prefixo, Integer weight, String order, Material m) {
        this.prefix = prefixo;
        this.weight = weight;
        this.order = order;
        this.m = m;
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getOrder() {
        return order;
    }

    public Material getM() {
        return m;
    }
}
