package me.xflyiwnl.iridiumclans.object;

import me.xflyiwnl.iridiumclans.IridiumClans;

import java.util.List;

public class Translator {

    public static String of(String path) {
        return IridiumClans.getInstance().getFileManager().getLanguage().yaml().getString("language." + path);
    }

    public static List<String> ofList(String path) {
        return IridiumClans.getInstance().getFileManager().getLanguage().yaml().getStringList("language." + path);
    }

}
