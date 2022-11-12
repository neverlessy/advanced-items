package ru.moongl.minecraft.advanceditems.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class BorderInventory implements AbstractInventory {
    String name;
    int rows;

    Inventory inventory;

    public BorderInventory(String name, int rows) {
        this.name = name;
        this.rows = rows;
        createInventory();
    }

    @Override
    public void open(Player player) {
        if (this.inventory == null) {
            inventoryList.put(getInventory().getHolder(), this);
        }
        player.openInventory(getInventory());
        if (!inventoryList.containsKey(getInventory().getHolder())) {
            inventoryList.put(getInventory().getHolder(), this);
        }
    }


    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    private void createInventory() {
        InventoryHolder holder = () -> this.inventory;
        this.inventory = Bukkit.createInventory(holder, this.rows * 9, Component.text(this.name));
    }
}
