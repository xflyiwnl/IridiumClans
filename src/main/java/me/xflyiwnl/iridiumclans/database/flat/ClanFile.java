package me.xflyiwnl.iridiumclans.database.flat;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.object.Clan;
import me.xflyiwnl.iridiumclans.object.ClanPlayer;
import me.xflyiwnl.iridiumclans.object.bank.Bank;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClanFile implements FlatFile<Clan> {

    private File path = new File(IridiumClans.getInstance().getDataFolder(), "database/clans");

    @Override
    public Map<UUID, Clan> all() {
        Map<UUID, Clan> clans = new HashMap<UUID, Clan>();

        if (!path.exists()) return clans;
        if (path.listFiles() == null) return clans;

        for (File file : path.listFiles()) {
            if (file.isDirectory()) continue;
            Clan clan = get(file);
            if (clan == null) continue;
            clans.put(clan.getUniqueId(), clan);
        }

        return clans;
    }

    @Override
    public boolean exists(Clan clan) {
        File file = new File(path.getPath(), clan.getUniqueId().toString() + ".yml");
        return file.exists();
    }

    @Override
    public Clan get(File file) {

        if (!file.exists()) return null;

        Map<String, Object> serialized = new HashMap<String, Object>();
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for (String key : yaml.getKeys(false)) {
            serialized.put(key, yaml.get(key));
        }

        Clan clan = new Clan();

        if (serialized.containsKey("uniqueId"))
            clan.setUniqueId(UUID.fromString(serialized.get("uniqueId").toString()));

        if (serialized.containsKey("createdDate"))
            clan.setCreatedDate(serialized.get("createdDate").toString());

        if (serialized.containsKey("name"))
            clan.setName(serialized.get("name").toString());

        if (serialized.containsKey("bank"))
            clan.setBank(new Bank(Double.valueOf(serialized.get("bank").toString())));

        if (serialized.containsKey("lore"))
            clan.setLore(serialized.get("lore").toString());

        if (serialized.containsKey("flag"))
            clan.setFlag((ItemStack) serialized.get("flag"));

        if (serialized.containsKey("home"))
            clan.setHome((Location) serialized.get("home"));

        return clan;
    }

    @Override
    public Clan get(UUID uniqueId) {
        return get(new File(path.getPath(), uniqueId.toString() + ".yml"));
    }

    @Override
    public void save(Clan clan) {
        if (!exists(clan)) {
            create(clan);
        }

        File file = new File(path.getPath(), clan.getUniqueId().toString() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("uniqueId", clan.getUniqueId().toString());
        yaml.set("name", clan.getName());
        yaml.set("leader", clan.getLeader().getUniqueId().toString());
        if (clan.getLore() != null) yaml.set("lore", clan.getLore());
        if (clan.getFlag() != null) yaml.set("flag", clan.getFlag());
        if (clan.getHome() != null) yaml.set("home", clan.getHome());
        yaml.set("bank", clan.getBank().get());
        yaml.set("createdDate", clan.getCreatedDate());

        List<String> formattedMembers = new ArrayList<String>();
        clan.getMembers().forEach(player -> {
            formattedMembers.add(player.getUniqueId().toString());
        });
        yaml.set("members", formattedMembers);

        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Clan clan) {
        File file = new File(path.getPath(), clan.getUniqueId().toString() + ".yml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Clan clan) {
        if (!exists(clan)) return;
        File file = new File(path.getPath(), clan.getUniqueId().toString() + ".yml");
        file.delete();
    }

}
