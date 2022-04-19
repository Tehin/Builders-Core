package com.tehin.aurealis.builderscore.commands.project.sub;

import com.tehin.aurealis.builderscore.commands.CoreSubCommand;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class ChatCmd extends CoreSubCommand {

	public ChatCmd(String permission, int length, String usage) {
		super(permission, length, usage);
	}

	public boolean exec(Player player, String[] args, Project project) {
		if (!super.isValid(player, args)) return false;
		
		if (!project.isMember(player)) {
			Utils.sendMessage(player, "&cYou are not a member of this project.");
			return false;
		}

		return true;
	}

}
