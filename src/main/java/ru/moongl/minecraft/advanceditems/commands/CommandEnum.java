package ru.moongl.minecraft.advanceditems.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import ru.moongl.minecraft.advanceditems.commands.tabcompleters.CommandAdiCompleter;

public enum CommandEnum {
    ADI("adi", new CommandAdi(), new CommandAdiCompleter());

    private final String name;
    private final CommandExecutor command;

    private final TabCompleter completer;

    CommandEnum(String name, CommandAdi command, TabCompleter completer) {
        this.name = name;
        this.command = command;
        this.completer = completer;
    }

    public String getName() {
        return name;
    }

    public CommandExecutor getCommand() {
        return command;
    }

    public TabCompleter getCompleter() { return completer; }
}
