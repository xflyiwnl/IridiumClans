package me.xflyiwnl.iridiumclans.database.flat;

import me.xflyiwnl.iridiumclans.object.ClanObject;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FlatFile<T extends ClanObject> {

    Map<UUID, T> all();
    boolean exists(T t);

    T get(File file);
    T get(UUID uniqueId);

    void save(T t);
    void create(T t);
    void remove(T t);

}
