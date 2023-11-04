package me.xflyiwnl.iridiumclans.config;

import me.xflyiwnl.iridiumclans.IridiumClans;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YAML {

    private File file;
    private FileConfiguration yaml;

    public YAML(String name) {
        init(name);
    }

    public YAML(File file, FileConfiguration yaml) {
        this.file = file;
        this.yaml = yaml;
    }

    public void init(String name) {

        file = new File(IridiumClans.getInstance().getDataFolder(), name);
        if (!file.exists()) {
            IridiumClans.getInstance().saveResource(name, true);
        }
        yaml = YamlConfiguration.loadConfiguration(file);

    }

    public void save() {
        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File file() {
        return file;
    }

    public FileConfiguration yaml() {
        return yaml;
    }

}
