package me.xflyiwnl.iridiumclans.object;

import java.util.List;

public interface RankHandler {

    List<PermissionNode> getNodes();

    boolean containsNode(PermissionNode node);
    void addNode(PermissionNode node);
    void removeNode(PermissionNode node);

}
