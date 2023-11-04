package me.xflyiwnl.iridiumclans.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rank extends ClanObject implements RankHandler {

    private List<PermissionNode> nodes = new ArrayList<PermissionNode>();

    public Rank() {
    }

    public Rank(UUID uniqueId, String name) {
        super(uniqueId, name);
    }

    public Rank(UUID uniqueId, String name, List<PermissionNode> nodes) {
        super(uniqueId, name);
        this.nodes = nodes;
    }

    public boolean containsNode(PermissionNode node) {
        return getNodes().contains(node);
    }
    public void addNode(PermissionNode node) {
        if (!containsNode(node)) {
            getNodes().add(node);
        }
    }
    public void removeNode(PermissionNode node) {
        getNodes().remove(node);
    }

    @Override
    public List<PermissionNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<PermissionNode> nodes) {
        this.nodes = nodes;
    }
}
