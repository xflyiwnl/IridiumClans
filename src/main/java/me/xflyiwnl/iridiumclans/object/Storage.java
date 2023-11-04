package me.xflyiwnl.iridiumclans.object;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private Inventory inventory;
    private Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();

    public Storage() {
    }

    public Storage(Map<Integer, ItemStack> items) {
        this.items = items;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public void setItems(Map<Integer, ItemStack> items) {
        this.items = items;
    }
}
