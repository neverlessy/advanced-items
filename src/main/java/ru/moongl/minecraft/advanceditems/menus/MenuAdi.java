package ru.moongl.minecraft.advanceditems.menus;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuAdi extends BorderInventory {

    public MenuAdi(String name, int rows) {
        super(name, rows);

        setBorders(Material.BLACK_STAINED_GLASS_PANE,
                "-#######-",
                "#-------#",
                "-#######-"
        );

        addElements();
    }

    @Override
    public void open() {

    }

    // §

    public void addElements() {
        addItem("§", Material.NETHERITE_SWORD, 10, "test");
    }

    @Override
    public void handle(InventoryClickEvent e) {
        if (e.getRawSlot() == 1) {
            addItem("churka", Material.CLOCK, 0, "LORE", "LORE2", "LORE3", "LORE4");
            e.getWhoClicked().sendMessage("you churka?");
            update(e.getWhoClicked());
        }
    }
}
