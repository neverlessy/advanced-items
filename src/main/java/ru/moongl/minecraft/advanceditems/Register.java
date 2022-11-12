package ru.moongl.minecraft.advanceditems;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class Register {

    public static void registerEvents(Listener... listeners) {
        for(Listener event : listeners) {
            AdvancedItems.getInstance().getServer().getPluginManager().registerEvents(event, AdvancedItems.getInstance());
        }
    }

    public static void registerCommands(HashMap<HashMap, HashMap> hashMapCommands) {
        for(Map.Entry<HashMap, HashMap> entry : hashMapCommands.entrySet()) {
            HashMap<String, CommandExecutor> executor = entry.getKey();
            HashMap<String, TabCompleter> completer = entry.getValue();

            executor.forEach((key, value) -> {
                AdvancedItems.getInstance().getCommand(key).setExecutor(value);
            });
            completer.forEach((key, value) -> {
                AdvancedItems.getInstance().getCommand(key).setTabCompleter(value);
            });
        }

    }
}
