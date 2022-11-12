package ru.moongl.minecraft.advanceditems.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.potion.PotionEffectType;
import ru.moongl.minecraft.advanceditems.menus.AbstractInventory;

import java.util.Optional;

@SuppressWarnings("unused")
public class CustomInventoryListening implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        if (inventory == null) return;
        getInventory(inventory.getHolder()).ifPresent(abstractInventory -> {
            e.setCancelled(true);
            abstractInventory.handle(e);
        });
    }

    @EventHandler
    public void on(InventoryDragEvent e) {
        Inventory inventory = e.getInventory();
        getInventory(inventory.getHolder()).ifPresent(abstractInventory -> e.setCancelled(true));
    }

    @EventHandler
    public void on(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        getInventory(holder).ifPresent(abstractInventory -> AbstractInventory.inventoryList.remove(holder));
    }

    private Optional<AbstractInventory> getInventory(InventoryHolder holder) {
        return Optional.ofNullable(AbstractInventory.inventoryList.get(holder));
    }

}