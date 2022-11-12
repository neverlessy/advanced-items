package ru.moongl.minecraft.advanceditems.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AbstractInventory {

    HashMap<InventoryHolder, AbstractInventory> inventoryList = new HashMap<>();

    void open();

    Inventory getInventory();

    default void update(Entity player) {
        if(player instanceof Player) {
            Player p = (Player) player;
            p.updateInventory();
        }
    }

    default void addItem(String name, Material mat, int slot, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(name));

        meta.addItemFlags(
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ENCHANTS
        );

        List<Component> loreLines = new ArrayList<>();
        for (String loreLine : lore) {
            loreLines.add(Component.text(loreLine));
        }
        meta.lore(loreLines);

        item.setItemMeta(meta);

        getInventory().setItem(slot, item);
    }

    /**
     Легкое создание границ для предмета
     '-#######-'
     '#-------#'
     '-#######-'
     @param material Материал предмета для границ
     @param borders Массив строк в виде паттерна
     */
    default void setBorders(Material material, String... borders) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ENCHANTS
        );
        meta.displayName(Component.text(""));
        item.setItemMeta(meta);

        int countChars = 0;
        int countLines = 0;
        for (String borderline : borders) {
            for(char borderElement : borderline.toCharArray()) {
                if(borderElement == '#') {
                    getInventory().setItem(countLines + countChars, item);
                }
                countChars++;
            }
            if(getInventory().getSize() < countLines) {
                countLines += 9;
            }
        }
    }

    default void open(Player player) {
        player.openInventory(getInventory());

        if(!inventoryList.containsKey(getInventory().getHolder())) {
            inventoryList.put(getInventory().getHolder(), this);
        }
    }

    void handle(InventoryClickEvent e);
}
