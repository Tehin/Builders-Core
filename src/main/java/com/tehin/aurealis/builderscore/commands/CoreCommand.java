package com.tehin.aurealis.builderscore.commands;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class CoreCommand implements CommandExecutor {

    private final String name;

    public CoreCommand(String name) {
        this.name = name;
    }

    public abstract boolean sendCmd(Player player, String cmd, String[] subCmdArgs);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0) {
            if (command.getName().equalsIgnoreCase("spawn")) {
                Core.getInstance().getSpawnsManager().getLobbySpawn().tp(player);
                return true;
            }
            player.sendMessage(Utils.toColor("&cFor usage: /" + name + " help"));
            return false;
        }

        String cmd = args[0];
        String[] subCmdArgs = Arrays.copyOfRange(args, 1, args.length);

        final boolean success = sendCmd(player, cmd, subCmdArgs); // Executes the commands and returns to Spigot if it was successfully done or not.

        return success;
    }
}
