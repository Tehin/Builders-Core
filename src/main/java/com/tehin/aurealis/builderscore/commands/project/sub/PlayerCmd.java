package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;

import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerCmd extends CoreSubCommand {

    public PlayerCmd(String prefix, String description, String permission, int length, String usage) {
        super(prefix, description, permission, length, usage);
    }

    public boolean exec(Player player, String[] args, Project project) {
        if (!super.isValid(player, args)) return false;

        if (!project.isMember(player) || !project.isLeader(player)) {
            Utils.sendMessage(player, "&cYou have no permission in this project to modify the members.");
            return false;
        }

        final String action = args[0];

        final OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        boolean isMember = project.isMember(target.getUniqueId());

        switch (action) {
            case "add":
                if (isMember) {
                    Utils.sendMessage(player, "&c" + target.getName() + " is already a member of the project.");
                    return false;
                }

                if (project.getMembers().size() == 6) {
                    Utils.sendMessage(player, "&cYour project already has 6 members.");
                    return false;
                }

                project.addPlayer(target.getUniqueId());
                break;
            case "remove":
                if (!isMember) {
                    Utils.sendMessage(player, "&c" + target.getName() + " is not a member of the project.");
                    return false;
                }

                project.removePlayer(target.getUniqueId());
                break;
            default:
                super.sendUsage(player);
        }

        Utils.sendMessage(player, "&aSuccessfully " + action + "ed" + " " + target.getName() + " from " + project.getName() + " project.");
        return true;
    }
}
