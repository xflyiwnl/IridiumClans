package me.xflyiwnl.iridiumclans.object;

import me.xflyiwnl.iridiumclans.IridiumClans;

import java.util.List;

public class Settinger {

    public static String of(String path) {
        return IridiumClans.getInstance().getFileManager().getSettings().yaml().getString("settings." + path);
    }

    public static List<String> ofList(String path) {
        return IridiumClans.getInstance().getFileManager().getSettings().yaml().getStringList("settings." + path);
    }

    public static int ofInt(String path) {
        return IridiumClans.getInstance().getFileManager().getSettings().yaml().getInt("settings." + path);
    }

    public static double ofDouble(String path) {
        return IridiumClans.getInstance().getFileManager().getSettings().yaml().getDouble("settings." + path);
    }

    public static boolean ofBoolean(String path) {
        return IridiumClans.getInstance().getFileManager().getSettings().yaml().getBoolean("settings." + path);
    }

}
