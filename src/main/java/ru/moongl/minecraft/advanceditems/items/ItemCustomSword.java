package ru.moongl.minecraft.advanceditems.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.moongl.minecraft.advanceditems.AdvancedItems;
import ru.moongl.minecraft.advanceditems.utils.ItemBuilder;
import ru.moongl.minecraft.advanceditems.utils.ItemRarity;

public class ItemCustomSword extends ItemStack {

    public ItemCustomSword(Material mat) {
        super(mat);

        ItemBuilder ib = new ItemBuilder(ItemRarity.COMMON);
        ItemMeta meta = this.getItemMeta();

        meta.lore(ib.createLoreLines(4.0, 1.0, 10));

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_DYE, ItemFlag.HIDE_UNBREAKABLE);
        NamespacedKey customItem = new NamespacedKey(AdvancedItems.getInstance(), "customItem");
        meta.getPersistentDataContainer().set(customItem, PersistentDataType.STRING, "toUse");
        this.setItemMeta(meta);
    }
}
