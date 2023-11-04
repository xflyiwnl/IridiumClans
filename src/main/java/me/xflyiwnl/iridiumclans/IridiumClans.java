package me.xflyiwnl.iridiumclans;

import me.xflyiwnl.iridiumclans.command.AdminCommand;
import me.xflyiwnl.iridiumclans.command.ClanCommand;
import me.xflyiwnl.iridiumclans.database.DatabaseSource;
import me.xflyiwnl.iridiumclans.database.FlatFileSource;
import me.xflyiwnl.iridiumclans.listener.PlayerListener;
import me.xflyiwnl.iridiumclans.object.Clan;
import me.xflyiwnl.iridiumclans.object.ClanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class IridiumClans extends JavaPlugin {

    private static IridiumClans instance;

    private final FileManager fileManager = new FileManager();
    private DatabaseSource databaseSource;

    private String defaultLanguage = "ru_RU";

    private Map<UUID, Clan> clans = new HashMap<UUID, Clan>();
    private Map<UUID, ClanPlayer> players = new HashMap<UUID, ClanPlayer>();

    @Override
    public void onEnable() {
        instance = this;
        fileManager.generate();

        if (fileManager.getLanguage() == null) {
            Bukkit.getLogger().info("Language file not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        registerCommands();
        registerListeners();

        checkDatabase();
        databaseSource.load();

        checkPlayers();
    }

    public void registerCommands() {
        getCommand("clans").setExecutor(new ClanCommand());
        getCommand("clans").setTabCompleter(new ClanCommand());

        getCommand("clansadmin").setExecutor(new AdminCommand());
        getCommand("clansadmin").setTabCompleter(new AdminCommand());
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
    }

    public void checkDatabase() {
        // todo database type flat or sql
        databaseSource = new FlatFileSource();
    }

    public void checkPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ClanPlayer clanPlayer = IridiumClans.getInstance().getPlayer(player.getUniqueId());

            if (clanPlayer != null) return;

            clanPlayer = new ClanPlayer(player.getUniqueId());
            clanPlayer.create(true);
        }
    }

    public Clan getClan(UUID uniqueId) {
        return clans.get(uniqueId);
    }

    public ClanPlayer getPlayer(UUID uniqueId) {
        return players.get(uniqueId);
    }

    public static IridiumClans getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public DatabaseSource getDatabaseSource() {
        return databaseSource;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public Map<UUID, Clan> getClans() {
        return clans;
    }

    public Map<UUID, ClanPlayer> getPlayers() {
        return players;
    }
}
