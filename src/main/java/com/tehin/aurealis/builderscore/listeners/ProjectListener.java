package com.tehin.aurealis.builderscore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.tehin.aurealis.builderscore.events.ScoreboardUpdateEvent;
import com.tehin.aurealis.builderscore.project.Project;

public class ProjectListener implements Listener {

	@EventHandler 
	public void onScoreboardUpdate(ScoreboardUpdateEvent e) {
		Project project = e.getProject();
		project.getOnlineMembers().forEach(p -> {
			if (!p.getWorld().getName().equals(project.getName())) return;
			p.setScoreboard(project.getBoard());
		});
	}
	
}
