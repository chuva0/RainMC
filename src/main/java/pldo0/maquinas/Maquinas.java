package pldo0.maquinas;

import org.bukkit.Material;
import pldo0.api_externa.ColourEffects;

public enum Maquinas {

    NUVEM("Nuvem",
            ColourEffects.gradientEffect("[Nuvem]", ColourEffects.getColour(50, 123, 163), ColourEffects.getColour(103, 141, 201)),
            "Produz nuvens!", 420000l, Material.WHITE_WOOL),
    ;


    String nome,displayname,desc;
    Integer cooldown;
    Long price;
    Material material;

    Maquinas(String nome, String displayname, String desc, Long price, Material material) {
        this.desc = desc;
        this.nome = nome;
        this.displayname = displayname;
        this.price = price;
        this.material = material;
    }

    public String getNome() {
        return nome;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public Long getPrice() {
        return price;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayname() {
        return displayname;
    }
}
