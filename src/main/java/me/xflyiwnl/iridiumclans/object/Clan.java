package me.xflyiwnl.iridiumclans.object;

import me.xflyiwnl.iridiumclans.object.bank.Bank;
import me.xflyiwnl.iridiumclans.object.rank.Rank;
import me.xflyiwnl.iridiumclans.object.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clan extends ClanObject implements MemberList {

    private Bank bank = new Bank();
    private Storage storage = new Storage();
    private String createdDate;

    private List<ClanPlayer> members = new ArrayList<ClanPlayer>();
    private List<Rank> ranks = new ArrayList<Rank>();

    public Clan() {
    }

    public Clan(UUID uniqueId, String name, String createdDate) {
        super(uniqueId, name);
        this.createdDate = createdDate;
    }

    public Clan(UUID uniqueId, String name, Bank bank, Storage storage, String createdDate) {
        super(uniqueId, name);
        this.bank = bank;
        this.storage = storage;
        this.createdDate = createdDate;
    }

    public Clan(UUID uniqueId, String name, Bank bank, Storage storage, String createdDate, List<ClanPlayer> members, List<Rank> ranks) {
        super(uniqueId, name);
        this.bank = bank;
        this.storage = storage;
        this.createdDate = createdDate;
        this.members = members;
        this.ranks = ranks;
    }

    public void create(boolean save) {
        if (save) save();
    }

    public void save() {

    }

    public void remove() {

    }

    public void broadcast(String message) {

    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public List<ClanPlayer> getMembers() {
        return members;
    }

    public void setMembers(List<ClanPlayer> members) {
        this.members = members;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }
}
