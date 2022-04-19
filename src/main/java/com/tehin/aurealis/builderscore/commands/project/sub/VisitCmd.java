package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class VisitCmd extends CoreSubCommand {

	public VisitCmd(String permission, int length, String usage) {
		super(permission, length, usage);
	}

	public boolean exec(Player player, String[] args) {
		if (!super.isValid(player, args)) return false;

		Project project = Core.getInstance().getProjectsManager().getProjectByName(args[0]);

		if (project == null) {
			Utils.sendMessage(player, "&cProject " + args[0] + " does not exist.");
			return false;
		}
		
		project.visit(player);
		return true;
	}

}
