package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class GoCmd extends CoreSubCommand {

	public GoCmd(String prefix, String description, String permission, int length, String usage) {
		super(prefix, description, permission, length, usage);
	}

	@Override
	public boolean exec(Player player, String[] args, Project project) {
		if (!super.isValid(player, args)) return false;

		project = Core.getInstance().getProjectsManager().getProjectByName(args[0]);
		
		if (project == null) {
			Utils.sendMessage(player, "&cProject " + args[0] + " does not exist.");
			return false;
		}
		
		if (player.hasPermission("builders.rol.admin")) {
			project.teleport(player, true);
			return true;
		}
		
		if (!project.isMember(player)) {
			Utils.sendMessage(player, "&cYou are not member of this project, please use /project visit " + args[0]);
			return false;
		}
		
		project.teleport(player, false);
		return true;
	}


}
