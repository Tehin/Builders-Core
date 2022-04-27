package com.tehin.aurealis.builderscore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.tehin.aurealis.builderscore.project.Project;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardUpdateEvent extends Event  {
	
	private List<Player> players;
	private Scoreboard updatedScoreboard;

	public ScoreboardUpdateEvent(List<Player> players, Scoreboard updatedScoreboard) {
		this.players = players;
		this.updatedScoreboard = updatedScoreboard;
	}

	public Scoreboard getUpdatedScoreboard() {
		return updatedScoreboard;
	}
	public List<Player> getPlayers() {
		return this.players;
	}

	private static final HandlerList handlers = new HandlerList(); //This are the unimplemented methods.
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
