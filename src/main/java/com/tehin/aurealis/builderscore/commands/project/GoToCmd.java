package com.tehin.aurealis.builderscore.commands.project;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class GoToCmd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean valid = Utils.verifyCommand(sender, null, args, 1, "/goto <project name>");
		if (!valid) return false;
		
		Player player = (Player) sender;
		
		Project project = Core.getInstance().getProjectsManager().getProjectByName(args[0]);
		
		if (project == null) {
			Utils.sendMessage(player, "&cProject " + args[0] + " does not exist.");
			return false;
		}
		
		if (player.hasPermission("builders.bypass")) {
			project.teleport(player, true);
		}
		
		if (!player.hasPermission("builders.bypass") && !project.isMember(player)) {
			Utils.sendMessage(player, "&cYou are not member of this project, please use /visit " + args[0]);
			return false;
		}
		
		project.teleport(player, false);
		return true;
	}
	

}
