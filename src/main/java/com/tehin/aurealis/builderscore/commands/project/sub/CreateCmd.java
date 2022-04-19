package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.project.size.Size;
import com.tehin.aurealis.builderscore.project.type.CustomWorldType;
import com.tehin.aurealis.builderscore.utils.Utils;

public class CreateCmd extends CoreSubCommand {

	public CreateCmd(String permission, int length, String usage) {
		super(permission, length, usage);
	}

	public boolean exec(Player player, String[] args) {
		if (!super.isValid(player, args)) return false;
		
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
