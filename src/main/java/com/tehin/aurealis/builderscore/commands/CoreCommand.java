package com.tehin.aurealis.builderscore.commands;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public abstract class CoreCommand implements CommandExecutor {

    private final String name;
    private final HashMap<String, CoreSubCommand> subCommands;

    public CoreCommand(String name) {
        this.name = name;
        this.subCommands = new HashMap<>();

        registerSubCommands();
    }

    public void registerSubCommand(CoreSubCommand subCmd) {
        this.subCommands.put(subCmd.getPrefix(), subCmd);
    }

    public CoreSubCommand getSubCommand(String prefix) {
        return this.subCommands.get(prefix);
    }

    protected Collection<CoreSubCommand> getSubCommands() {
        return this.subCommands.values();
    }

//    Abstract Implementations
    public abstract void registerSubCommands();

    public abstract boolean sendCmd(Player player, String cmd, String[] subCmdArgs);


//      Available Methods
    protected boolean exists(String subCommand) {
        return this.subCommands.containsKey(subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0) {
            if (command.getName().equalsIgnoreCase("spawn")) {
                Core.getInstance().getSpawnsManager().sendToSpawn(player);
                return true;
            }
            player.sendMessage(Utils.toColor("&cFor usage: /" + name + " help"));
            return false;
        }

        String cmd = args[0];

        if (!exists(cmd)) {
            Utils.sendMessage(player, "&cThat command does not exist, please use /project help");
            return false;
        }

        String[] subCmdArgs = Arrays.copyOfRange(args, 1, args.length);

        final boolean success = sendCmd(player, cmd, subCmdArgs); // Executes the commands and returns to Spigot if it was successfully done or not.

        return success;
    }
}
