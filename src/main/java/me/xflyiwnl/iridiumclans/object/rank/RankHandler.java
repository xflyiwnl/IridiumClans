package me.xflyiwnl.iridiumclans.object.rank;

import java.util.List;

public interface RankHandler {

    List<PermissionNode> getNodes();

    boolean containsNode(PermissionNode node);
    void addNode(PermissionNode node);
    void removeNode(PermissionNode node);

}
