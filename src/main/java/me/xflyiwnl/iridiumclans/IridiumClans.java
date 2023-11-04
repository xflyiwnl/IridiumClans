package me.xflyiwnl.iridiumclans;

import org.bukkit.plugin.java.JavaPlugin;

public final class IridiumClans extends JavaPlugin {

    private static IridiumClans instance;

    @Override
    public void onEnable() {
        instance = this;



    }

    public static IridiumClans getInstance() {
        return instance;
    }

}
