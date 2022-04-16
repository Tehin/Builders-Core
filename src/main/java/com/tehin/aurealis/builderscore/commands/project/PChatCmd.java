package com.tehin.aurealis.builderscore.commands.project;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class PChatCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean valid = Utils.verifyCommand(sender, null, args, 1, "/invite <player>");
		if (!valid) return false;
		
		Player player = (Player) sender;
		Project project = Core.getInstance().getProjectsManager().getProjectByPlayerLoc(player);
		
		if (project == null) {
			Utils.sendMessage(player, "&cYou are not in the world of a project, please teleport.");
			return false;
		}
		
		if (!project.isMember(player)) {
			Utils.sendMessage(player, "&cYou are not a member of this project.");
			return false;
		}

		
		
		return true;
	}

}
