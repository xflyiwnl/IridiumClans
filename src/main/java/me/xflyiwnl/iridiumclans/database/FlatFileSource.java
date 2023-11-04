package me.xflyiwnl.iridiumclans.database;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.database.flat.ClanFile;
import me.xflyiwnl.iridiumclans.database.flat.PlayerFile;

public class FlatFileSource implements DatabaseSource {

    private final ClanFile clanFile = new ClanFile();
    private final PlayerFile playerFile = new PlayerFile();

    @Override
    public void load() {
        IridiumClans.getInstance().getClans().putAll(clanFile.all());
        IridiumClans.getInstance().getPlayers().putAll(playerFile.all());
    }

    @Override
    public void unload() {
    }

    public ClanFile getClanFile() {
        return clanFile;
    }

    public PlayerFile getPlayerFile() {
        return playerFile;
    }
}
