package ru.moongl.minecraft.advanceditems.utils;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;

public enum ItemRarity {
    COMMON("Обычный", NamedTextColor.GRAY),
    UNCOMMON("Необычный", NamedTextColor.GREEN),
    RARE("Редкий", NamedTextColor.BLUE),
    EPIC("Эпический", NamedTextColor.LIGHT_PURPLE),
    LEGENDARY("Легендарный", NamedTextColor.GOLD),
    STELLAR("Звездный", NamedTextColor.AQUA);

    private final String rarityName;
    private final NamedTextColor color;

    ItemRarity(String rarityName, NamedTextColor color) {
        this.rarityName = rarityName;
        this.color = color;
    }

    public String getRarityName() {
        return rarityName;
    }

    public NamedTextColor getColor() {
        return color;
    }
}
