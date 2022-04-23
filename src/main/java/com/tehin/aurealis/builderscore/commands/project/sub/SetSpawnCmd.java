package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawnCmd extends CoreSubCommand {

    public SetSpawnCmd(String prefix, String description, String permission, int length, String usage) {
        super(prefix, description, permission, length, usage);
    }

    public boolean exec(Player player, String[] args, Project project) {
        if (!super.isValid(player, args)) return false;

        if (!project.isMember(player)) {
            Utils.sendMessage(player, "&cYou are not a member of this project.");
            return false;
        }

        Location loc = player.getLocation();
        project.setSpawn(loc);

        Utils.sendMessage(player, "&aSpawn of " + project.getName() + " has been successfully changed.");
        return true;
    }
}
