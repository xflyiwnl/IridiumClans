package me.xflyiwnl.iridiumclans.database.flat;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.object.Clan;
import me.xflyiwnl.iridiumclans.object.ClanPlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerFile implements FlatFile<ClanPlayer> {

    private File path = new File(IridiumClans.getInstance().getDataFolder(), "database/players");

    @Override
    public Map<UUID, ClanPlayer> all() {
        Map<UUID, ClanPlayer> players = new HashMap<UUID, ClanPlayer>();

        if (!path.exists()) return players;
        if (path.listFiles() == null) return players;

        for (File file : path.listFiles()) {
            if (file.isDirectory()) continue;
            ClanPlayer player = get(file);
            if (player == null) continue;
            players.put(player.getUniqueId(), player);
        }

        return players;
    }

    @Override
    public boolean exists(ClanPlayer player) {
        File file = new File(path.getPath(), player.getUniqueId().toString() + ".yml");
        return file.exists();
    }

    @Override
    public ClanPlayer get(File file) {
        if (!file.exists()) return null;

        Map<String, Object> serialized = new HashMap<String, Object>();
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for (String key : yaml.getKeys(false)) {
            serialized.put(key, yaml.get(key));
        }

        ClanPlayer clanPlayer = new ClanPlayer();

        if (serialized.containsKey("uniqueId"))
            clanPlayer.setUniqueId(UUID.fromString(serialized.get("uniqueId").toString()));

        if (serialized.containsKey("joinedDate"))
            clanPlayer.setJoinedDate(serialized.get("joinedDate").toString());

        if (serialized.containsKey("clan")) {
            Clan clan = IridiumClans.getInstance().getClan(UUID.fromString(serialized.get("clan").toString()));
            if (clan != null) {
                clanPlayer.setClan(clan);
                clan.addMember(clanPlayer);
            }
        }

        return clanPlayer;
    }

    @Override
    public ClanPlayer get(UUID uniqueId) {
        return get(new File(path.getPath(), uniqueId.toString() + ".yml"));
    }

    @Override
    public void save(ClanPlayer player) {
        if (!exists(player)) {
            create(player);
        }

        File file = new File(path.getPath(), player.getUniqueId().toString() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("uniqueId", player.getUniqueId().toString());
        if (player.getJoinedDate()!= null) yaml.set("joinedDate", player.getJoinedDate());
        if (player.hasClan()) yaml.set("clan", player.getClan().getUniqueId().toString());
        if (player.hasRank()) yaml.set("rank", player.getRank().getUniqueId().toString());

        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(ClanPlayer player) {
        File file = new File(path.getPath(), player.getUniqueId().toString() + ".yml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(ClanPlayer player) {
        if (!exists(player)) return;
        File file = new File(path.getPath(), player.getUniqueId().toString() + ".yml");
        file.delete();
    }

}
