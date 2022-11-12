package ru.moongl.minecraft.advanceditems.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.moongl.minecraft.advanceditems.AdvancedItems;
import ru.moongl.minecraft.advanceditems.chat.MessageTypes;
import ru.moongl.minecraft.advanceditems.items.ItemCustomSword;
import ru.moongl.minecraft.advanceditems.menus.AbstractInventory;
import ru.moongl.minecraft.advanceditems.menus.MenuAdi;
import ru.moongl.minecraft.advanceditems.utils.ItemRarity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.sun.tools.attach.VirtualMachine.list;

public class CommandAdi implements CommandExecutor {

    private final ArrayList<Material> armorList = new ArrayList<>(Arrays.asList(
            Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET,
            Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET,
            Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET,
            Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET,
            Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET,
            Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET
    ));

    private final ArrayList<Material> swordList = new ArrayList<>(Arrays.asList(
            Material.WOODEN_SWORD,Material.STONE_SWORD,Material.IRON_SWORD, Material.GOLDEN_SWORD,Material.DIAMOND_SWORD,Material.NETHERITE_SWORD
    ));

    private final ArrayList<Material> pickaxeList = new ArrayList<>(Arrays.asList(
            Material.WOODEN_PICKAXE,Material.STONE_PICKAXE,Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE,Material.DIAMOND_PICKAXE,Material.NETHERITE_PICKAXE
    ));

    private final ArrayList<Material> axeList = new ArrayList<>(Arrays.asList(
            Material.WOODEN_AXE,Material.STONE_AXE,Material.IRON_AXE, Material.GOLDEN_AXE,Material.DIAMOND_AXE,Material.NETHERITE_AXE
    ));

    private final ArrayList<Material> hoeList = new ArrayList<>(Arrays.asList(
            Material.WOODEN_HOE,Material.STONE_HOE,Material.IRON_HOE, Material.GOLDEN_HOE,Material.DIAMOND_HOE,Material.NETHERITE_HOE
    ));

    private final ArrayList<Material> shovelList = new ArrayList<>(Arrays.asList(
            Material.WOODEN_SHOVEL,Material.STONE_SHOVEL,Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL,Material.DIAMOND_SHOVEL,Material.NETHERITE_SHOVEL
    ));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("advitems.command.adi")) {
            try {
                executeCommand(args, player);
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage(MessageTypes.ERROR.getPrefix() + "Произошла ошибка при выполнении команды");
            }
        } else {
            player.sendMessage(MessageTypes.ERROR.getPrefix() + "У вас недостаточно прав");
        }
        return true;
    }

    public void executeCommand(String[] arguments, Player player) {
        AbstractInventory menu = new MenuAdi("inv", 3);
        try {
            switch (arguments[0]) {
                case "menu":
                    menu.open(player);
                    break;
                case "create":
                    if (arguments.length < 3) {
                        player.sendMessage(MessageTypes.WARN.getPrefix() + "Чтобы создать предмет, введите его имя и тип");
                        break;
                    }
                    if (arguments[1] != null && arguments[2] != null) {
                        checkTypeItem(player, arguments[1], arguments[2]);
                    } else {
                        player.sendMessage(MessageTypes.WARN.getPrefix() + "Чтобы создать предмет, введите его имя и тип");
                    }
                    break;
                case "delete":
                    if (arguments.length < 2) {
                        player.sendMessage(MessageTypes.WARN.getPrefix() + "Введите имя удаляемого предмета");
                        break;
                    }
                    if (arguments[1] != null) {
                        player.sendMessage(MessageTypes.SUCCESS.getPrefix() + "Вы успешно удалили §6" + arguments[1]);
                    } else {
                        player.sendMessage(MessageTypes.WARN.getPrefix() + "Введите имя удаляемого предмета");
                    }
                    break;
                default:
                    player.sendMessage(MessageTypes.ERROR.getPrefix() + "Вы ввели недопустимый аргумент");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            player.sendMessage(MessageTypes.INFO.getPrefix() + "Попробуйте §c/adi §7<§6menu§7/§6create§7/§6delete§7> §7<§6тип§7>");
        }
    }

    private void checkTypeItem(Player player, String itemType, String name) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if(itemInHand.getType() != Material.AIR) {
            switch (itemType) {
                case "sword":
                case "armor":
                case "pickaxe":
                case "axe":
                case "hoe":
                case "shovel":
                    if(getSwordList().contains(itemInHand.getType()) ||getPickaxeList().contains(itemInHand.getType()) || getArmorList().contains(itemInHand.getType()) || getPickaxeList().contains(itemInHand.getType()) || getAxeList().contains(itemInHand.getType()) || getHoeList().contains(itemInHand.getType()) || getShovelList().contains(itemInHand.getType())) {
                        createItem(itemInHand, player, itemType, name);
                    } else {
                        player.sendMessage(MessageTypes.ERROR.getPrefix() + "У вас в руках должен быть предмет типа " + ChatColor.DARK_RED + itemType);
                    }
                    break;
                default:
                    if(itemType.equals("bow") || itemType.equals("shield")) {
                        createItem(itemInHand, player, itemType, name);
                    } else {
                        player.sendMessage(MessageTypes.WARN.getPrefix() + "Неизвестный тип предмета");
                    }
                    break;
            }
        } else {
            player.sendMessage(MessageTypes.ERROR.getPrefix() + "В вашей руке должен находится предмет");
        }
    }

    private void createItem(ItemStack itemInHand, Player player, String type, String name) {
        switch (type) {
            case "sword":
                ItemStack sword = new ItemCustomSword(itemInHand.getType());
                itemInHand.setAmount(0);
                player.getInventory().setItemInMainHand(sword);
                player.sendMessage(MessageTypes.SUCCESS.getPrefix() + "Вы успешно создали предмет с именем " + ChatColor.GOLD + name);
                break;
            case "armor":
                break;
        }

        File file = new File(AdvancedItems.getInstance().getDataFolder() + "/items/" + type + "/" + name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable localThrowable) {}
            configSetDefaultLines(config, type, player);
            try {
                config.save(file);
            } catch (Throwable localThrowable1) {}
        }
    }

    private void deleteItem() {

    }

    private int getItemId() {
        List<Path> dirs = null;
        int FIRST_ITEM = 0;

        try {
            dirs = Files.walk(Paths.get(AdvancedItems.getInstance().getDataFolder() + "/items/"), 4096)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            return dirs.size();
        } catch (NullPointerException | IOException e) {
            return FIRST_ITEM;
        }
    }

    public void configSetDefaultLines(FileConfiguration config, String type, Player player) {
        config.set("item.id", getItemId());
        config.set("item.createtime", new Date().toString());

        config.set("item.info.rarity", ItemRarity.COMMON.getRarityName());
        config.set("item.info.material", player.getInventory().getItemInMainHand().getType().toString());

        config.set("item.author.name", player.getName());
        config.set("item.author.uuid", player.getUniqueId().toString());

    }

    public ArrayList<Material> getArmorList() {
        return armorList;
    }

    public ArrayList<Material> getSwordList() {
        return swordList;
    }

    public ArrayList<Material> getPickaxeList() {
        return pickaxeList;
    }

    public ArrayList<Material> getAxeList() {
        return axeList;
    }

    public ArrayList<Material> getHoeList() {
        return hoeList;
    }

    public ArrayList<Material> getShovelList() {
        return shovelList;
    }
}
