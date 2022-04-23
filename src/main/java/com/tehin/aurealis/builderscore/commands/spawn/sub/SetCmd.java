package com.tehin.aurealis.builderscore.commands.spawn.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.spawn.Spawn;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.spawn.manager.SpawnsManager;
import com.tehin.aurealis.builderscore.utils.Utils;

public class SetCmd extends CoreSubCommand {

	public SetCmd(String prefix, String description, String permission, int length, String usage) {
		super(prefix, description, permission, length, usage);
	}

	public boolean exec(Player player, String[] args, Project project) {
		if (!super.isValid(player, args)) return false;

		// lobby or whatever you would add later.
		final String type = args[0];

		switch (type) {
			case "lobby":
				Core.getInstance().getSpawnsManager().setLobbySpawn(new Spawn("lobby", player.getLocation()));
				Utils.sendMessage(player, "&a" + args[0] + " has been successfully set as the lobby spawn.");
				break;
			default:
				super.sendUsage(player);
		}

		return false;
	}
}
