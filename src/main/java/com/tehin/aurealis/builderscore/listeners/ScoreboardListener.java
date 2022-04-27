package com.tehin.aurealis.builderscore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.tehin.aurealis.builderscore.events.ScoreboardUpdateEvent;
import com.tehin.aurealis.builderscore.project.Project;

import java.util.Arrays;

public class ScoreboardListener implements Listener {

	@EventHandler 
	public void onScoreboardUpdate(ScoreboardUpdateEvent e) {
		System.out.println(Arrays.toString(e.getPlayers().toArray()));
		e.getPlayers().forEach(p -> {
			p.setScoreboard(e.getUpdatedScoreboard());
		});
	}
	
}
