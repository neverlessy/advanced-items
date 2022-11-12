package ru.moongl.minecraft.advanceditems.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private StringBuilder damageStars = new StringBuilder("☆☆☆☆☆ ");

    private StringBuilder attackSpeedStars = new StringBuilder("☆☆☆☆☆ ");

    private StringBuilder criteChanseStars = new StringBuilder("☆☆☆☆☆ ");

    private StringBuilder rarityStars = new StringBuilder("☆☆☆☆☆ ");

    private List<Component> lore = new ArrayList<Component>();

    private static final char STAR_FILL = '★';

    public ItemRarity rarity;

    public ItemBuilder(ItemRarity rarity) {
        this.rarity = rarity;
    }


    public List<Component> createLoreLines(double damage, double attackSpeed, double criteChanse) {
        setStarsInLore(damage, attackSpeed, criteChanse, rarity);
        Component space = Component.text().content(" ").build();

        Component damageComponent = Component.text()
                .content(" Урон: ")
                .color(TextColor.color(NamedTextColor.GRAY))
                .append(
                        Component.text()
                                .content(damageStars + " §7(§e" + damage + " ед§7)")
                                .color(TextColor.color(NamedTextColor.RED))
                                .build()
                )
                .build();

        Component attackSpeedComponent = Component.text()
                .content(" Скорость атаки: ")
                .color(TextColor.color(NamedTextColor.GRAY))
                .append(
                        Component.text()
                                .content(attackSpeedStars + " §7(§e" + attackSpeed + " у/с§7)")
                                .color(TextColor.color(NamedTextColor.RED))
                                .build())
                .build();

        Component criteChanseComponent = Component.text()
                .content(" Шанс крита: ")
                .color(TextColor.color(NamedTextColor.GRAY))
                .append(
                        Component.text()
                                .content(criteChanseStars + " §7(§e" + criteChanse + " %§7)")
                                .color(TextColor.color(NamedTextColor.RED))
                                .build())
                .build();

        Component rarityComponent = Component.text()
                .content(" Редкость: ")
                .color(TextColor.color(NamedTextColor.GRAY))
                .append(
                        Component.text()
                                .content(rarityStars + " §7(")
                                .color(TextColor.color(NamedTextColor.RED))
                                .build())
                .append(
                        Component.text()
                                .content(rarity.getRarityName() + "§7)")
                                .color(TextColor.color(rarity.getColor()))
                                .build()
                )
                .build();

        lore.add(space);
        lore.add(damageComponent);
        lore.add(attackSpeedComponent);
        lore.add(criteChanseComponent);
        lore.add(rarityComponent);
        lore.add(space);

        return lore;
    }

    public void setStarsInLore(double damage, double attackSpeed, double criteChanse, ItemRarity rarity) {
        for( double i = 0.0; i <= damage / 4; i+=1.0) {
            damageStars.setCharAt((int) i, STAR_FILL);
        }

        for( double i = 0.0; i <= attackSpeed / 2; i+=1.0) {
            attackSpeedStars.setCharAt((int) i, STAR_FILL);
        }

        for( double i = 0.0; i <= criteChanse / 10; i+=1.0) {
            criteChanseStars.setCharAt((int) i, STAR_FILL);
        }
        switch (rarity) {
            case COMMON:
            case UNCOMMON:
            case RARE:
                rarityStars = new StringBuilder("★☆☆☆☆ ");
                break;
            case EPIC:
                rarityStars = new StringBuilder("★★☆☆☆ ");
                break;
            case LEGENDARY:
                rarityStars = new StringBuilder("★★★☆☆ ");
                break;
            case STELLAR:
                rarityStars = new StringBuilder("★★★★★ ");
                break;
            default:
                rarityStars = new StringBuilder("☆☆☆☆☆ ");
                break;
        }
    }

    public String centeredText(String t, int size) {
        String space = " ";
        String finalSting = "";
        int preSize = t.length();
        if (preSize%2==0) {
            for (int i = 0 ; i < size + 1; i++) {
                if (i == size / 2) {
                    finalSting += t;
                } else {
                    finalSting += space;
                }
            }
        } else {
            for (int i = 0 ; i < (size / 2) - (t.length() - 1 / 2); i++) {
                if (i == size / 2) {
                    finalSting += t;
                } else {
                    finalSting += space;
                }
            }
        }
        return finalSting;
    }

    public ItemRarity getRarityName() {
        return rarity;
    }
}
