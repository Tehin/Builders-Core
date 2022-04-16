package com.tehin.aurealis.builderscore.commands.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.spawn.Spawn;
import com.tehin.aurealis.builderscore.utils.Utils;

public class SpawnCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if (args.length > 1) {
			Utils.sendMessage(player, "&cUsage: /spawn or /spawn <spawn name>");
			return false;
		}
		
		if (args.length == 0) {
			Core.getInstance().getSpawnsManager().getLobbySpawn().tp(player);;
			return false;
		}
		
		String name = args[0];
		Spawn spawn = Core.getInstance().getSpawnsManager().getSpawn(name);
		
		if (spawn == null) {
			Utils.sendMessage(player, "&cSpawn " + name + " does not exist.");
			return false;
		}
		
		spawn.tp(player);
		
		return false;
	}

}
