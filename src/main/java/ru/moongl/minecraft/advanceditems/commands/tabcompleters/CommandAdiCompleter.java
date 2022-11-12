package ru.moongl.minecraft.advanceditems.commands.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandAdiCompleter implements TabCompleter {

    private ArrayList<String> first_arg_complete =
            new
                    ArrayList<>(Arrays.asList(
                    "create",
                    "delete",
                    "menu"
            ));
    private ArrayList<String> second_arg_complete =
            new
                    ArrayList<>(Arrays.asList(
                    "sword",
                    "pickaxe",
                    "shield",
                    "armor",
                    "bow"
            ));

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) {
            return first_arg_complete;
        } else if(args.length == 2 && args[0].equals("create")) {
            return second_arg_complete;
        } else if(args.length == 2 && args[0].equals("delete")){
            return List.of("name");
        } else {
            return List.of("");
        }
    }
}
