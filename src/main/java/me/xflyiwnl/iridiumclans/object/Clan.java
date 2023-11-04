package me.xflyiwnl.iridiumclans.object;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.database.DatabaseSource;
import me.xflyiwnl.iridiumclans.database.FlatFileSource;
import me.xflyiwnl.iridiumclans.database.flat.FlatFile;
import me.xflyiwnl.iridiumclans.object.bank.Bank;
import me.xflyiwnl.iridiumclans.object.rank.Rank;
import me.xflyiwnl.iridiumclans.object.storage.Storage;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clan extends ClanObject implements MemberList {

    private ClanPlayer leader;
    private String lore;
    private ItemStack flag;
    private Location home;

    private Bank bank = new Bank();
    private Storage storage = new Storage();
    private String createdDate;

    private List<ClanPlayer> members = new ArrayList<ClanPlayer>();
    private List<Rank> ranks = new ArrayList<Rank>();

    public Clan() {
    }

    public Clan(UUID uniqueId, String name, ClanPlayer leader, String createdDate) {
        super(uniqueId, name);
        this.leader = leader;
        this.createdDate = createdDate;
    }

    public Clan(UUID uniqueId, String name, ClanPlayer leader,
                Bank bank, Storage storage, String createdDate) {
        super(uniqueId, name);
        this.leader = leader;
        this.bank = bank;
        this.storage = storage;
        this.createdDate = createdDate;
    }

    public Clan(UUID uniqueId, String name, ClanPlayer leader,
                String lore, ItemStack flag, Location home,
                Bank bank, Storage storage, String createdDate,
                List<ClanPlayer> members, List<Rank> ranks) {
        super(uniqueId, name);
        this.leader = leader;
        this.lore = lore;
        this.flag = flag;
        this.home = home;
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
        IridiumClans.getInstance().getClans().remove(getUniqueId());
        DatabaseSource source = IridiumClans.getInstance().getDatabaseSource();
        if (source instanceof FlatFileSource) {
            FlatFile<Clan> flatFile = ((FlatFileSource) source).getClanFile();
            flatFile.save(this);
        }

    }

    public void remove() {
        IridiumClans.getInstance().getClans().remove(getUniqueId());
        DatabaseSource source = IridiumClans.getInstance().getDatabaseSource();
        if (source instanceof FlatFileSource) {
            FlatFile<Clan> flatFile = ((FlatFileSource) source).getClanFile();
            flatFile.remove(this);
        }
    }

    public void broadcast(String message) {
        for (ClanPlayer member : members) {
            if (!member.isOnline()) continue;
            member.getPlayer().sendMessage(message);
        }
    }

    public ClanPlayer getLeader() {
        return leader;
    }

    public void setLeader(ClanPlayer leader) {
        this.leader = leader;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public ItemStack getFlag() {
        return flag;
    }

    public void setFlag(ItemStack flag) {
        this.flag = flag;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
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
