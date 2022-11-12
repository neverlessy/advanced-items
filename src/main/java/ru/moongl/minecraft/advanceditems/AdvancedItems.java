package ru.moongl.minecraft.advanceditems;

import org.bukkit.plugin.java.JavaPlugin;
import ru.moongl.minecraft.advanceditems.commands.Commands;
import ru.moongl.minecraft.advanceditems.listeners.CustomInventoryListening;
import ru.moongl.minecraft.advanceditems.listeners.PlayerListener;

public final class AdvancedItems extends JavaPlugin {
    private static AdvancedItems instance;

    @Override
    public void onEnable() {
        instance = this;
        Register.registerEvents(
                new CustomInventoryListening(),
                new PlayerListener()
        );
        Register.registerCommands(Commands.getCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AdvancedItems getInstance() {
        return instance;
    }

}
