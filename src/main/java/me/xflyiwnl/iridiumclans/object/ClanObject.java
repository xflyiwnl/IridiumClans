package me.xflyiwnl.iridiumclans.object;

import java.util.UUID;

public abstract class ClanObject implements Identifyable, Nameable {

    private UUID uniqueId = UUID.randomUUID();
    private String name;

    public ClanObject() {
    }

    public ClanObject(String name) {
        this.name = name;
    }

    public ClanObject(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
