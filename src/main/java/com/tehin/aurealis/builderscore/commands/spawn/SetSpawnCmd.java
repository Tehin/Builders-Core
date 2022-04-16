package com.tehin.aurealis.builderscore.commands.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.spawn.Spawn;
import com.tehin.aurealis.builderscore.utils.Utils;

public class SetSpawnCmd implements CommandExecutor {

	
	// /setspawn <name>
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		// Verifies if the player is in the world of a project, so it sets the location only to the project spawn.
		Project project = Core.getInstance().getProjectsManager().getProjectByPlayerLoc(player);
		if (project != null && project.isMember(player)) {
			project.setSpawn(player.getLocation());
			Utils.sendMessage(player, "&aSpawn of " + project.getName() + " project has been successfully set.");
			return false;
		}
		
		// Normal spawn creation
		boolean valid = Utils.verifyCommand(sender, "builders.spawn.config", args, 1, "/setspawn <name> or just /setspawn if you are in the world of a project.");
		
		if (!valid) return false;
		
		Location loc = player.getLocation();
		Spawn spawn = new Spawn(args[0], loc);

		try {			
			spawn.create();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.sendMessage(player, "&cAn error has ocurred, check the console.");
			return false;
		}
		
		Utils.sendMessage(player, "&aSpawn " + spawn.getSpawnName() + " has been successfully created at " + spawn.getWorldName() + " world.");
		return false;
	}
	
	

}
