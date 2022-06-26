package com.matt4499.msmp;
import com.matt4499.msmp.commands.*;
import com.matt4499.msmp.events.*;
import com.matt4499.msmp.homes.*;
import com.matt4499.msmp.types.DataHelper;
import com.matt4499.msmp.types.DiscordWebhook;
import com.matt4499.msmp.warps.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class Main extends JavaPlugin implements Listener {
    public static HashMap<UUID, Boolean> afkmap = new HashMap<>();
    public static HashMap<Player, Player> lastsent = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static FileConfiguration playerdata;
    public static File playersConfigFile;
    public static JavaPlugin instance;
    public static HomeHelper homeHelper;
    public static WarpHelper warpHelper;
    @Override
    public void onEnable() {

        instance = this;
        homeHelper = new HomeHelper();
        warpHelper = new WarpHelper();


        playersConfigFile = new File(getDataFolder(), "players.yml");
        playerdata = YamlConfiguration.loadConfiguration(playersConfigFile);

        startScoreboardTimer();
        FreezeCommand freezeCommand = new FreezeCommand();
        WarpCommand warpCommand = new WarpCommand();
        Bukkit.getPluginManager().registerEvents(new AFKCommand(), this);
        Bukkit.getPluginManager().registerEvents(new DataHelper(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new AntiPVPDrop(), this);
        Bukkit.getPluginManager().registerEvents(new JoinLeaveMessages(), this);
        Bukkit.getPluginManager().registerEvents(new CrateHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CrateItemHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerNameChat(), this);
        Bukkit.getPluginManager().registerEvents(new DeathCoordinates(), this);
        Bukkit.getPluginManager().registerEvents(new OreLogs(), this);
        Bukkit.getPluginManager().registerEvents(new ChatFilter(), this);
        Bukkit.getPluginManager().registerEvents(new AntiMobGrief(), this);
        Bukkit.getPluginManager().registerEvents(new PunishCommand(), this);
        Bukkit.getPluginManager().registerEvents(new MOTD(), this);
        Bukkit.getPluginManager().registerEvents(new AntiSpam(), this);
        Bukkit.getPluginManager().registerEvents(new TNTLogger(), this);
        Bukkit.getPluginManager().registerEvents(new SignColor(), this);
        Bukkit.getPluginManager().registerEvents(freezeCommand, this);
        Bukkit.getPluginManager().registerEvents(new hoeaoe(), this);
        Bukkit.getPluginManager().registerEvents(warpCommand, this);

        getCommand("afk").setExecutor(new AFKCommand());
        getCommand("tell").setExecutor(new TellCommand());
        getCommand("msg").setExecutor(new TellCommand());
        getCommand("message").setExecutor(new TellCommand());
        getCommand("whisper").setExecutor(new TellCommand());
        getCommand("r").setExecutor(new ReplyCommand());
        getCommand("f").setExecutor(new FCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("pog").setExecutor(new POGCommand());
        getCommand("l").setExecutor(new LCommand());
        getCommand("givecrate").setExecutor(new GiveCrateCommand());
        getCommand("votekey").setExecutor(new ConsoleVoteCrate());
        getCommand("trash").setExecutor(new TrashCommand());
        getCommand("tp").setExecutor(new TPCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("tpc").setExecutor(new TPCCommand());
        getCommand("staffmode").setExecutor(new StaffModeCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tpayes").setExecutor(new TPAAcceptCommand());
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("clearchat").setExecutor(new CCCommand());
        getCommand("ccb").setExecutor(new CCBanCommand());
        getCommand("show").setExecutor(new ShowCommand());
        getCommand("freeze").setExecutor(freezeCommand);
        getCommand("seen").setExecutor(new SeenCommand());

        getCommand("warp").setExecutor(warpCommand);
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
        getCommand("warpinfo").setExecutor(new WarpInfoCommand());

        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("homes").setExecutor(new HomesCommand());
    }
    @Override
    public void onDisable() {
        homeHelper.saveHomes();
        warpHelper.saveWarps();
        try {
            playerdata.save(playersConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object getData(UUID id, String key) {
        var object = playerdata.get(id + "." + key);
        if(object == null) {
            Player p = Bukkit.getPlayer(id);
            assert p != null;
            switch (key) {
                case "totaljoins" -> {
                    setData(id, "totaljoins", 0);
                    object = 0;
                }
                case "firstjoin" -> {
                    setData(id, "firstjoin", System.currentTimeMillis());
                    object = System.currentTimeMillis();
                }
                case "totalls" -> {
                    setData(id, "totalls", 0);
                    object = 0;
                }
                case "totaldeaths" -> {
                    setData(id, "totaldeaths", 0);
                    object = 0;
                }
                default -> {
                }
            }
        }
        return object;
    }

    public static Object getGlobalVar(String key) {
        var object = playerdata.get("global." + key);
        if(object == null) {
            switch (key) {
                case "totalfs" -> {
                    setGlobalVar("totalfs", 0);
                    object = 0;
                }
                case "totalpogs" -> {
                    setGlobalVar("totalpogs", 0);
                    object = 0;
                }
                case "totaldiamondsmined" -> {
                    setGlobalVar("totaldiamondsmined", 0);
                    object = 0;
                }
                case "totaldeaths" -> {
                    setGlobalVar("totaldeaths", 0);
                    object = 0;
                }
                default -> {
                    Bukkit.getConsoleSender().sendMessage("[Data] Default value for global variable " + key + " is null and is a known key with no default value.");
                }
            }
        }
        return object;
    }
    public static void setGlobalVar(String key, Object value) {
        playerdata.set("global."+key, value);
        try {
            playerdata.save(playersConfigFile);
            Bukkit.getConsoleSender().sendMessage("[Data] Set global."+key+" to: " + value.toString());
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[Data] An error occured while trying to save data");
            e.printStackTrace();
        }
    }

    public static void setData(UUID id, String key, Object value) {
        playerdata.set(id+"."+key, value);
        try {
            playerdata.save(playersConfigFile);
            Bukkit.getConsoleSender().sendMessage("[Data] Set " + id+"."+key+" to: " + value.toString());
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[Data] An error occured while trying to save data");
            e.printStackTrace();
        }
    }

    public void startScoreboardTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                int onlineCount = Bukkit.getOnlinePlayers().size();
                if(onlineCount == 0) return;
                Bukkit.getOnlinePlayers().forEach(player -> {
                    int ping = player.getPing();
                    int ramUsage = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
                    player.setPlayerListFooter(hex("#dc143cPing: " + ping + " #dc143cOnline: " + onlineCount));
                });
        }, 0L, 200L);
    }
    public static void announce(String string) {
        Bukkit.broadcastMessage(hex(string));
    }
    public static String hex(String string) {
        Matcher match = pattern.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start(), match.end());
            string = string.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(string);
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        if(e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            Player p = e.getPlayer();
            Bukkit.broadcastMessage(hex("&7"+p.getDisplayName()+" &7is now sleeping"));
            Main.logToChatChannel("[Sleep] " + p.getDisplayName() + " is now sleeping");
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        int playerDeaths = (int) getData(e.getEntity().getUniqueId(), "totaldeaths");
        setData(e.getEntity().getUniqueId(), "totaldeaths", playerDeaths + 1);
        int totalDeaths = (int) getGlobalVar("totaldeaths");
        totalDeaths++;
        setGlobalVar("totaldeaths", totalDeaths);
        final int finalTotalDeaths = totalDeaths;
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            Bukkit.broadcastMessage(hex("&fTotal Deaths: " + finalTotalDeaths));
        }, 1L);
    }

    public static void logToChatChannel(String message) {
        DiscordWebhook webhook = new DiscordWebhook("url");
        webhook.setContent(message);
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logToGameLogs(String message) {
        DiscordWebhook webhook = new DiscordWebhook("url");
        webhook.setContent(message);
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
