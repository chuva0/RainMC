package pldo0.comandos;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import pldo0.grupos.GrupoManager;
import pldo0.grupos.Grupos;
import pldo0.kits.KitInventory;
import pldo0.kits.KitsItens;
import pldo0.maquinas.MaquinaInventory;
import pldo0.punish.PunishmentInventory;
import pldo0.punish.PunishmentManager;
import pldo0.ranks.Ranks;
import pldo0.ranks.RanksInvetory;
import pldo0.saldo.SaldoManager;
import pldo0.utils.BukkitItemStack;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pldo0.Pldozero.*;

public class Comandos extends SaldoManager implements CommandExecutor {

    public static ArrayList<Player> rankup = new ArrayList<>();
    public static ArrayList<Player> combustivel = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("magnata")) {
            if (args.length == 0) {
                player.sendMessage("§7O atual §d[Magnata] §7e: §f" + getTop() + " §a("+formatMoney(getTop())+")");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("tell")) {
            if (args.length==0) {
                player.sendMessage(ChatColor.GRAY + "Use /tell " + ChatColor.GREEN + " (player) (mensagem)");
                return true;
            }
            if (args.length > 0) {
                String player_recive = args[0];
                List<String> mensagem = new ArrayList<>();
                for (int i = 1; i < (args.length); i++) {
                    mensagem.add(args[i]);
                }
                if (args.length < 2) {
                    player.sendMessage(ChatColor.GRAY + "Use /tell " + ChatColor.GREEN +player_recive+" (mensagem)");
                    return true;
                } else {
                    if (player.getName().equalsIgnoreCase(player_recive)) {
                        player.sendMessage(ChatColor.RED + "Esta enviando tell para voce mesmo!");
                        return true;
                    }
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (on.getName().equalsIgnoreCase(player_recive)) {
                            on.sendMessage(ChatColor.DARK_GREEN + "[Mensagem de "+player.getName()+"] " + ChatColor.GREEN + mensagem.stream().collect(Collectors.joining(" ")));
                            player.sendMessage(ChatColor.DARK_GREEN + "[Enviado a "+on.getName()+"] " + ChatColor.GREEN + mensagem.stream().collect(Collectors.joining(" ")));
                            return true;
                        }
                    }
                    player.sendMessage(ChatColor.RED + "Jogador offline!");
                }
            }
        }
        if (command.getName().equalsIgnoreCase("bc")) {
            if (!hasPermission(player, Grupos.MODERADOR.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage("§aUse /bc (mensagem)");
                return true;
            }
            if (args.length > 0) {
                List<String> bc = new ArrayList<>();

                for (int i = 0; i < args.length; i++) {
                    bc.add(args[i]);
                }

                String finalbc = bc.stream().collect(Collectors.joining(" "));

                Bukkit.getOnlinePlayers().stream().forEach(e -> e.sendMessage(""));
                Bukkit.getOnlinePlayers().stream().forEach(e -> e.sendMessage("§e[Anuncio da Moderacao] §f" + finalbc));
                Bukkit.getOnlinePlayers().stream().filter(e->hasPermission(e, Grupos.BUILDER.getWeight()))
                        .forEach(e -> e.sendMessage("§e[Anuncio da Moderacao] §7§oAnuncio feito por: " + e.getName() + " (visivel somente para staff)"));
                Bukkit.getOnlinePlayers().stream().forEach(e -> e.sendMessage(""));


                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("rankup")) {
            if (args.length > 0) {
                return true;
            }
            if (getRank(player).equals(Ranks.OBSIDIAN1)) {
                player.sendMessage(ChatColor.GREEN + "Voce esta no nosso atual ultimo rank! Parabens! Aguarde as atualizacoes de rank para poder prosseguir!");
                return true;
            }
            if (!existPlayer(player)) {
                player.sendMessage(ChatColor.RED + "§e[RankUp] §7Seus dados ainda nao foram salvos no servidor! Contate alguem da equipe de moderacao e envie este erro!");
                return true;
            }
            if (!rankup.contains(player)) {
                if(!(getSaldo(player) >= getNextRank(player).getCost())) {
                    player.sendMessage(ChatColor.RED + "§e[RankUp] §7Voce ainda nao pode ir para o proximo rank! Faltam " +
                            ChatColor.GRAY + (getNextRank(player).getCost() - getSaldo(player)) +
                            ChatColor.RED + " de money");
                    StringBuilder sb = new StringBuilder();
                    long barRankPlayer = ((getSaldo(player)*100)/getNextRank(player).getCost())/5;
                    long restBar = 20 - barRankPlayer;
                    for (int i = 0; i< barRankPlayer; i++) {
                        sb.append(ChatColor.GREEN + "|");
                    }
                    for (int i = 0; i< restBar; i++) {
                        sb.append(ChatColor.GRAY + "|");
                    }
                    player.sendMessage("§e[RankUp] §7Progresso: (" + sb + ")");
                    return true;
                }
                player.sendMessage(ChatColor.WHITE + "§e[RankUp] §7Voce tem certeza que deseja ir para o proximo rank? Se sim digite §f/rankup §7novamente, caso nao, digite " +
                        "§ccancelar §7no chat!");
                rankup.add(player);
            } else {
                if(getSaldo(player) >= getNextRank(player).getCost()) {
                    player.sendMessage(ChatColor.GREEN + "§e[RankUp] §aVoce passou de rank!");
                    setNextRank(player);
                    rankup.remove(player);
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        on.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§e[RankUp] §7" + player.getName() + " upou para " + getRank(player).getPrefix()));
                    }
                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("tempos")) {
            if(!hasPermission(player, Grupos.GERENTE.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                for(String on : getRankConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
                    if (getRankConfig().getConfig().getBoolean("Player." + on + ".hasTempo")) {
                        long restTemp = (getTempo(on) - System.currentTimeMillis());
                        player.sendMessage(ChatColor.YELLOW +
                                "[Log-Grupos] " + ChatColor.GRAY +""+ChatColor.ITALIC+ on + " (Grupo atual: "+getGroup(on).toString()+") expira em: " +
                                ChatColor.GREEN + KitsItens.formatador(restTemp)
                        );
                    }
                }
            }
            if (args.length == 1) {
                for (Grupos grupos : Grupos.values()) {
                    if (args[0].equalsIgnoreCase(grupos.toString())) {
                        for(String on : getRankConfig().getConfig().getConfigurationSection("Player").getKeys(false)) {
                            if (getRankConfig().getConfig().getBoolean("Player." + on + ".hasTempo") && getGroup(on).equals(Grupos.valueOf(args[0].toUpperCase()))) {
                                long restTemp = (getTempo(on) - System.currentTimeMillis());
                                player.sendMessage(ChatColor.YELLOW +
                                        "[Log-Grupos] " + ChatColor.GRAY +""+ChatColor.ITALIC+ on + " (Grupo atual: "+getGroup(on).toString()+") expira em: " +
                                        ChatColor.GREEN + KitsItens.formatador(restTemp)
                                );
                            }
                        }
                    }
                }
            }
        }
        if (command.getName().equalsIgnoreCase("ranks")) {
            if (args.length > 0) {
                return true;
            }
            RanksInvetory.ranksInv(player);
            return true;
        }
        if (command.getName().equalsIgnoreCase("tempo")) {
            if (args.length>0) {
                return true;
            }
            if (!hasTempGroup(player)) {
                player.sendMessage(ChatColor.RED + "Voce nao esta em nenhum grupo temporario!");
                return true;
            }
            long restTemp = (getTempo(player) - System.currentTimeMillis());
            if (restTemp < 3) {
                player.sendMessage(ChatColor.GREEN + "O seu " + getGroup(player).getPrefix() + ChatColor.GREEN + " expira em alguns segundos!");
                return true;
            }
            player.sendMessage(ChatColor.GREEN + "O seu " + getGroup(player).getPrefix() + ChatColor.GREEN + " expira em " +
                    KitsItens.formatador(restTemp));
            return true;
        }
        if (command.getName().equalsIgnoreCase("forcegrupo")) {
            if (!hasPermission(player, Grupos.ADMINISTRADOR.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Use /forcegrupo (player) para remove-lo do grupo temporario!");
                return true;
            }
            if (args.length==1) {
                String target = args[0];
                if (!hasTempGroup(target)) {
                    player.sendMessage(ChatColor.RED + "Este jogador nao esta num grupo temporario!");
                    return true;
                }
                new GrupoManager().setGroupPlayer(target, Grupos.valueOf(
                        getRankConfig().getConfig().getString("Player." + target + ".LastGrupo")
                ));
                getRankConfig().getConfig().set("Player." + target + ".LastGrupo", null);
                getRankConfig().getConfig().set("Player." + target + ".hasTempo", false);
                getRankConfig().getConfig().set("Player." + target + ".Tempo", null);
                getRankConfig().saveConfig();
                for (Player onn : Bukkit.getOnlinePlayers()) {
                    if (onn.getName().equals(target)) {
                        onn.sendMessage(ChatColor.GREEN + "Seu grupo atual foi interrompido!");
                        new GrupoManager().entryTeam(onn);
                    }
                }
            }
        }
        if (command.getName().equalsIgnoreCase("grupo")) {
            if (!hasPermission(player, Grupos.MODERADOR.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Use /grupo (player) (grupo)");
                return true;
            }
            if (args.length>=1) {
                if (!existPlayer(args[0])) {
                    player.sendMessage(ChatColor.RED + "Jogador nao existe!");
                    return true;
                }
                if (args.length < 3) {
                    String grupo = args[1];
                    List<String> gruposs = new ArrayList<>();
                    gruposs.clear();
                    Arrays.asList(Grupos.values()).stream().forEach(e -> gruposs.add(e.toString()));
                    if (!gruposs.contains(grupo.toUpperCase())) {
                        player.sendMessage(ChatColor.RED + "Grupo inexistente!");
                        return true;
                    }
                    if ((!getGroup(player).equals(Grupos.MASTER)) && (getGroup(player).getWeight() - 1) <= Grupos.valueOf(grupo.toUpperCase()).getWeight()) {
                        player.sendMessage(ChatColor.RED + "Voce nao pode adicionar este cargo ao jogador!");
                        return true;
                    }
                    if ((!getGroup(player).equals(Grupos.MASTER)) && Grupos.valueOf(grupo.toUpperCase()).getWeight() >= getGroup(player).getWeight()) {
                        player.sendMessage(ChatColor.RED + "Voce nao pode adicionar este cargo ao jogador!");
                        return true;
                    }
                    for (Grupos grupos : Grupos.values()) {
                        if (grupos.toString().equals(grupo.toUpperCase())) {
                            player.sendMessage(ChatColor.GREEN + "Voce colocou o jogador " + args[0] + " como " + grupos.getPrefix());
                            setGroupPlayer(args[0], grupos);
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                if (on.getName().equalsIgnoreCase(args[0])) {
                                    entryTeam(on);
                                    on.sendMessage(ChatColor.GREEN + player.getName() + " colocou voce no grupo " + grupos.getPrefix());
                                    return true;
                                }
                            }
                            return true;
                        }
                    }
                }
                else if (args.length<4){
                    player.sendMessage(ChatColor.GREEN + "Use /grupo " + args[0] + " (grupo) ([opcional] tempo, ex: /grupo "+args[0]+" grupo 30 d)");
                    return true;
                }
                if (args.length == 4) {
                    String grupo = args[1];
                    List<String> gruposs = new ArrayList<>();
                    gruposs.clear();
                    Arrays.asList(Grupos.values()).stream().forEach(e -> gruposs.add(e.toString()));
                    if (!gruposs.contains(grupo.toUpperCase())) {
                        player.sendMessage(ChatColor.RED + "Grupo inexistente!");
                        return true;
                    }
                    if ((!getGroup(player).equals(Grupos.MASTER)) && (getGroup(player).getWeight() - 1) <= Grupos.valueOf(grupo.toUpperCase()).getWeight()) {
                        player.sendMessage(ChatColor.RED + "Voce nao pode adicionar este cargo ao jogador!");
                        return true;
                    }
                    if ((!getGroup(player).equals(Grupos.MASTER)) && Grupos.valueOf(grupo.toUpperCase()).getWeight() >= getGroup(player).getWeight()) {
                        player.sendMessage(ChatColor.RED + "Voce nao pode adicionar este cargo ao jogador!");
                        return true;
                    }
                    if (hasTempGroup(args[0])) {
                        player.sendMessage(ChatColor.RED + "Jogador ja esta em grupo temporario! Use /forcegrupo " + args[0]);
                        return true;
                    }
                    int quantia;
                    List<String> times = new ArrayList<>();
                    times.add("minutos");
                    times.add("dias");
                    times.add("horas");
                    times.add("meses");
                    times.add("segundos");
                    try {
                        quantia = Integer.parseInt(args[2]);
                        if (!times.contains(args[3])) {
                            player.sendMessage(ChatColor.RED + "Coloque parametros de tempo validos!");
                            return true;
                        }
                        for (Grupos grupos : Grupos.values()) {
                            if (grupos.toString().equals(grupo.toUpperCase())) {
                                player.sendMessage(ChatColor.GREEN + "Voce colocou o jogador " + args[0] + " como " + grupos.getPrefix() + ChatColor.GREEN + " por " + quantia + " "+ args[3]);
                                setTempGroupPlayer(args[0], grupos, converterTempoCargo(args[3], quantia));
                                if (grupos.equals(Grupos.CHUVA) || grupos.equals(Grupos.TEMPORAL) || grupos.equals(Grupos.TEMPESTADE)) {
                                    for (Player on : Bukkit.getOnlinePlayers()) {
                                        on.sendTitle(ChatColor.GREEN + args[0]+" agora é " + grupos.getPrefix(),
                                                ChatColor.GRAY + "Compre vip voce tambem em " + ChatColor.AQUA + "http://rainmc.com.br", 20,100,20);
                                    }
                                }
                                if (grupos.equals(Grupos.AJUDANTE)) {
                                    for (Player on : Bukkit.getOnlinePlayers()) {
                                        on.sendTitle(ChatColor.GREEN + args[0]+" agora é " + grupos.getPrefix(),
                                                ChatColor.GRAY + "Ele esta fazendo o teste para integrar a staff!", 20,100,20);
                                    }
                                }
                                return true;
                            }
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Insira um valor numerico no tempo!");
                        return true;
                    }
                }
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("maquinas") || command.getName().equalsIgnoreCase("maquina")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "                   Maquinas");

                player.sendMessage(" ");
                player.sendMessage(ChatColor.GREEN + "Para ver suas maquinas, use: /maquina ver");
                player.sendMessage(ChatColor.GREEN + "Para comprar uma maquina, use: /maquina comprar");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.GREEN + "                   Maquinas");

            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("ver")) {
                    MaquinaInventory.guiMaquinasPlayer(player);
                }
                if (args[0].equalsIgnoreCase("comprar")) {
                    MaquinaInventory.guiComprarMaquinas(player);
                }
            }
            return true;
        }
        if (command.getName().equalsIgnoreCase("combustivel")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "                      Maquinas");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.GREEN + "Para comprar combustivel, use: /combustivel comprar");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.GREEN + "                      Maquinas");

            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("comprar")) {

                    player.sendMessage(ChatColor.GREEN + "                      Maquinas");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.GREEN + "Cada combustivel custa "+ChatColor.DARK_GREEN+"50k"+ChatColor.GREEN+" de money! Digite no chat quantos");
                    player.sendMessage(ChatColor.GREEN + "               combustiveis voce deseja comprar!");
                    player.sendMessage(ChatColor.GREEN + "");
                    player.sendMessage(ChatColor.GREEN + "         Para cancelar a operacao, digite " + ChatColor.RED + "cancelar");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.GREEN + "                      Maquinas");

                    combustivel.add(player);

                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("s")) {
            if (hasPermission(player, Grupos.BUILDER.getWeight())) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.GREEN + "Use /s (mensagem)");
                    player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "       (Apenas jogador com ");
                    player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "    cargo Builder pra cima");
                    player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "  conseguira ver as mensagens)");
                } else {
                    List<String> mensagem = new ArrayList<>();

                    for (int i = 0; i < args.length; i++) {
                        mensagem.add(args[i]);
                    }

                    String msg = mensagem.stream().collect(Collectors.joining(" "));

                    for (Player on : Bukkit.getOnlinePlayers()) {
                        if (hasPermission(on, Grupos.BUILDER.getWeight())) {
                            on.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "(Chat de moderacao) " +getGroup(player).getPrefix() + " " + ChatColor.WHITE + player.getName() + " : " + msg);
                        }
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
            }
        }
        if (command.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Seu money: " + formatMoney(player));
                if (hasPermission(player, Grupos.ADMINISTRADOR.getWeight())) {
                    player.sendMessage(ChatColor.GREEN + "Use /money (adicionar / remover) (player) (quantidade)");
                }
                return true;
            }
            if (args.length >= 1) {
                if (!hasPermission(player, Grupos.ADMINISTRADOR.getWeight())) {
                    if (args[0].equalsIgnoreCase("pay")) {
                        if (args.length < 3) {
                            player.sendMessage(ChatColor.GREEN + "Use /money pay (player) (quantidade)");
                            return true;
                        }
                        String target = args[1];
                        Long quantidade;
                        if (target.length() > 16) {
                            player.sendMessage(ChatColor.RED + "O jogador tem mais de 16 caracteres em seu nome!");
                            return true;
                        }
                        if (!existPlayer(target)) {
                            player.sendMessage(ChatColor.RED + "Jogador nunca entrou no servidor!");
                            return true;
                        }
                        if (player.getName().equalsIgnoreCase(target)) {
                            player.sendMessage(ChatColor.RED + "Voce nao pode tentar se dar money!");
                            return true;
                        }
                        try {
                            quantidade = Long.parseLong(args[2]);
                            if (!(getSaldo(player) >= quantidade)) {
                                player.sendMessage(ChatColor.RED + "Voce nao tem money suficiente para isso!");
                                return true;
                            }
                            if (quantidade < 0) {
                                player.sendMessage(ChatColor.RED + "Use valores positivos!");
                                return true;
                            }
                            String number = String.valueOf(quantidade);

                            removeSaldo(player, quantidade.longValue());
                            addSaldo(target, quantidade.longValue());

                            player.sendMessage(ChatColor.GREEN + "Voce deu " + number + " para " + target);
                            setTab(player);
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                if (on.getName().equalsIgnoreCase(target)) {
                                    setTab(on);
                                    on.sendMessage(ChatColor.GREEN + "Voce recebeu " + number + " de " + player.getName());
                                }
                            }
                            return true;

                        } catch (NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "A quantidade inserida nao eh um valor numerico!");
                            return true;
                        }
                    }
                    if (existPlayer(args[0])) {
                        player.sendMessage(ChatColor.GREEN + "Money de " + args[0] + ": " + formatMoney(args[0]));
                    } else {
                        player.sendMessage(ChatColor.RED + "Jogador nao existe!");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("adicionar")) {
                    if (args.length < 3) {
                        player.sendMessage(ChatColor.GREEN + "Use /money adicionar (player) (quantidade)");
                        return true;
                    }
                    String target = args[1];
                    Long quantidade;
                    if (target.length() > 16) {
                        player.sendMessage(ChatColor.RED + "O jogador tem mais de 16 caracteres em seu nome!");
                        return true;
                    }
                    try {
                        quantidade = Long.parseLong(args[2]);

                        if (quantidade < 0) {
                            player.sendMessage(ChatColor.RED + "Use valores positivos!");
                            return true;
                        }

                        String number = String.valueOf(quantidade);

                        addSaldo(target, quantidade.longValue());

                        player.sendMessage(ChatColor.GREEN + "Voce adicionou " + number + " para " + target);
                        for (Player on : Bukkit.getOnlinePlayers()) {
                            if (on.getName().equalsIgnoreCase(target)) {
                                setTab(on);
                            }
                        }
                        return true;

                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "A quantidade inserida nao eh um valor numerico!");
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("remover")) {
                    if (args.length < 3) {
                        player.sendMessage(ChatColor.GREEN + "Use /money remover (player) (quantidade)");
                        return true;
                    }
                    String target = args[1];
                    Long quantidade;
                    if (target.length() > 16) {
                        player.sendMessage(ChatColor.RED + "O jogador tem mais de 16 caracteres em seu nome!");
                        return true;
                    }
                    try {
                        quantidade = Long.parseLong(args[2]);
                        if (quantidade < 0) {
                            player.sendMessage(ChatColor.RED + "Use valores positivos!");
                            return true;
                        }
                        String number = String.valueOf(quantidade);

                        removeSaldo(target, quantidade.longValue());

                        player.sendMessage(ChatColor.GREEN + "Voce removeu " + number + " para " + target);
                        for (Player on : Bukkit.getOnlinePlayers()) {
                            if (on.getName().equalsIgnoreCase(target)) {
                                setTab(on);
                            }
                        }
                        return true;

                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "A quantidade inserida nao eh um valor numerico!");
                        return true;
                    }
                }
            }
        }
        if (command.getName().equalsIgnoreCase("kit") || command.getName().equalsIgnoreCase("kits")) {
            KitInventory.guiKits(player);
        }
        if (command.getName().equalsIgnoreCase("blocos")) {
            if (!hasPermission(player, Grupos.ADMINISTRADOR.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            player.getInventory().addItem(new BukkitItemStack(Material.LAPIS_BLOCK).setDisplayName(ChatColor.GREEN + "Mina").finish());
        }
        if (command.getName().equalsIgnoreCase("terreno")) {
            Location loc = player.getLocation();
            String world = loc.getWorld().getName();
            int x = (int) loc.getX();
            int z = (int) loc.getZ();
            for (double i = (x-3); i < (x+3); i++) {
                for (double j = (z-3); j < (z+3); j++) {
                    Random r = new Random();
                    int rr = r.nextInt(10000000);
                    if (getTerrenoConfig().getConfig().contains("Player." + player.getName())) {
                        for (String b : getTerrenoConfig().getConfig().getConfigurationSection("Player." + player.getName()).getKeys(false)) {
                            if (rr == Integer.valueOf(b)) {
                                rr = r.nextInt(10000000);
                            }
                        }
                    }
                    getTerrenoConfig().getConfig().set("Player." + player.getName() + "."+rr+".Location.World",world);
                    getTerrenoConfig().getConfig().set("Player." + player.getName() + "."+rr+".Location.X",i);
                    getTerrenoConfig().getConfig().set("Player." + player.getName() + "."+rr+".Location.Z",j);
                    getTerrenoConfig().saveConfig();
                }
            }
            player.sendMessage(ChatColor.GREEN + "Area protegida!");
        }
        if (command.getName().equalsIgnoreCase("punir")) {
            if (!hasPermission(player, Grupos.AJUDANTE.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Use /punir (jogador)");
                return true;
            }
            if (args.length == 1) {
                String target = args[0];
                PunishmentInventory.guiPunish(player, target);
            }
        }
        if (command.getName().equalsIgnoreCase("despunir")) {
            if (!hasPermission(player, Grupos.AJUDANTE.getWeight())) {
                player.sendMessage(ChatColor.RED + "Sem permissao!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Use /despunir (jogador)");
                return true;
            }
            if (args.length == 1) {
                String target = args[0];
                if ((!PunishmentManager.isBan(target) && !PunishmentManager.isMute(target))) {
                    player.sendMessage(ChatColor.RED + "Este jogador nao tem nenhuma punicao!");
                    return true;
                }
                PunishmentManager.punish.getConfig().set("Player." + target, null);
                PunishmentManager.punish.saveConfig();
                player.sendMessage(ChatColor.GREEN + "Voce despuniu " + target);
            }
        }
        return false;
    }

    public static long converterTempoCargo(String p, int amount) {
        if (p.equalsIgnoreCase("dias")) {
            return System.currentTimeMillis() + TimeUnit.DAYS.toMillis(amount);
        }
        if (p.equalsIgnoreCase("minutos")) {
            return System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(amount);
        }
        if (p.equalsIgnoreCase("segundos")) {
            return System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(amount);
        }
        if (p.equalsIgnoreCase("horas")) {
            return System.currentTimeMillis() + TimeUnit.HOURS.toMillis(amount);
        }
        if (p.equalsIgnoreCase("meses")) {
            return System.currentTimeMillis() + (TimeUnit.DAYS.toMillis(amount*30));
        }
        return System.currentTimeMillis();
    }
}
