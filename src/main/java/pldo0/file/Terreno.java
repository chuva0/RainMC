package pldo0.file;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static pldo0.Pldozero.getInstance;

public class Terreno {

    private File file;
    private FileConfiguration fileConfiguration;

    public Terreno() {
        file = new File(getInstance().getDataFolder(), "terreno.yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Criando terreno.yml! (Nao existia!)");
                loadConfiguration();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Nao foi possivel criar terreno.yml! Erro: " + e);
            }
        }
    }
    public FileConfiguration getConfig() {
        return fileConfiguration;
    }
    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Nao foi possivel salvar terreno.yml");
        }
    }
    public void loadConfiguration() {
        getConfig().createSection("Player");
        saveConfig();
    }
}
