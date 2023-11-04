package me.xflyiwnl.iridiumclans.object.rank;

import me.xflyiwnl.iridiumclans.object.ClanObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rank extends ClanObject implements RankHandler {

    private int weight = 0;
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

    @Override
    public boolean containsNode(PermissionNode node) {
        return getNodes().contains(node);
    }

    @Override
    public void addNode(PermissionNode node) {
        if (!containsNode(node)) {
            getNodes().add(node);
        }
    }

    @Override
    public void removeNode(PermissionNode node) {
        getNodes().remove(node);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public List<PermissionNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<PermissionNode> nodes) {
        this.nodes = nodes;
    }
}
