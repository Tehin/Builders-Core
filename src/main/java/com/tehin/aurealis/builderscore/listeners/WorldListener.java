package com.tehin.aurealis.builderscore.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.tehin.aurealis.builderscore.Core;
import com.tehin.aurealis.builderscore.project.Project;
import com.tehin.aurealis.builderscore.utils.Utils;

public class WorldListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		UUID uuid = e.getPlayer().getUniqueId();
		String worldName = e.getBlock().getLocation().getWorld().getName();
		Project project = Core.getInstance().getProjectsManager().getProjectByName(worldName);
		
		System.out.println(player.hasPermission("builders.bypass"));
		if (project == null || player.hasPermission("builders.bypass")) return;
		
		if (!project.getMembers().contains(uuid)) {
			e.setCancelled(true);
			Utils.sendNoPermission(player);
			return;
		}

		if (project.isClosed() && (uuid != project.getLeader())) {
			e.setCancelled(true);
			player.sendMessage(Utils.toColor("&cOnly the leader can modify the project when it's closed."));
			return;
		}
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		UUID uuid = e.getPlayer().getUniqueId();
		String worldName = e.getBlock().getLocation().getWorld().getName();
		Project project = Core.getInstance().getProjectsManager().getProjectByName(worldName);
		
		if (project == null || player.hasPermission("builders.bypass")) return;
		
		if (!project.getMembers().contains(uuid)) {
			e.setCancelled(true);
			Utils.sendNoPermission(player);
			return;
		}

		if (project.isClosed() && (uuid != project.getLeader())) {
			e.setCancelled(true);
			player.sendMessage(Utils.toColor("&cOnly the leader can modify the project when it's closed."));
			return;
		}
	}

}
