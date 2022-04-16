package com.tehin.aurealis.builderscore.commands.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.spawn.manager.SpawnsManager;
import com.tehin.aurealis.builderscore.utils.Utils;

public class SetLobbyCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean valid = Utils.verifyCommand(sender, "builders.spawn.config", args, 1, "/setlobby <spawn name>");
		
		if (!valid) return false;
		
		SpawnsManager manager = Core.getInstance().getSpawnsManager();
		
		Player player = (Player) sender;
		
		String name = args[0];
		
		if (!manager.exists(name)) {
			Utils.sendMessage(player, "&cThat spawn has not been created.");
			return false;
		}
		
		if (manager.getLobbySpawn().getSpawnName().equalsIgnoreCase(name)) {
			Utils.sendMessage(player, "&cThat spawn has been already set as the lobby.");
			return false;
		}
		
		manager.setLobbySpawn(manager.getSpawn(name));
		Utils.sendMessage(player, "&a" + args[0] + " has been successfully set as the lobby spawn.");
		
		return false;
	}
}
