package ru.moongl.minecraft.advanceditems.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        ArrayList<Inventory> inventories = new ArrayList<>(List.of(event.getInventory()));

        Player player = (Player) event.getPlayer();

        setCustomData(player, inventories);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ArrayList<Inventory> inventories = new ArrayList<>(List.of(event.getPlayer().getInventory()));
        Player player = event.getPlayer();

        setCustomData(player, inventories);
    }

    public void setCustomData(Player player, ArrayList<Inventory> inv) {
        InventoryView view = player.getOpenInventory();
        inv.add(view.getBottomInventory());

        for(Inventory inventory : inv) {
            for (ItemStack item : inventory.getContents()) {
                try {
                    Set<NamespacedKey> container = item.getItemMeta().getPersistentDataContainer().getKeys();
                    container.forEach((key) -> {
                        if (key.getKey().equals("customitem")) {
                            // Заглушка, key находит.
                        }
                    });
                } catch (NullPointerException ignored) {}
            }
        }
    }
}
