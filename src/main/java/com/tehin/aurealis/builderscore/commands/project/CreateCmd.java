package com.tehin.aurealis.builderscore.commands.project;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.project.size.Size;
import com.tehin.aurealis.builderscore.project.type.CustomWorldType;
import com.tehin.aurealis.builderscore.utils.Utils;

public class CreateCmd implements CommandExecutor {

	// /create <name> <client name> <length> <width> <time limit>
	// /create test 400 500 10/30 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean valid = Utils.verifyCommand(sender, "builders.create", args, 7, 
				"/create <project name> <client name> <length> <width> <time limit> <priority ? 1 to 100> <type ? FLAT or VOID>"
		);
		if (!valid) return false;
		
		Player player = (Player) sender;
		
		final String 
		name = args[0],
		client = args[1],
		timeLimit = args[4];
		
		int length, width, priority;
		
		CustomWorldType worldType = null;
		
		if (Core.getInstance().getProjectsManager().exists(name)) {
			Utils.sendMessage(player, "&cProject " + name + " already exists.");
			return false;
		}
		
		try {
			length = Integer.parseInt(args[2]);
			width = Integer.parseInt(args[3]);
			priority = Integer.parseInt(args[5]);
		} catch (NumberFormatException e) {
			Utils.sendMessage(player, "&cPlease use valid integers for the width, length or priority.");
			return false;
		}
		
		try {
			worldType = CustomWorldType.fromName(args[6]);
		} catch (IllegalArgumentException e) {
			Utils.sendMessage(player, "&cPlease use FLAT or VOID for the world type.");
		}
		
		Project project = new Project(player.getUniqueId(), client, name, new Size(length, width), timeLimit, priority, worldType);
		
		try {			
			project.create();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.sendMessage(player, "&cAn error has ocurred, check the console.");
			return false;
		}
		
		Utils.sendMessage(player, "&aProject " + project.getName() + " has been successfully created!");
		project.teleport(player, false);
		return true;
	}
	

}
