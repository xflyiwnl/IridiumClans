package me.xflyiwnl.iridiumclans.object;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.database.DatabaseSource;
import me.xflyiwnl.iridiumclans.database.FlatFileSource;
import me.xflyiwnl.iridiumclans.database.flat.FlatFile;
import me.xflyiwnl.iridiumclans.object.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClanPlayer extends ClanObject {

    private Clan clan;
    private Rank rank;
    private String joinedDate;

    public ClanPlayer() {
    }

    public ClanPlayer(UUID uniqueId) {
        super(uniqueId, "");
    }

    public ClanPlayer(UUID uniqueId, Clan clan, Rank rank, String joinedDate) {
        super(uniqueId, "");
        this.clan = clan;
        this.rank = rank;
        this.joinedDate = joinedDate;
    }

    public void create(boolean save) {
        IridiumClans.getInstance().getPlayers().put(getUniqueId(), this);
        if (save) save();
    }

    public void save() {
        DatabaseSource source = IridiumClans.getInstance().getDatabaseSource();
        if (source instanceof FlatFileSource) {
            FlatFile<ClanPlayer> flatFile = ((FlatFileSource) source).getPlayerFile();
            flatFile.save(this);
        }
    }

    public void remove() {
        IridiumClans.getInstance().getPlayers().remove(getUniqueId());
        DatabaseSource source = IridiumClans.getInstance().getDatabaseSource();
        if (source instanceof FlatFileSource) {
            FlatFile<ClanPlayer> flatFile = ((FlatFileSource) source).getPlayerFile();
            flatFile.remove(this);
        }
    }

    public boolean isOnline() {
        return getPlayer() == null ? false : getPlayer().isOnline();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getUniqueId());
    }

    public boolean hasClan() {
        return clan != null ? true : false;
    }

    public boolean hasRank() {
        return rank != null ? true : false;
    }

    @Override
    public String getName() {
        return Bukkit.getOfflinePlayer(getUniqueId()).getName();
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }
}
