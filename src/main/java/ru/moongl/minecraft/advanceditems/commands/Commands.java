package ru.moongl.minecraft.advanceditems.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

import java.util.HashMap;

public class Commands {
    public static HashMap<HashMap, HashMap> getCommands() {
        HashMap<String, CommandExecutor> hashMap = new HashMap<>();
        HashMap<String, TabCompleter> completerHashMap = new HashMap<>();
        HashMap<HashMap, HashMap> exit = new HashMap<>();

        for (CommandEnum value : CommandEnum.values()) {
            hashMap.put(value.getName(), value.getCommand());
            completerHashMap.put(value.getName(), value.getCompleter());
        }
        exit.put(hashMap, completerHashMap);

        return exit;
    }
}
