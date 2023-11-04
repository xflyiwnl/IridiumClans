package me.xflyiwnl.iridiumclans.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ClanCommand implements TabCompleter, CommandExecutor {

    private List<String> tabCompletes = Arrays.asList(
            "create",
            "remove",
            "deposit",
            "withdraw",
            "storage",
            "invite",
            "kick",
            "members",
            "info",
            "ranks",
            "rank"
    );

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return tabCompletes;
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

}
