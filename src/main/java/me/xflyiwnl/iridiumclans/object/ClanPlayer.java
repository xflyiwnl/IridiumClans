package me.xflyiwnl.iridiumclans.object;

import org.bukkit.Bukkit;

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
