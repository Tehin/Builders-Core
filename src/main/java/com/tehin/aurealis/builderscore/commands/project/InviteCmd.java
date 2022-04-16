package com.tehin.aurealis.builderscore.commands.project;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class InviteCmd implements CommandExecutor {

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
		
		if (!project.isMember(player) || !project.isLeader(player)) {
			Utils.sendMessage(player, "&cYou are not the leader.");
			return false;
		}
		
		@SuppressWarnings("deprecation")
		OfflinePlayer invited = Bukkit.getOfflinePlayer(args[0]);
		
		if (project.getMembers().contains(invited.getUniqueId())) {
			Utils.sendMessage(player, "&c" + invited.getName() + " is already a member of the project.");
			return false;
		}
		
		if (project.getMembers().size() == 6) {
			Utils.sendMessage(player, "&cYour project already has 6 members.");
			return false;
		}
		
		project.inviteMember(invited.getUniqueId());
		Utils.sendMessage(player, "&aSuccessfully invited " + invited.getName() + " to " + project.getName() + " project.");
		return true;
	}
	
}
