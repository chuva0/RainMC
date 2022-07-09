package pldo0.punish;

public enum Punishment {

    BANIMENTO("Banido"),
    KICK("Kickado"),
    MUTE("Mutado"),
    ;

    private String nome;

    Punishment (String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public String toString() {
        return getNome();
    }
}
