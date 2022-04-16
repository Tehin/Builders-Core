package com.tehin.aurealis.builderscore.commands.project;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class VisitCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean valid = Utils.verifyCommand(sender, null, args, 1, "/visit <project name>");
		if (!valid) return false;
		
		Player player = (Player) sender;
		
		Project project = Core.getInstance().getProjectsManager().getProjectByName(args[0]);
		
		if (project == null) {
			Utils.sendMessage(player, "&cProject " + args[0] + " does not exist.");
			return false;
		}
		
		project.visit(player);
		return true;
	}

}
