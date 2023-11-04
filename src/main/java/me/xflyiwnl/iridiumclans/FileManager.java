package me.xflyiwnl.iridiumclans;

import me.xflyiwnl.iridiumclans.config.YAML;
import me.xflyiwnl.iridiumclans.object.Settinger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private YAML language, settings, ranks;

    public FileManager() {
    }

    public void generate() {
        createFolders();

        settings = new YAML("settings.yml");
        ranks = new YAML("ranks.yml");

        generateLanguages();
        checkLanguage();
    }

    public void createFolders() {
        List<String> folders = Arrays.asList(
                "database",
                "database/players",
                "database/clans",
                "language"
        );
        folders.forEach(folder -> {
            File file = new File(IridiumClans.getInstance().getDataFolder(), folder);
            if (!file.exists()) {
                file.mkdirs();
            }
        });
    }

    public void generateLanguages() {
        List<String> allLanguages = Arrays.asList(
                "cz_CZ",
                "de_DE",
                "en_EN",
                "kz_KZ",
                "pl_PL",
                "ru_RU",
                "ua_UA"
        );
        allLanguages.forEach(key -> {
            new YAML("language/" + key + ".yml");
        });
    }

    public void checkLanguage() {
        File dataFolder = IridiumClans.getInstance().getDataFolder();
        File languageFile = new File(dataFolder, "language/" + Settinger.of("language") + ".yml");

        if (!languageFile.exists()) {
            String defaultLanguage = IridiumClans.getInstance().getDefaultLanguage();
            languageFile = new File(dataFolder, "language/" + defaultLanguage + ".yml");
            if (!languageFile.exists()) {
                return;
            }
            language = new YAML("language/" + defaultLanguage + ".yml");
            return;
        }

        language = new YAML(languageFile, YamlConfiguration.loadConfiguration(languageFile));
    }

    public YAML getLanguage() {
        return language;
    }

    public YAML getSettings() {
        return settings;
    }

    public YAML getRanks() {
        return ranks;
    }
}
